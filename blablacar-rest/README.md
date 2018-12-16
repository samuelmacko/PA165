# REST api documentation:
Rest api offers a functionality to list and manipulate a Drive entity.


## Exposed methods:

### list all drives
```shell
curl --request GET --url http://localhost:8080/blablacar-rest/drives
```

### get a single drive
```shell
curl --request GET --url http://localhost:8080/blablacar-rest/drives/1
```

### create a new drive
```shell
curl --request POST --url http://localhost:8080/blablacar-rest/drives/create \
  --header 'content-type: application/json' \
  --data '{
	"driverId": 1,
	"toCityId": 1,
	"fromCityId": 2,
	"price": 100.00,
	"capacity": 4
	"date": "16-12-2018 22:46",
}'
```

##### delete a drive
```shell
curl --request DELETE --url http://localhost:8080/blablacar-rest/drives/1
```

##### add a customer
```shell
curl --request POST --url http://localhost:8080/blablacar-rest/drives/add-customer \
  --header 'content-type: application/json' \
  --data '{
	"driveId": 1,
	"customerId": 1
}'
```

##### remove a customer
```shell
curl --request POST --url http://localhost:8080/blablacar-rest/drives/remove-customer \
  --header 'content-type: application/json' \
  --data '{
	"driveId": 1,
	"customerId": 1
}'
```

##### edit a capacity
```shell
curl --request POST --url http://localhost:8080/blablacar-rest/drives/edit-capacity \
  --header 'content-type: application/json' \
  --data '{
	"driveId": 1,
	"capacity": 4
}'
```

##### edit a driver
```shell
curl --request POST --url http://localhost:8080/blablacar-rest/drives/edit-driver \
  --header 'content-type: application/json' \
  --data '{
	"driveId": 1,
	"driverId": 1
}'
```
