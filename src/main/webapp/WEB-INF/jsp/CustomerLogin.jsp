<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
        function showAlert(message) {
            alert(message);
        }
    </script>
    <body>
        <h1> Login Page</h1>
        <p>Welcome</p>
        <form:form modelAttribute="customer" method="post" action = "home.htm">
        <table>
        <tr>
		    <td>Email ID:</td>
		    <td><form:input path="customerEmail" size="30" /> <font color="red"><form:errors path="customerEmail"/></font></td>
		</tr>
		<tr>
		    <td>Password:</td>
		    <td><form:input path="customerPassword" size="30" /> <font color="red"><form:errors path="customerPassword"/></font></td>
		</tr>
		
    	<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
        </table>
        </form:form>
        <form method="get" action="forgotPassword.htm">
           <button type = "submit">Forgot Password.</button>
        </form>
        <form method="get" action="signup.htm">
           <button type = "submit">New have an account?. Click Here</button>
        </form>
        <form method="get" action="flightAdmin.htm">
            <button type = "submit">Login as Admin</button>
         </form>

         <c:if test="${not empty requestScope.passwordAlertMessage}">
            <script>
                showAlert("${requestScope.passwordAlertMessage}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
        <c:if test="${not empty requestScope.userNameAlertMessage}">
            <script>
                showAlert("${requestScope.userNameAlertMessage}"); // Call JavaScript function to display the alert
            </script>
        </c:if>
    </body>
</html>
