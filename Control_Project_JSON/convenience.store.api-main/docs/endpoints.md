# ENDPOINTS
This is the documentation for each endpoint inside of this api.
## Users
- Endpoint: /users
### Get
#### Get all users
- Query params: 
    - orderby: ["id", "username", "email"]
    - order: ["asc", "desc"]
- Status code: [200,401,403]
- Response: array of users

#### Get user by id
- Url: ```endpoint/{id}```
- Status code: [200,401,403]
- Response: json object with user information. 
### Post
#### Login
- Request: 
    ```json
    "email":"dummy@email.com",
    "password": "123456"
    ```
- Status code: [200, 403, 404]
- Response: json object with jwt token and user information.
#### Register
- Request: 
    ```json
    "username": "dummy",
    "email":"dummy@email.com",
    "password": "123456",
    "role": "ADMIN"
    ```
- Status code: [200, 401, 403]
- Response: json object with user information.

### Put
#### Edit user
- Url: ```endpoint/{id}```
- Request: 
    ```json
    "username": "dummy2",
    "email":"dummy2@email.com",
    ```
- Status code: [200, 401, 403]
- Response: json object with user information.
## Delete
- Url: ```endpoint/{id}```
- Status code: [200, 401, 403, 404]
- Response: json object with user information.

## Product Entities
- Endpoint: /products/entities
### Get
#### Get all product entities
- Query params:
    - orderby: ["id","name"]
    - order: ["asc", "desc"]
- Status code: [200,401,403]
- Response: array of product entities
#### Get product entity by id
- Url: ```endpoint/{id}```
- Status code: [200,403,404]
- Response: json object with product entity
### Post
#### Create new product entity
- Request: 
    ```json
    "name": "Milk"
    ```
- Status code: [200, 401, 403]
- Response: json object with product entity
### Put
- Url: ```endpoint/{id}```
- Request 
    ```json
    "name": "bread"
    ```
- Status code: [200, 403,404]
- Response: json object with product entity
## Delete
- Url: ```endpoint/{id}```
- Status code: [200,403,404]
- Response: json object with product entity

## Product
- Endpoint: /products
### Get
#### Get all products
- Query params:
    - orderby: ["id", "entityId", "sold"]
    - order: ["asc", "desc"]
    - sold: ["true", "false"]
- Status code: [200,401,403]
- Response: array of products
#### Get product by id
- Url: ```endpoint/{id}```
- Status code: [200,403,404]
- Response: json object with product
### Post
#### Create new Product
- Request
    ```json
    entityId: 1
    ```
- Status code: [200,403,404]
- Response: json object with product
## Delete
- Url: ```endpoint/{id}```
- Status code: [200,403,404]
- Response: json object with product

## Transactions
- Endpoint: /transaction
### Get
### Get all transactions
- Query params:
    - order: ["asc", "desc"]
- Status code: [200,401,403]
- Response: array of transactions
#### Get transaction by id
- Url: ```endpoint/{id}```
- Status code: [200,403,404]
- Response: json object with transaction
### Post
#### Create new transaction
- Request
    ```json
    "cpf": "111.111.111-5",
    "productId": 1
    ```
- Status code: [200,403,404]
- Response: json object with transaction

### Delete
- Url: ```endpoint/{id}```
- Status code: [200,403,404]
- Response: json object with transaction
