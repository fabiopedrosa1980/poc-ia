### POC OpenAI com Modulith

## Stack 
 - Java 21
 - Spring Boot 3.3
 - OpenAI
 - Modulith
 - Postgres
 - Actuator
 - Docker
 - Virtual Threads

## Endpoints
POST localhost:8080/orders 
```
{
    "lineItems" :[
        {
        "product": 100,
        "quantity": 2
        }   
    ]
    
}
```
GET localhost:8080/orders

## OpenAI
 - Crie a chave: https://platform.openai.com/  
 - Use na variavel de ambiente: ${OPEN_AI}=suachave

## Chamano o chat OpenAI
GET localhost:8080/story

## Acessando os Modulos 
 - http://localhost:8080/actuator/modulith 