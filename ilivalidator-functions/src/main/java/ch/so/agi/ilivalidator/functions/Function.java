package ch.so.agi.ilivalidator.functions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.microsoft.azure.functions.annotation.*;

import ch.ehi.basics.settings.Settings;

import com.microsoft.azure.functions.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.interlis2.validator.Validator;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/validate". Invoke it using "curl" command in bash:
     * _ curl --request POST --header "Content-Type:application/octet-stream" --data-binary @ch_254900.itf http://localhost:7071/api/validate&code={your function key}
     * Function Key is not needed when running locally, to invoke HttpTrigger deployed to Azure, see here(https://docs.microsoft.com/en-us/azure/azure-functions/functions-bindings-http-webhook#authorization-keys) on how to get function key for your app.
     */
    @FunctionName("validate")
    public String run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION, dataType="binary") HttpRequestMessage<Byte[]> req,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        
        // Set user home to a writable directory on azure.
        // Default is C:\
        // ili2c wants to create the .ilicache directory.
        System.setProperty("user.home", "/tmp");

        try {                        
            byte[] byteFile = ArrayUtils.toPrimitive(req.getBody());
            File uploadedFile = File.createTempFile("upload", ".interlis");
            FileUtils.writeByteArrayToFile(uploadedFile, byteFile);

            String logFileName = uploadedFile.getAbsolutePath() + ".log";

	        Settings settings = new Settings();
	        settings.setValue(Validator.SETTING_ILIDIRS, Validator.SETTING_DEFAULT_ILIDIRS);
	        settings.setValue(Validator.SETTING_LOGFILE, logFileName);

	        Validator.runValidation(uploadedFile.getAbsolutePath(), settings);
	        
	        String logFileContent = new String(Files.readAllBytes(Paths.get(logFileName)));
	        return logFileContent;
		} catch (Exception e) {
            e.printStackTrace();
            context.getLogger().info(e.getMessage());
            return e.getMessage();
		}
    }
}
