# desafio-backend-totvs

Para subir a aplicação bastar seguir os passos:

1. rodar o comando na pasta do projeto
   - mvn package
2. rodar o comando para criar nossa imagem docker
   - docker build -t desafio-backend-totvs .
3. Agora vamos execultar no docker compose
   - docker-compose up --build
***
### Configuração do keycloak

Depois que o docker do Keycloak subir, acesse o keycloak no endereço localhost:8080 utilizando o usuário e senha admin.

Acessar o Keycloak Admin Console
Abra seu navegador e vá para 

    http://localhost:8080

1. Faça login com o usuário administrativo 
   - Por padrão, é **admin** com senha **admin**.
2. Criar um Realm
   - No Keycloak Admin Console, clique no menu suspenso no canto superior esquerdo e selecione "Add Realm".
         Insira um nome **TOTVS** para o seu realm e clique em "Create".
3. Criar um Cliente (Representando sua API)
   Dentro do seu novo realm, vá para a seção "Clients" no menu lateral.
   Clique em "Create".
      Preencha o formulário com:
      - Client ID: **spring-client**
      - Client Protocol: openid-connect
      - Clique em "NEXT" -> "NEXT" -> "SAVE"
4. Agora vamos criar as Rols
   clique em Realms Roles no menu esquerdo,
    e em create role
      - role name = **spring-user-role** e salve.
5. Criar um Usuário.

   No Keycloak Admin Console, vá para a seção "Users" no menu lateral.

    Clique em "Add User".
    Preencha o formulário com:
   - Username: **apiuser**
   - Clique em "Save".
   
   Vá para a aba "Roles mapping" do usuário recém-criado.
   Clique em "Assing roles".
   - Selectione a roles **spring-user-role** e clique em Assign
   
   Vá para a aba "Credentials" do usuário recém-criado.
   Insira uma senha para o usuário, marque "Temporary" como Off e clique em "Set Password".
6. Vamos no menu Authentication
   - Na Aba Requered action e desmarque a opção  **verify profile**
7. Parafinalizar vamos no menu Realm setting 
   - no campo Frontend URL vamos colocar **http://keycloak:8080**
   - clique em Save.
   
Agora nosso keycloak já está configurado para disponibilizar o token de acesso da nossa API.
    Acesse o endereço

        http://localhost:8080/realms/TOTVS/protocol/openid-connect/token
***
        curl --location --request POST 'http://localhost:8080/realms/TOTVS/protocol/openid-connect/token' \
        --header 'Content-Type: application/x-www-form-urlencoded' \
        --header 'Cookie: JSESSIONID=0D1A0BF27124B09B714C8DD5120AECBC' \
        --data-urlencode 'client_id=spring-client' \
        --data-urlencode 'username=apiuser' \
        --data-urlencode 'password=123456' \
        --data-urlencode 'grant_type=password'

com o valor retornado no access_token,vamos passar para as nossas APIs. 
Segue uma consulta de exemplo.

    curl --location --request GET 'localhost:8081/v1/bills/1' \
    --header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJNdkFDc3l1R1hnNGNmUklJY01lSkc4VTNWRkIzQTAweVU2dUtHT0R4aXA0In0.eyJleHAiOjE3MTcxNjgzMDYsImlhdCI6MTcxNzE2ODAwNiwianRpIjoiNTRlYjE0MTktYWQ3OS00OTQ5LWFkNGMtMzhjN2YzMWNmYjM5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9UT1RWUyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJkMzI4Njk1OS1jNmExLTQwM2QtODYyNi03OTBiNTBjZThiOGUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJzcHJpbmctY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6IjBlZDhlYmJjLTk0NDMtNDI5ZC05OWYzLWIzNTY0MmM4YmRkOSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiLyoiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJzcHJpbmctdXNlci1yb2xlIiwiZGVmYXVsdC1yb2xlcy10b3R2cyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjBlZDhlYmJjLTk0NDMtNDI5ZC05OWYzLWIzNTY0MmM4YmRkOSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiYXBpdXNlciJ9.RTKCu5Ualx8JHh0-0gaK_XjMpZn6MwK5XtbuoDY-pZJ5A_X1hjvDp6dcyBO_MHznvMbCL3dEER2Sy_YTy5Ccmwr4pHRF6wVntww0PD9d_6MAZzTheX3LAdx15BBuNBeUM4vj7hLR6JJ6gmLiSAT-yQ5O1teWqb6f-SsTWxgaR7FMTX6gyDWmcG0jj8390vZL0jperz6CGKtBEExKlhuGVQvhCzCto3CfWjHLS4Fab5N2FCLIBD74RjaVCG6itdUFPYXZabn-r-x48RytvLxh4tM2dQ6fhcYba_jYlCvxe2On6-aA3YbhyffnjC0DIB3Ne95sbNV95A02vyYT2dhYyA' \

*** 
OBS: O sistema em Spring boot só funcionará quando o keycloak tiver configurado.
O arquivo csv está na pasta do projeto.