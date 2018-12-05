package ch.so.agi.ilivalidator.functions;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.impl.io.DefaultHttpRequestParser;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.io.SessionInputBufferImpl;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import play.mvc.Http.RequestBody;

/**
 * Azure Functions with HTTP Trigger.
 * 
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpTrigger-Java". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTrigger-Java&code={your function key}
     * 2. curl "{your host}/api/HttpTrigger-Java?name=HTTP%20Query&code={your function key}"
     * Function Key is not needed when running locally, to invoke HttpTrigger deployed to Azure, see here(https://docs.microsoft.com/en-us/azure/azure-functions/functions-bindings-http-webhook#authorization-keys) on how to get function key for your app.
     */
    @FunctionName("validate")
    public String run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS, dataType="binary") HttpRequestMessage<Byte[]> req,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        context.getLogger().info(req.getBody().toString());

        try {
            Path tmpDirectory = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")),
                    "ilivalidator_");
//            context.getLogger().info(tmpDirectory.toAbsolutePath().toString());

            //String toWrite = "Hello";

            RequestBody requestBody = new RequestBody(req.getBody());
            context.getLogger().info(requestBody.asText());
            
            String str = new String(ArrayUtils.toPrimitive(req.getBody()));
            context.getLogger().info(str);

            File xtfFile = File.createTempFile("upload", ".itf");
            FileWriter writer = new FileWriter(xtfFile);
            writer.write(str);
            writer.close();
            
            File fileDir = new File("/vagrant/fubar8.tmp");
			
    		Writer out = new BufferedWriter(new OutputStreamWriter(
    			new FileOutputStream(fileDir), "ISO-8859-1"));
    		out.write(str);
    		out.close();
    		
    		 context.getLogger().info(String.valueOf(str.contains("ü")));


//            ByteArrayInputStream inputStream = new ByteArrayInputStream(req.getBody().getBytes());
//            HttpTransportMetricsImpl metrics = new HttpTransportMetricsImpl();
//            SessionInputBufferImpl inbuffer = new SessionInputBufferImpl(metrics, 1024);
//            inbuffer.bind(inputStream);
//            HttpMessageParser<HttpRequest> requestParser = new DefaultHttpRequestParser(inbuffer);
//            BasicHttpEntityEnclosingRequest request = (BasicHttpEntityEnclosingRequest)requestParser.parse();

//            for (Header hdr : request.getAllHeaders()) {
//                context.getLogger().info((String.format("%-30s = %s", hdr.getName(), hdr.getValue())));
//            }

//            context.getLogger().info(xtfFile.getAbsolutePath().toString());
            context.getLogger().info(req.getHeaders().toString());
//            context.getLogger().info(req.getQueryParameters().toString());

//            context.getLogger().info(req.toString());
//            context.getLogger().info(req.getBody());

        } catch (IOException e) {
            e.printStackTrace();
            context.getLogger().info(e.getMessage());
		} 

        return "fubar";
        // Parse query parameter
        //String query = request.getQueryParameters().get("name");
        //String name = request.getBody().orElse(query);

        //if (name == null) {
        //    return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        //} else {
        //    return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name).build();
        //}
    }
}
