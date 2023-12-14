# Measure Rest Application
Measurements from the sensor - rest api application with spring boot, spring data jpa, possibility of paging display, with sort by fields and sort order. Entity constraint validation and existing in database check - with return errors to client. Using Spring 5, PostgreSQL 11<br/><br/>
Get all measurements (list):</br>
![get list of measurments](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/2b02b85a-056d-4323-8dbc-381e755da5aa)
<br/><br/>
Get all measurements with page with size and sort with direction:
<br/><br/>
![pageAndSortWithDirection copy](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/362c5347-061e-407f-92a5-721110d958b7)
<br/><br/>
Add measurment - entity constraint validation:
<br/><br/>
![measureEntityConstraintValidation](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/f872070f-5c36-434d-bc47-6e9dff07397b)
<br/><br/>
Add measure - no errors:
<br/><br/>
![post_measure_right](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/0aec9790-55f4-4936-a400-05c6652a6734)
<br/><br/>
Add measure - sensor with given name not exists:
<br/><br/>
![post_measure_error](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/17b8047e-78dc-48f8-962f-c9ad6da23466)
<br/><br/>
Update measure - no errors:
<br/><br/>
![update measure right](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/3206ba4f-0a35-4e2e-bf03-5a96cf4ceefd)
<br/><br/>
Update measure - validation error(no sensor):
<br/><br/>
![update measure validate error](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/754b4181-a029-416d-8a4f-70463a03ff57)
<br/><br/>
Sensors list:
<br/><br/>
![sensors list](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/84a38480-1874-4f86-abfc-f1afc4db7d1a)
<br/><br/>
Add sensor - field validation:
<br/><br/>
![sensor field validator](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/9fd12954-b874-49e5-9544-5a629c661160)
<br/><br/>
Add sensor - a device with given name already exists in the database:
<br/><br/>
![sensor already exists](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/226cd180-4203-4c14-b6da-2b0acdd3d936)
<br/><br/>
Get rainy days count:
<br/><br/>
![get rainy days count](https://github.com/DmitryChelogaev/measureRestApp/assets/91143076/67ddb569-e154-470a-baa3-435fcfca8b38)

