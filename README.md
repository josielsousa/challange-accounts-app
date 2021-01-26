# Challenge-Accounts

Desafio técnico em Java: API responsável por fornecer funcionalidades de criação de contas e transferências entre elas para um banco digital.

### Pré-requisitos

Possuir `docker` e `docker-compose` instalados.

-  [docker](https://docs.docker.com/get-docker/)

-  [docker-compose](https://docs.docker.com/compose/install/)

---

### Instalação

Após a instalação dos pré-requisitos, para disponibilizar a API, basta executar os próximos comandos:

```bash
git clone https://github.com/josielsousa/challenge-accounts.git
cd challenge-accounts
docker-compose up api
```

---

### Testes unitários

A API foi construída possuindo testes unitários, para executar os testes, basta executar os seguintes comandos:

```bash
git clone https://github.com/josielsousa/challenge-accounts.git
cd challenge-accounts
docker-compose up api-test
```


Após a execução de todos testes unitários, será criado um arquivo chamado `coverage.html`, contendo o resumo da cobertura dos testes realizados.

  ---
  
### Rotas / Endpoints - `Accounts`


*  `/accounts` - `POST` - Rota utilizada para criação de uma nova `account`


	Exemplo de requisição : 
	```bash 
	curl --request POST -v \
	  --url http://localhost:3000/accounts \
	  --header 'content-type: application/json' \
	  --data '{
		"cpf": "04075532151",
		"name": "Juliana Alice Luana Nunes",
		"secret": "secret",
		"balance": 99.50
	}'
	```


	Payload de entrada:
	```json
	{
		"cpf": "47298817027",
		"name": "Joãozinho",
		"secret": "secret",
		"balance": 80.99
	}
	```
	| Atributo| Obrigatório | Descrição
	|--|--|--|
	| name | SIM | Nome do usuário que a conta que será criada.
	| cpf | SIM | Número de CPF do usuário. Deve ser utilizado um número válido
	| secret | SIM | Senha para ser utilizada na autenticação do usuário
	| balance | NÃO | Saldo inicial da conta que será criada.


	Payload Retornos: 

*  Status Code `200` - Sucesso, conta criada 
	```json
	{
		"id": "ac23d91c-08b0-45aa-90d9-9534207c318e",
		"cpf": "47298817027",
		"name": "Joãozinho",
		"secret": "$2a$10$ID9bqUy9DvXqKGrGsRuJBuiZ.WvTcbs9X.UkEEHcYuUdu5IuULtNm",
		"balance": 80.99,
		"created_at": "2020-07-26T21:25:41.123199754Z"
	}
	```

	
	| Atributo| Descrição
	|--|--|
	| id | UUID gerado para a `account`.
	| name | Nome do usuário.
	| cpf | Número de CPF do usuário. 
	| secret | Hash gerado para o `secret` informado.
	| balance | Saldo inicial da conta que será criada.
	| created_at | Data de criação da conta 


* Status Code `422` -  Erro - Os dados de entrada são válidos porém existe uma `account` para o CPF informado. 
	```json
	{
	  "error": "Já existe uma conta criada com o CPF informado."
	}
	```

* Status Code `500` -  Erro inesperado durante o processamento da requisição
	```json
	{
	  "error": "Erro Inesperado"
	}
	```
---

*  `/accounts` - `GET` - Rota utilizada para listagem de todas as `accounts`


	Exemplo de requisição : 
	```bash 
	curl --request GET -v --url http://localhost:3000/accounts
	```

* Status Code `204` -  Requisição executada com sucesso, porém não possui dados de retorno, lista de `accounts` vazia.
		
	Payload de retorno: Não se aplica.

* Status Code `200` -  Quando existir accounts para serem retornadas

	Payload de retorno:
	```json
	{
	  "data": [
	    {
	      "id": "011bd273-6f88-4488-8d69-abee5c41340f",
	      "cpf": "47298817027",
	      "name": "Joãozinho",
	      "secret": "$2a$10$FNcpXUGL1nhnF51t2Xojt.B.dndOKfB9zZOyy3n5hdF9cg3gnFZMq",
	      "balance": 80.99,
	      "created_at": "2020-07-26T23:04:30.904920793Z"
	    },
	    {
	      "id": "689b2629-f02a-412e-873f-cfaab344b413",
	      "cpf": "14394183901",
	      "name": "Emanuelly Cláudia Jennifer",
	      "secret": "$2a$10$bKLye7aAf/f.D/tgHH4vDuo6KDK17mKFM.Thmt/aSLFzJTP4Bndny",
	      "balance": 0,
	      "created_at": "2020-07-26T23:05:01.010789798Z"
	    },
	    {
	      "id": "41a721e6-16d3-45ae-8604-b6239fea31ae",
	      "cpf": "04075532151",
	      "name": "Juliana Alice Luana Nunes",
	      "secret": "$2a$10$e3hj/3mVf4.K8cb.s50aKOrN2SXF2ZWYLJeigev1hJCjOXxYI68te",
	      "balance": 99.5,
	      "created_at": "2020-07-26T23:47:48.877912737Z"
	    }
	  ],
	  "success": true
	}
	```
	
	| Atributo| Descrição
	|--|--|
	| data | Lista de `accounts`
	| success | Boolean - Indica o status de sucesso para a requisição

* Status Code `500` -  Erro inesperado durante o processamento da requisição
	```json
	{
	  "error": "Erro Inesperado"
	}
	```
---

*  `/accounts/{id}/balance` - `GET` - Rota utilizada para recuperar o saldo de uma  `account`


	Exemplo de requisição : 
	```bash 
	curl --request GET -v \
	  --url http://localhost:3000/accounts/{id}/balance
	```

	Payload de retorno:

*  Status Code `200` - Sucesso, conta encontrada, saldo retornado: 

	```json
	{
		"data": {
		    "balance": 80.99
		},
		"success": true
	}
	```
	
	| Atributo| Descrição
	|--|--|
	| data | Retorna o saldo da `account` no atributo `balance`
	| success | Boolean - Indica o status de sucesso para a requisição

* Status Code `500` -  Erro inesperado durante o processamento da requisição
	```json
	{
	  "error": "Erro Inesperado"
	}
	```

* Status Code `404` -  Quando não encontrar a account conforme o `id` informado
	```json
	{
	  "error": "Conta não encontrada"
	}
	```

### Tecnologias utilizadas

*  [Java](https://www.java.com/) - A linguagem usada

* [4devs - Gerador de Pessoas](https://www.4devs.com.br/gerador_de_pessoas) - Mock utilizado para gerar dados válidos durante testes e documentação da API.

---
### Autor

*  **Josiel Sousa** - [josielsousa](https://github.com/josielsousa)
