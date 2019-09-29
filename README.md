# goneat
Application for a food app for looking up restaurants and make bookings



=======================================================================
DATABASE SETUP

    A mysql db  is required. Following details should be set up in application.properties file for same
    spring.datasource.username=
    spring.datasource.password=
    spring.datasource.url=
=======================================================================





========================================================================
Service Details



1. A user need sign up first to use the app using following request
    POST Method
      {{BaseUrl}}/user
    {
    	"firstName":"FNAME",
    	"lastName":"LNAME",
    	"email":"FLNAME@gmail.com",
    	"password":"##PASSWORD##"
    }
    A unique userId would be generated from above request for user
    Response -
    {
        "userId": "2yoLet4cLK9uadUpUG04fr6lWYqZvE",
        "firstName": "FNAME",
        "lastName": "LNAME",
        "email": "FLNAME@gmail.com"
    }
    The encrypted password would be saved for user




 2. To access any service a authentication token is required which can be generated using following request
    POST Method
      {{BaseUrl}}/login {
    	"email":"gunjan2@gmail.com",
    	"password":"Gunjan"
    }
    This would return a Authorization token in Response header "Authentication" in format Bearer@@token@@
    This header has to be provided in every request with value of token and prefix separated by space
    ex - Bearer @@token@@
    The Authorization token would be valid for 10 days(The expiration time can be set from application properties)
    The userId is also returned in the headers


3. User Service
    a) Get user by user id
        GET Method
          {{baseurl}}/user/{{userid}}

          -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \

    b) Get all users
        GET Method
          {{baseurl}}/user?page=1&limit=10

          -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \
        The results are paginated. If no value is provided for page and limit then by default 1st page with first 25 results will be shown.


4. City Services
    a) City Creation
     A city can be created using following request
        POST Method
            {{BaseUrl}}/city

            -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \
            Bangalore
          Request Body should be string and should contain only city name

     b) Get all cities
        GET Method
         {{BaseUrl}}/city
          -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \

     c) Get city by city Id
        GET Method
          {{BaseUrl}}/city/{{cityid}}
           -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \




5. Restaurant Services
    a) Restaurant Creation
    A restaurant can be created using following request with valid city id
    POST Method
      {{BaseUrl}}/restaurants

      -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \
      {
    	"cityId":1,
    	"restaurantName":"abcdef"
    }

    b)Get restaurant by restaurant Id
        GET Method
        {{BaseUrl}}/restaurants/{{restid}}
         -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \
    c) Search Restaurants
        Restaurants can be searched within a city with following request
        GET Request
          {{BaseUrl}}/restaurants?cityId=1&keyword=city&page=0&limit=2

          -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \

         ## Authorization Header needs to be provided
         When no search keyword is provided then all the results wold be displayed
         The results are paginated. If no value is provided for page and limit then by default 1st page with first 25 results will be shown.


6. Marking a restaurant favourite
    A restaurant can be marked favourite using following request
    POST Method
      {{BaseUrl}}/restaurants/favourite/userId/F37${userId}

      -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \
      {
    	"restaurantIds":1
     }
     if a restaurant is marked as favourite again a message showing the same would returned


7. Booking in a restaurant
    A user can book a slot (1 slot in 1 hour) for duration of 1 hour for a mximum number of guests 20(customizable) using following request
    POST Method
      '{{BaseUrl}}/restaurants/booking?restId=1

      -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndW5qYW4yQGdtYWlsLmNvbSIsImV4cCI6MTU3MDYwMTM3MH0.uzl_MYSQHmyyKQ1MgoOlJNnyUd0wPSWVlDin4OgRzZ1u_v7Ae94A8-OPwVgZGHFfjTP25TETQIeGWAUFVt7gSg' \
      {
    	"userId":"F37Lqr2nPe4UAVIz5yJOic85418UTe",
    	"date":"2019-09-30", //yyyy-mm-dd
    	"time":"20:00",         //HH:mm
    	"numberOfGuests":"20"
    }


==================================================================================================