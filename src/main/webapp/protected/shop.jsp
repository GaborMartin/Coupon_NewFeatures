<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<jsp:include page="../snippets/head.jsp">
    <jsp:param name="title" value="Shop"/>
</jsp:include>
<body>
<h1>Shop</h1>
<c:if test="${empty error}">
    <p>ID: ${shop.id}</p>
    <p>Name: ${shop.name}</p>
    <p>Creator ID: ${shop.creatorId}
</c:if>
<h2>My Coupons for this shop:</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Percentage (%)</th>
        <th>Creator ID</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty error}">
    <c:forEach var="coupon" items="${coupons}">
        <tr>
            <td>${coupon.id}</td>
            <td><a href="coupon?id=<c:out value="${coupon.id}"/>">${coupon.name}</a></td>
            <td>${coupon.percentage}</td>
            <td>${coupon.creatorId}</td>
        </tr>
    </c:forEach>
    </c:if>
    </tbody>
</table>
<jsp:include page="../snippets/show-error.jsp"/>
<jsp:include page="../snippets/to-profile.jsp"/>
<jsp:include page="../snippets/logout.jsp"/>
</body>
</html>
