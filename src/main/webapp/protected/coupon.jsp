<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<jsp:include page="../snippets/head.jsp">
    <jsp:param name="title" value="Coupon"/>
</jsp:include>
<body>
<h1>Coupon</h1>
<p>ID: ${coupon.id}</p>
<p>Name: ${coupon.name}</p>
<p>Percentage: ${coupon.percentage}%</p>
<p>Creator ID: ${coupon.creatorId}</p>
<p>Shops:
    <ul>
        <c:forEach var="shop" items="${couponShops}">
            <li>${shop.name}</li>
        </c:forEach>
    </ul>
</p>
<h2>Add to shops</h2>
<form action="coupon?id=${coupon.id}" method="post">
    <select name="shopIds" multiple>
        <c:forEach var="shop" items="${allShops}">
            <option value="<c:out value="${shop.id}"/>">${shop.id} - ${shop.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Add">
</form>
<jsp:include page="../snippets/show-info.jsp"/>
<jsp:include page="../snippets/show-error.jsp"/>
<jsp:include page="../snippets/to-profile.jsp"/>
<jsp:include page="../snippets/logout.jsp"/>
</body>
</html>
