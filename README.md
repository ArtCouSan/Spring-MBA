# Spring-MBA
Avaliação de Spring para MBA

# ChatBotTelegram
Projeto para o MBA - Criação de bot  telegram

Spring Boot Rest API + Swagger
Usando como banco temporario o H2, pela simplicidade e facilidade de uso.

# Docs

http://localhost:8080/swagger-ui.html#/

Usuario Controller:

**POST - /usuario**

Cadastra conta do usuario, onde será informado o dia de pagamento da fatura e o limite de credito

{
  "conta": {
    "diaDoMesFatura": 1,
    "limite": 5000
  },
  "nick": "teste",
  "nome": "Nome teste",
  "password": "1234"
}

**PUT - /usuario**

Usado para atualizar dados pessoais do usuario

{
  "nick": "teste2",
  "nome": "Nome teste",
  "password": "4567"
}

**GET - /usuario/{id}**

Busca informações do usuario pelo id informado

**POST - /usuario/upload**

Selecione o txt com os alunos e aguarde cadastro dos mesmos, onde será enviado:

>Nome: AARON FELIPE GRASSMANN 

>Nick: 3095564

>Password: 100-11


Conta Controller:


Transferencia Controller:




# Autor

```
Arthur Coutinho Santos - RM 336256
```
