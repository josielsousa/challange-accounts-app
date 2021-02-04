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
git clone https://github.com/josielsousa/challange-accounts-domain.git
git clone https://github.com/josielsousa/challange-accounts-app.git
cd challange-accounts-app
./runapp
```
  
### Rotas / Endpoints - `Accounts`


*  `/account` - `POST` - Rota utilizada para criação de uma nova `account`


	Exemplo de requisição : 
	```bash 
	curl --request POST -v \
	  --url http://localhost:8080/accounts \
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
	curl --request GET -v --url http://localhost:8080/accounts
	```
		
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

*  `/account/{id}/balance` - `GET` - Rota utilizada para recuperar o saldo de uma  `account`


	Exemplo de requisição : 
	```bash 
	curl --request GET -v \
	  --url http://localhost:8080/accounts/{id}/balance
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


---
  
### Rotas / Endpoints - `Transfers`

*  `/transfer` - `POST` - Realiza a transferência entre as `accounts`

	Exemplo de requisição : 
	```bash 
	curl --request POST -v \
	  --url http://localhost:8080/transfers \
	  --header 'content-type: application/json' \
	  --data '{
		"account_destination_id": "689b2629-f02a-412e-873f-cfaab344b413",
		"amount": 0.33
	}'
	```

	Payload de entrada:

	```json
	{
		"account_origin_id": "ab34cd90-f02a-412e-873f-cfaab344b413",
		"account_destination_id": "689b2629-f02a-412e-873f-cfaab344b413",
		"amount": 0.01
	}
	```
	| Atributo| Obrigatório | Descrição
	|--|--|--|
	| account_destination_id | SIM | Identificador universal UUID da conta de destino do `amount`
	| amount | SIM | Valor que será transferido da `account_origin_id` para a `account_destination_id` 


	Payload Retornos: 

*  Status Code `200` - Quando a transferência for bem sucedida.
	```json
	{
	  "data": {
	    "id": "ae4c2025-ac57-48ab-a5ed-b9266fe52314",
	    "account_origin_id": "41a721e6-16d3-45ae-8604-b6239fea31ae",
	    "account_destination_id": "689b2629-f02a-412e-873f-cfaab344b413",
	    "amount": 0.01,
	    "created_at": "2020-07-26T23:48:58.858125762Z"
	  },
	  "success": true
	}
	```

	| Atributo| Descrição
	|--|--|
	| data | Retorna os dados da transferência realizada
	| id | Identificador universal UUID da transferência realizada.
	| account_origin_id | Identificador universal UUID da conta de origem do `amount`
	| account_destination_id | Identificador universal UUID da conta de destino do `amount`
	| amount | Valor que foi transferido da `account_origin_id` para a `account_destination_id` 
	| created_at | Data de realização da transferência 
	| success | Boolean - Indica o status de sucesso para a requisição


* Status Code `404` -  Quando a `account` origem não for encontrada
	A conta de origem será recuperada do token informado.
	```json
	{
	  "error": "Conta de origem não encontrada"
	}
	```

* Status Code `404` -  Quando a `account` destino não for encontrada
	```json
	{
	  "error": "Conta de destino não encontrada"
	}
	```

* Status Code `422` -  Quando não houver saldo disponível suficiente na `account` de origem.
	```json
	{
	 "error": "Conta de origem sem saldo disponível"
	}
	```
---

*  `/transfers` - `GET` - Recupera todas as transferências realizadas para o usuário autenticado, a `account` será recuperar a através do `token` informado no header `Access-Token`

	Exemplo de requisição : 
	```bash 
	curl --request GET -v \
	  --url http://localhost:8080/transfers/{account_origin_id}, \
	  --header 'content-type: application/json'
	```


	Payload Retornos: 

*  Status Code `200` - Quando existir transferências a serem retornadas.
	```json
	{
	  "data": [
	    {
	      "id": "ae4c2025-ac57-48ab-a5ed-b9266fe52314",
	      "account_origin_id": "41a721e6-16d3-45ae-8604-b6239fea31ae",
	      "account_destination_id": "689b2629-f02a-412e-873f-cfaab344b413",
	      "amount": 0.01,
	      "created_at": "2020-07-26T23:48:58.858125762Z"
	    },
	    {
	      "id": "a854fd37-3770-4a64-b8d0-d47321ccd870",
	      "account_origin_id": "41a721e6-16d3-45ae-8604-b6239fea31ae",
	      "account_destination_id": "689b2629-f02a-412e-873f-cfaab344b413",
	      "amount": 0.01,
	      "created_at": "2020-07-26T23:57:31.34448352Z"
	    },
	    {
	      "id": "28080c5b-a8a5-4380-934a-a24afa79ecc2",
	      "account_origin_id": "41a721e6-16d3-45ae-8604-b6239fea31ae",
	      "account_destination_id": "689b2629-f02a-412e-873f-cfaab344b413",
	      "amount": 0.33,
	      "created_at": "2020-07-27T00:25:34.391882336Z"
	    }
	  ],
	  "success": true
	}
	```

	| Atributo| Descrição
	|--|--|
	| data | Retorna uma lista com todas as transferências realizadas
	| success | Boolean - Indica o status de sucesso para a requisição


### Tecnologias utilizadas

*  [Java](https://www.java.com/) - A linguagem usada

* [4devs - Gerador de Pessoas](https://www.4devs.com.br/gerador_de_pessoas) - Mock utilizado para gerar dados válidos durante testes e documentação da API.

---
### TODO
[] Adicionar Transação para controlar o fluxo de transferências;
[] Adicionar JWT auth;
[] Adicionar validação de dados de entrada;

---
### Autor

*  **Josiel Sousa** - [josielsousa](https://github.com/josielsousa)
