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
   < % 
    List<String> mySessionVar = (String) session.getAttribute("getSeats"); 
  %>
    <c:out value = "{$sessionScope.getSeats}"></c:out>
    
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input id ="inputId1" type="submit" value="S1" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input id ="inputId2" type="submit" value="S2" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input id ="inputId3" type="submit" value="S3" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input id ="inputId4" type="submit" value="S4" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input id ="inputId5" type="submit" value="S5" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input id ="inputId6" type="submit" value="S6" name="userSelectedOption"/>
        </div>
    </form>
    <script>
        console.log("Hi");
    
        var inputs = document.getElementsByTagName("input");
    
        var inputValues = [];
        for (var i = 0; i < inputs.length; i++) {
        inputValues.push(inputs[i].value);
      }
        console.log(inputValues);

        var input1 = document.getElementById("inputId1");
        var input2 = document.getElementById("inputId2");
        var input3 = document.getElementById("inputId3");
        var input4 = document.getElementById("inputId4");
        var input5 = document.getElementById("inputId5");
        var input6 = document.getElementById("inputId6");
        var mySessionVar[] = '<%= session.getAttribute("mySessionVar") %>'

        
    </script>
    
</body>
</html>
