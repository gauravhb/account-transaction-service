# account-transaction-service

Endpoints
1. POST : http://localhost:8080/account
Sample Request Body :
{
    "customerId": "12345",
    "name": "Gaurav",
    "type": "savings",
    "balance": "1230"
}

Sample Response :
{
    "id": 1,
    "customerId": "12345",
    "name": "Gaurav",
    "type": "savings",
    "balance": 1230
}

2. GET http://localhost:8080/account/balance/1

Sample Response :
{
    "accountNumber": 1,
    "accountBalance": 1230.00
}
