<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> SignUp Page</h1>
        <p>Welcome</p>
        <form:form modelAttribute="customer" method="post" action = "signUpLogin.htm">
        <table>
            <tr>
                <td>First Name :</td>
                <td><form:input path="customerFirstName" size="30" /> <font color="red"><form:errors path="customerFirstName"/></font></td>
            </tr>
            <tr>
                <td>Last Name :</td>
                <td><form:input path="customerLastName" size="30" /> <font color="red"><form:errors path="customerLastName"/></font></td>
            </tr>
            <tr>
                <td>Email Id :</td>
                <td><form:input path="customerEmail" size="30" /> <font color="red"><form:errors path="customerEmail"/></font></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><form:input path="customerPassword" size="30" /> <font color="red"><form:errors path="customerPassword"/></font></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Signup" /></td>
            </tr>
        </table>
        </form:form>
        <form method="get" action="login.htm">
           <button type = "submit">Already have an account?. Click Here</button>
        </form>
    </body>
</html>
