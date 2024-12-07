# Assignment 14 for Spring Boot lesson

This folder is based on **assignment 13**.

The test cases for **assignment 14** are in the **test** folders.

Below are examples of the endpoints:
## POST 

Create one cart.

`POST localhost:8080/cart/save`

```
# Request body
{
  "cartModelId": 4,
  "cartModelName": "wonderful",
  "cartModelPrice": 5.0,
  "cartModelShortDesc": "blue sky",
  "cartModelQuantity": 3000  
}

# Response
{
  "status": "Successful",
  "message": "A new cart is created successfully."
}
```

## GET one cart

Get one cart.

`GET localhost:8080/cart/3`

```
{
  "status": "Successful",
  "data": {
    "cartModelId": 3,
    "cartModelName": "Shape-changing container set",
    "cartModelPrice": 85.0,
    "cartModelShortDesc": "Mix and match your containers to fit your food content.",
    "cartModelQuantity": 150
  }
}
```

## GET all

Get all carts.

`GET localhost:8080/cart/all`

```
{
  "status": "Successful",
  "data": [
    {
      "cartModelId": 1,
      "cartModelName": "Super useful cutting set",
      "cartModelPrice": 45.0,
      "cartModelShortDesc": "A collection of useful vegetables cutting tools.",
      "cartModelQuantity": 100
    },
    {
      "cartModelId": 2,
      "cartModelName": "Multi-purpose cooking oven",
      "cartModelPrice": 140.0,
      "cartModelShortDesc": "Oven for baking, steaming, microwaving and grilling.",
      "cartModelQuantity": 170
    },
    {
      "cartModelId": 3,
      "cartModelName": "Shape-changing container set",
      "cartModelPrice": 85.0,
      "cartModelShortDesc": "Mix and match your containers to fit your food content.",
      "cartModelQuantity": 150
    }
  ]
}

```

## GET Search catalogue by name

`GET localhost:8080/cart/search?catalogueName=super`

```
{
  "status": "Successful",
  "data": [
    {
      "cartModelId": 1,
      "cartModelName": "Super useful cutting set",
      "cartModelPrice": 45.0,
      "cartModelShortDesc": "A collection of useful vegetables cutting tools.",
      "cartModelQuantity": 100
    }
  ]
}
```