# cts-training

gateway api url
http://localhost:8075/FlightBookingServices/booking/SearchFlight

token generator
http://localhost:8075/authenticate
{
    "username":"raja.juluru@gmail.com",
    "password":"abcdefg"
}

--------------------------------------------------------------------------------------------------------
register end point
user register
http://localhost:9090/user/register

http://localhost:8075/user/register

roles are changing while saving 'U'

input data
{
"firstName":"juluru",
"lastName":"kumar raja",
"emailid":"raja.juluru@gmail.com",
"address":"mylavaram",
"password":"abcdefg",
"mobilenumber":9999999999
}

--------------------------------------------------------------------------------------------
post methods
http://localhost:9090/Admin/AddAirlines
http://localhost:8075/FlightBookingServices/Admin/AddAirlines/AIRLINE3
http://localhost:8075/FlightBookingServices/Admin/updateAirlines/AIRLINE3
get methods
http://localhost:8075/FlightBookingServices/Admin/deleteAirlines/AIRLINE3
add airlines

{
	"airLineName":"indigo",
	"airLineDescription":"indigo air lines forum address in ap",
    "lockStatus":0,
    "date":"2021-08-31"
}
---------------------------------------------------------------------------------------------
http://localhost:9090/Admin/AddFlights
add flights

api gateway
http://localhost:8075/FlightBookingServices/Admin/AddFlights
http://localhost:8075/FlightBookingServices/Admin/updateFlights/FLIGHT13
http://localhost:8075/FlightBookingServices/Admin/deleteFlights/FLIGHT13
http://localhost:8075/FlightBookingServices/Admin/findAllFlights
{
    
	"fromLocation":"vij",
	"toLocation":"hyd",
	"seatsCount":10,
	"remarks":"ntf",
	"lockstatus":0
	,"date":"2021-08-31",
	
	"price":9999,
	
	"airLineId":"AIRLINE1"
}


--------------------------------------------------------------------------------------------------
schedule flight date and time

http://localhost:9090/Admin/SchduleFlights
http://localhost:8075/FlightBookingServices/Admin/SchduleFlights
http://localhost:8075/FlightBookingServices/Admin/CancelSchduleFlight/SCHFLIGHT13

{
	"flightId":"FLIGHT10",
	"journeyDate":"2021-08-01",
	"onBoradingTime":"15:07:00",
	"departureTime":"16:07:00",
    "bookingStartDate":"2021-08-01",
    "bookingEndDate":"2021-08-01"
}
-----------------------------------------------------------------------------------------------------
user Login
http://localhost:9090/user/login

{
    "emailid":"raja",
    "password":"shgfh"
}


using microservice userlogin
http://localhost:8070/User/Login


{
    "emailid":"raja.juluru@gmail.com",
    "password":"abcdefg"
}


------------------------------------------------------------------------------------------------------
//swagger ui
http://localhost:9090/swagger-ui.html#/
------------------------------------------------------------------------------------------------------
search flight
http://localhost:9090/booking/SearchFlight
api gateway url
http://localhost:8075/FlightBookingServices/booking/SearchFlight


{
    "source":"vijayawada",
	"destination":"hyderabad",
	"journey":"2021-08-30",
	"noOfTickets":3
}

----------------------------------------------------------------------------------------------------
http://localhost:9090/booking/doBooking
http://localhost:8075/FlightBookingServices/booking/doBooking

do booking


{

    "scheduleflightId":"SCHFLIGHT15",
	"seatnos":2,
    "flightId":"FLIGHT11",
    "userid":"raja.juluru@gmail.com",
    "detailsOfPassenger":[
        {
            "name":"raja",
             "age":22,
	          "gender":"male"
        },{
            "name":"juluru",
             "age":10,
	          "gender":"femalie"
        }
    ]
}

-----------------------------------------------------------------------------------------------------

ticket detials
http://localhost:9090/booking/pnr/PNR20
-----------------------------------------------------------------------------------------------------
cancelling all tickets by booking id is
http://localhost:9090/booking/ticketsCancelByBookingId/{bid}
------------------------------------------------------------------------------------------------------

cancel using pnr number
 http://localhost:9090/booking/pnrcancel/{pnrnumber}
---------------------------------------------------------------------------------------------------------

creating coupon by admin
http://localhost:8075/FlightBookingServices/Admin/AddDiscountCoupon

{
    	"couponcode":"CF20",

	"couponDiscount":20
}

---------------------------------------------------------------------------------------------------------
get discount for coupons 

http://localhost:8075/FlightBookingServices/Admin/getDiscount/CF20

----------------------------------------------------------------------------------------------------------
http://localhost:8075/FlightBookingServices/booking/getHistory/raja.juluru@gmail.com
--------------------------------------------------------------------------------------------------------
http://localhost:8075/FlightBookingServices/booking/getBookingIdDetails/BMASTER12
