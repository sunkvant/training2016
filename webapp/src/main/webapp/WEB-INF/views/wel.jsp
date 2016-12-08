<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" action="/main">
   <table>
    <tr>
        <td><form:label path="cityName">City name</form:label></td>
        <td><form:input path="cityName" /></td>
    </tr>
   	<tr>
        <td><form:label path="countryId">Country id</form:label></td>
        <td><form:input path="countryId" /></td>
    </tr>

    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>