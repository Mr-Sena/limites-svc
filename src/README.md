

 ## How to run service:
 
 - Before make the app build, execute the database docker container.
 
 ``` 
docker compose -f docker-compose-postgres.yaml up
 ```

 > Executar também, o docker-compose do diretório do projeto transaction-bff para provisionar os tópicos do Kafka.

<br><br>

 ## Http Curl Request
 ### Enviar o arquivo para armazenar em núvem [CLOUD AWS S3]

 ```
curl --form file='@<replace-by-filepath>' \
-X POST -v --header "Content-Type: multipart/form-data" \
localhost:8086/documentos
 ```

 - For instance: 
```
curl --form file='@/home/mr_sena/Imagens/Brasil-Order-Empire.jpg' \
-X POST -v --header "Content-Type: multipart/form-data" \
localhost:8086/documentos
```
<br>

 ### Carregar o arquívo do armazenamento: 

 ```
curl -v --output <destino-do-arquivo> \
localhost:8086/documentos/<source-filename>
```

 - For instance: 
 ```
curl -v --output ./target-image \
localhost:8086/documentos/b16d8d8b-0873-403a-b9d5-f31d08d4eecdfile
```
