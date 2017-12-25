<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>web sql main page</title>
</head>
<body>
    <h1>Hello, why it does`t work? test inf:"${test}"</h1>
      <c:forEach items="${commands}" var="item">
          <a href="<c:out value="${item}" />">  <c:out value="${item}" /> </a> <br />

      </c:forEach>


</body>
</html>