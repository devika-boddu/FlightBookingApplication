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
<script>
    function showAlert(message) {
        alert(message);
    }
</script>
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
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input type="submit" value="S1" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input type="submit" value="S2" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input type="submit" value="S3" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input type="submit" value="S4" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input type="submit" value="S5" name="userSelectedOption"/>
        </div>
    </form>
    <form method="get" name="seatBookings" action="products.htm">
        <div class="seat">
            <input type="submit" value="S6" name="userSelectedOption"/>
        </div>
    </form>
    
    <p>Selected Seat: <c:out value="${sessionScope.seatsList}"/> <span id="selectedSeat"></span></p>
    <form method="get" action="products.htm" name="pay">
        <input type="submit" value="Pay" name="userSelectedOption"/>
    </form>
    <c:if test="${not empty requestScope.seatSelectedError}">
            <script>
                showAlert("${requestScope.seatSelectedError}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
    <!-- <script>
        // Add your JavaScript logic for seat selection here
        const seats = document.querySelectorAll('.seat');

        seats.forEach(seat => {
            seat.addEventListener('click', () => {
                if (seat.classList.contains('unavailable')) {
                    return;
                }
                seat.classList.toggle('selected');
                const selectedSeat = document.getElementById('selectedSeat');
                selectedSeat.textContent = '';
                const selectedSeats = document.querySelectorAll('.selected');
                if (selectedSeats.length > 0) {
                    selectedSeats.forEach(selected => {
                        selectedSeat.textContent += selected.textContent + ' ';
                    });
                } else {
                    selectedSeat.textContent = 'None';
                }
            });
        });
    </script> -->
</body>
</html>
