# psybot

## TODO List
* autenticacao
* salvar evolucao
* criar estrutura de atendimento e vincular com evolucao
* integracao com o chatgpt
* enviar evolucao para o chat e popular possiveis cids
* formatar toda evolucao do paciente (talvez algum grafico ?)
* publicar gcp
* consultas por login
* front :(

### Login
curl --location --request POST 'http://localhost:8888/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "user",
"password": "batata"
}'

### Get
curl --location --request GET 'http://localhost:8888/psychiatrist' \
--header 'Authorization: Bearer TOKEN'