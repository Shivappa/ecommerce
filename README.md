This is the Ecommerce Product service

[Building API-2] Call to fake store product using restTempalte to get the single product

- created the models
- created the required dtos
- created the controller
- created the service later
- implemented restTemplate
- invoked call to 3rd party[FakeStoreAPI] to get the data
- Returned response from the controller

[Building API-3] CRUD APIs and Exception Handling

- Constructor Injection
- CRUD APIs: getProduct(id), getProducts(), createProduct()
- Updated the service layer for new APIs
- Wrote a small controller advice

[Building API-4]
- Calling 3rd party APIClient (fakeStoreClient)
- Repo -> Service -> Controller
- Client -> Service -> Controller

Configuration Details:
ddl.auto -> create, update, verify
Create : everytime you start the application, it will drop all the tables and recreate them. High chances of losing data.
Update : everyime you start the application, only the update changes are implemented.
Verify : it does not create/udpate anything, it just verifies if the DB has all the tables/columns/mappings as mentioned in the entities. -> Generally used in companies, for production

Local development -> create or update
Production -> verify [ generally tables via separate scripts like FlyWay, Liquibase ]


Fundamental APIs for auth are already given by Spring Security OAuth2
1. /login
2. /logout
3. /signup
4. /validate -> to validate the token from Resource Service to Auth Service
5. /register -> registering a new client [ basically the service which will rely on Auth Service for authentication 