<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mevte
  Date: 28.06.2016
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Meal List</h1>

<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Manage</th>
    </tr>
    <c:forEach var="meal" items="${requestScope.get('mealList')}">
        <c:if test="${meal.isExceed()}">
            <tr style="color:red">
        </c:if>
        <c:if test="${false == meal.isExceed()}">
            <tr style="color:blue">
        </c:if>
        <td>${meal.getDateTime()}</td>
        <td>${meal.getDescription()}</td>
        <td>${meal.getCalories()}</td>
        <td>
            <a href="?action=edit&mealId=<c:out value="${meal.getId()}"/>">Update</a>
            <a href="?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a>
        </td>
    </tr>
    </c:forEach>

</table>
<p><a href="?action=insert">Add meal</a></p>
</body>
</html>
