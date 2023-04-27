<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<html>
<head>
    <title>Seat Selection</title>
    <style>
        /* Add your CSS styles for seat selection page here */
        .seat-map {
            display: grid;
            grid-template-columns: repeat(5, 1fr); /* Adjust as needed */
            gap: 10px; /* Adjust as needed */
        }
        .seat {
            width: 50px; /* Adjust as needed */
            height: 50px; /* Adjust as needed */
            text-align: center;
            line-height: 50px; /* Adjust as needed */
            font-weight: bold;
            background-color: lightgray;
            cursor: pointer;
        }
        .seat.selected {
            background-color: green;
        }
        .seat.unavailable {
            background-color: red;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    
    <div>
        <label for="seats">Number of seats:</label>
        <select name="seats" id="seats">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
    </div>
   <!-- < % 
    List<String> mySessionVar = (String) session.getAttribute("getSeats"); 
  %> -->
    
    <!-- <c:out value = "${sessionScope.seats}"/> -->
    <p>Get seats---</p>



    <form method="post" name="seatBookings" action="products.htm">
        <div class="seat">
            <input class="seatId" id ="S1" type="submit" value="S1" name="userSelectedOption"/>
        </div>
    </form>
    <form method="post" name="seatBookings" action="products.htm">
        <div class="seat">
            <input class="seatId" id ="S2" type="submit" value="S2" name="userSelectedOption"/>
        </div>
    </form>
    <form method="post" name="seatBookings" action="products.htm">
        <div class="seat">
            <input class="seatId" id ="S3" type="submit" value="S3" name="userSelectedOption"/>
        </div>
    </form>
    <form method="post" name="seatBookings" action="products.htm">
        <div class="seat">
            <input class="seatId" id ="S4" type="submit" value="S4" name="userSelectedOption"/>
        </div>
    </form>
    <form method="post" name="seatBookings" action="products.htm">
        <div class="seat">
            <input class="seatId" id ="S5" type="submit" value="S5" name="userSelectedOption"/>
        </div>
    </form>
    <form method="post" name="seatBookings" action="products.htm">
        <div class="seat">
            <input class="seatId" id ="S6" type="submit" value="S6" name="userSelectedOption"/>
        </div>
    </form>
    <form method="post" name="checkout" action="payment">
        <input type="submit" value="Checkout" name="Checkout"/>
    </form>

    <script>
        var seats = "${sessionScope.seats}"
        console.log(seats);
        seatIds = document.getElementsByClassName("seatId")
        for(let i=0; i<seatIds.length;i++){
           if(seats.includes(seatIds[i].id)){
                seatIds[i].setAttribute("disabled","")}
        }
    </script>
</body>
</html>
