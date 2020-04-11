# Spring-MBA
Avaliação de Spring para MBA

Spring Boot Rest API + Swagger
Usando como banco temporario o H2, pela simplicidade e facilidade de uso.

# Docs

http://localhost:8080/swagger-ui.html#/

## Usuario Controller:

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


## Conta Controller:

**GET - /conta**

Traz todas as contas presentes no sistema, com suas respectivas transações

**GET - /conta/{id}**

Busca a conta pelo id

**GET - /conta/{id}/extrato**

Gera extrato consolidado para downlaod do historico de transações no formato .csv

## Transferencia Controller:

**POST - /conta/{id}/transferencia/pagar-a-vista**

Para pagamento a vista, sera necessario informar o valor e o responsavel do debito na credito. Alem é claro do id da conta do usuario

{
  "responsavel": "Loja X",
  "valor": 150.1
}

**POST - /conta/{id}/transferencia/pagar-parcelado**

Para pagamento em parcelas, sera verifica o credito no mes solicitado, no envio será solicitado o numero de parcelas e o juros cobrado 
sobre cada parcela. . Alem é claro do id da conta do usuario

{
  "juros": 0,
  "numeroParcela": 0,
  "responsavel": "string",
  "valorParcela": 0
}

# Carga

O sistema realiza uma carga, cadastrando o seguinte usuario

{
  "mensagem": null,
  "idConta": 1,
  "usuario": {
    "mensagem": null,
    "nome": "Arthur",
    "idUsuario": 1,
    "nick": "teste",
    "status": true
  },
  "historico": [
    {
      "idHistorico": 1,
      "nomeCliente": "Loja X",
      "dataTransacao": "2020-04-01T03:00:00.000+0000",
      "valorParcela": 50,
      "numeroParcelas": 1,
      "juros": 0
    },
    {
      "idHistorico": 2,
      "nomeCliente": "Loja Y",
      "dataTransacao": "2020-04-01T03:00:00.000+0000",
      "valorParcela": 50,
      "numeroParcelas": 2,
      "juros": 0
    }
  ],
  "tranferencias": [
    {
      "mensagem": null,
      "idTransferencia": 1,
      "dataInsercao": "2020-04-01",
      "dataLimitePagamento": "2020-05-01T03:00:00.000+0000",
      "tipo": "D",
      "valor": 50,
      "responsavel": "Loja X"
    },
    {
      "mensagem": null,
      "idTransferencia": 2,
      "dataInsercao": "2020-04-01",
      "dataLimitePagamento": "2020-05-01T03:00:00.000+0000",
      "tipo": "D",
      "valor": 50,
      "responsavel": "Loja Y"
    },
    {
      "mensagem": null,
      "idTransferencia": 3,
      "dataInsercao": "2020-04-01",
      "dataLimitePagamento": "2020-06-01T03:00:00.000+0000",
      "tipo": "D",
      "valor": 50,
      "responsavel": "Loja Y"
    }
  ],
  "saldo": 4500,
  "limite": 5000,
  "consumido": 100,
  "diaDoMesFatura": 1
}


# Autor

```
Arthur Coutinho Santos - RM 336256
```
