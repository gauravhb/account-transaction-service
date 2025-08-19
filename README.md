# account-transaction-service

## Endpoints

### 1. Create Account

**POST** `http://localhost:8080/account`

#### Sample Request Body
```json
{
  "customerId": "12345",
  "name": "Gaurav",
  "type": "savings",
  "balance": "1230"
}


#### Sample Response Body
```json

{
  "id": 1,
  "customerId": "12345",
  "name": "Gaurav",
  "type": "savings",
  "balance": 1230
}


