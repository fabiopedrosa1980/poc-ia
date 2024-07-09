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


## Enpoints
POST localhost:8080/orders 
{
    "lineItems" :[
        {
        "product": 100,
        "quantity": 2
        }   
    ]
    
}

GET localhost:8080/orders

## OpenAI
Crie a chavd e use na variavel de ambiente -DOPEN_AI=suachave 
https://platform.openai.com/

GET localhost:8080/story

