# azure-functions-test

```
mvn archetype:generate -DarchetypeGroupId=com.microsoft.azure -DarchetypeArtifactId=azure-functions-archetype 

mvn clean package && mvn azure-functions:run

az login

mvn azure-functions:deploy
```

```
curl --request POST --header "Content-Type:application/octet-stream" --data-binary @ch_254900.itf https://ilivalidator-functions-20181205142252080.azurewebsites.net/api/validate?code=XXXXXXX

curl --request POST --header "Content-Type:application/octet-stream" --data-binary @Hauptnutzung_CH_V1_1.xml https://ilivalidator-functions-20181205142252080.azurewebsites.net/api/validate?code=XXXXXXX


```



```
curl -XPOST --data-binary @ch_254900.itf --header "Content-Type:application/octet-stream" http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream" --data-binary "ch_254900.itf" http://localhost:7071/api/validate


curl --request POST --header "Content-Type:multipart/form-data" -F file=@ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/x-www-form-urlencoded" -F file=@ch_254900.itf http://localhost:7071/api/validate

curl --request POST --header "Content-Type:application/x-www-form-urlencoded" -F file=@ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream" --data-binary @ch_254900.itf http://localhost:7071/api/validate

curl --request POST --header "Content-Type:application/x-www-form-urlencoded; charset=ISO-8859-1" --data-binary @ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/x-www-form-urlencoded; charset=ISO-8859-1" --data-binary @ch_254900.itf.zip http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream; charset=ISO-8859-1" --data-binary @ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream" --data-binary @ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream; charset=ISO-8859-1" --data-binary @ch_254900.itf.zip http://localhost:7071/api/validate


```

## Fubar
failed to create folder http://models.interlis.ch/

System.getProperty("user.home")
C:\
https://github.com/claeis/ili2c/blob/b7d6ed2ab3ace4a4c4f1a980cf831ae6fd53ea29/src/ch/interlis/ilirepository/impl/RepositoryAccess.java
https://github.com/claeis/ili2c/blob/97f868d52c3558cacf1aa11675d0ed4e42ed012d/doc/CHANGELOG.txt
System.setProperty()
D:\local\Temp

logging?

grösse?