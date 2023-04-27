<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Admin Dashboard</title>
        <style>
            body {
                overflow: hidden;
            }
            .container{
                height: 900px;
                width: 50%;
            }
            .create-container{
                margin: 70%;
                height: 900px;
                
            }
            .update-container{
                margin: 70%;
                height: 900px;
            }
            .delete-container{
                margin: 70%;
                height: 900px;
            }
            .show-container{
                margin: 70%;
                height: 900px;
            }
            .div-left{
                float:left;
                padding :45px;
                 position: fixed;
                 height: 900px;
                 border-right: 2px solid black;

            }
            .div-right{
                float:right;
                padding-right:10px;
                height: 900px;
            }
            .getalldetails{
                width:100%;
                height: 900px;
            }
            
        </style>
    </head>

    <body>
        <div class="container">
            <div class="div-left">
                <ul>
                    <li><a href="#create"> Create </a></li>                        
                    <li><a href="#update">Update </a></li>
                    <li><a href="#delete">Delete </a></li>
                    <li><a href="#show">Get All Flights </a></li>
                
                </ul>
            </div>

            <div class="div-right">
                <div id="create" class="create-container">
                    <form method = "post" action =  "dashboard.htm">
                        <label>Flight Name:</label>
                        <input type = "text" name = "flightName"/> <br/>
                        <label>Flight Source:</label> 
                        <input type = "text" name = "flightSource"/> <br/>
                        <label>Flight Destination:</label> 
                        <input type = "text" name = "flightDestination"/> <br/>
                        <label>Flight Description:</label> 
                        <input type = "text" name = "flightDescription"/> <br/>
                        <label>Flight Price:</label> 
                        <input type = "text" name = "flightPrice"/> <br/>
                        <label>Flight Image URL:</label> 
                        <input type = "text" name = "flightImageURL"/> <br/>
                        <input type = "submit" value = "Add a New Flight" name = "buttonClicked"/>
                    </form>
                </div>

                <div id="update" class="update-container">
                    <form method = "post" action =  "dashboard.htm"> 
                        <div id="get" class="get-container">
                            <form method = "post" action =  "dashboard.htm"> 
                                <label>Enter Flight ID:</label>
                                <input type = "text" name = "flightId"/> <br/>
                                <input type = "submit" value = "Get Flights" name = "buttonClicked" id ="get" />
                                
                                    <div class="Card">
                                        <!-- <label>Flight ID:</label> -->
                                        <input type = "text" value = "${sessionScope.fId}" name = "fId"/> <br/>
                                        <label>Flight Name:</label>
                                        <input type = "text" value = "${sessionScope.getProduct.flightName}" name = "flightName"/> <br/>
                                        <label>Flight Source:</label>
                                        <input type = "text" value = "${sessionScope.getProduct.source}" name = "flightSource"/> <br/>
                                        <label>Flight Destination:</label>
                                        <input type = "text" value = "${sessionScope.getProduct.destination}" name = "flightDestination"/> <br/>
                                        <label>Flight Price:</label>
                                        <input type = "text" value = "${sessionScope.getProduct.flightDescription}" name = "flightDescription"/> <br/>
                                        <label>Flight Image URL:</label>
                                        <input type = "text" value = "${sessionScope.getProduct.flightPrice}" name = "flightPrice"/> <br/>
                                        <label>Flight Image URL:</label>
                                        <input type = "text" value = "${sessionScope.getProduct.flightImage}" name = "flightImageURL"/> <br/>
                                    </div>
                                <input type = "submit" value = "Update Flight" name = "buttonClicked" id="update"/>
                            </form>
                        </div>
                        
                    </form>  
                </div>

                <div id="delete" class="delete-container">
                    <form method = "post" action =  "dashboard.htm"> 
                        <label>Enter Flight ID:</label>
                        <input type = "text" name = "flightId"/>

                        <input type = "submit" value = "Delete Flight" name = "buttonClicked" id="delete"/>
                    
                    </form>
                </div>

                <div id="show" class="show-container">
                    <form method = "post" action =  "dashboard.htm"> 
                        <div class="getalldetails">
                        <input type = "submit" value = "Display all Flights" name = "buttonClicked"/>
                        <table>
                            <tr>
                                <th>Flight ID</th>
                                <th>Flight Name</th>
                                <th>Flight Source</th>
                                <th>Flight Destination</th>
                                <th>Flight Description</th>
                                <th>Flight Price</th>
                                <th>Flight Image URL</th>
                            </tr>
                            <c:forEach var="flights" items="${sessionScope.allPackages}">
                                <tr>
                                    <td>${flights.flightId}</td>
                                    <td>${flights.flightName}</td>
                                    <td>${flights.source}</td>
                                    <td>${flights.destination}</td>
                                    <td>${flights.flightDescription}</td>
                                    <td>${flights.flightPrice}</td>
                                    <td>${flights.flightImage}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>