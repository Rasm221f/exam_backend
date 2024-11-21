Get all items:
GET http://localhost:7070/api/items

HTTP/1.1 200 OK
Date: Thu, 21 Nov 2024 10:44:31 GMT
Content-Type: application/json
Content-Length: 280

[
{
"id": 2,
"name": "VR goggles",
"purchasePrice": 1500.0,
"category": "VR",
"acquisitionDate": 1732185514000,
"description": "\"New VR goggles\""
},
{
"id": 1,
"name": "Tape recorder",
"purchasePrice": 899.99,
"category": "VIDEO",
"acquisitionDate": 1732185455000,
"description": "\"Old tape recorder\""
}
]

Get by id:
GET http://localhost:7070/api/items/2

HTTP/1.1 200 OK
Date: Thu, 21 Nov 2024 10:39:50 GMT
Content-Type: application/json
Content-Length: 134

{
"id": 2,
"name": "VR goggles",
"purchasePrice": 1500.0,
"category": "VR",
"acquisitionDate": 1732185514000,
"description": "\"New VR goggles\""
}

Post:
HTTP/1.1 201 Created
Date: Thu, 21 Nov 2024 11:04:49 GMT
Content-Type: application/json
Content-Length: 122

{
"id": 6,
"name": "headset",
"purchasePrice": 299.0,
"category": "SOUND",
"acquisitionDate": 1732185455000,
"description": "headset"
}
Response file saved.

Put:
HTTP/1.1 200 OK
Date: Thu, 21 Nov 2024 11:06:00 GMT
Content-Type: application/json
Content-Length: 122

{
"id": 3,
"name": "headset",
"purchasePrice": 299.0,
"category": "SOUND",
"acquisitionDate": 1633989600000,
"description": "headset"
}
DELETE:
DELETE http://localhost:7070/api/items/3

HTTP/1.1 204 No Content
Date: Thu, 21 Nov 2024 11:06:52 GMT
Content-Type: text/plain

<Response body is empty>



GET by category:
[
{
"id": 1,
"name": "Laptop",
"purchasePrice": 999.99,
"category": "VIDEO",
"acquisitionDate": 1658095200000,
"description": "High-performance laptop"
},
{
"id": 2,
"name": "Book",
"purchasePrice": 29.99,
"category": "VIDEO",
"acquisitionDate": 1674601200000,
"description": "Fiction novel"
}
]