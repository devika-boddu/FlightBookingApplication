<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<html>
<head>
    <title>Payment</title>
    <style>
        /* Add your CSS styles for seat selection page here */
        
    </style>
</head>
<body>
    <p>Selected Seat: <c:out value="${sessionScope.seatsList}"/> <span id="selectedSeat"></span></p>
    <h1>PaymentSuccessful</h1>
    </body>
</html>
