# azure-functions-test

```
mvn archetype:generate -DarchetypeGroupId=com.microsoft.azure -DarchetypeArtifactId=azure-functions-archetype 
```


```
curl -XPOST --data-binary @ch_254900.itf --header "Content-Type:application/octet-stream" http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream" --data-binary "ch_254900.itf" http://localhost:7071/api/validate


curl --request POST --header "Content-Type:multipart/form-data" -F file=@ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/x-www-form-urlencoded" -F file=@ch_254900.itf http://localhost:7071/api/validate

curl --request POST --header "Content-Type:application/x-www-form-urlencoded" -F file=@ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream" --data-binary @ch_254900.itf http://localhost:7071/api/validate

curl --request POST --header "Content-Type:application/x-www-form-urlencoded; charset=ISO-8859-1" --data-binary @ch_254900.itf http://localhost:7071/api/validate
curl --request POST --header "Content-Type:application/octet-stream; charset=ISO-8859-1" --data-binary @ch_254900.itf http://localhost:7071/api/validate

```