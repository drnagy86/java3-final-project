<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../include/header-homepage.jsp" />
<main class="flex-shrink-0 m-3" >

    <h1><fmt:message key="title.page" /></h1>
    <c:choose>
    <c:when test="${fn:length(contacts) == 1}">
        <p><fmt:message key="message.oneContact" /></p>
    </c:when>
    <c:otherwise>
    <p><fmt:message key="message.oneContact"><fmt:param value="${fn:length(contacts)}"/></fmt:message></p>
        </c:otherwise>
        </c:choose>

        <c:if test="${fn:length(contacts) > 0}">
        <c:forEach items="${contacts}" var="user">
    <p>
        <strong><fmt:message key="label.userId" />:</strong> ${user.userId}<br>
        <strong><fmt:message key="label.firstName" />:</strong> ${fn:escapeXml(user.username)}<br>
        <strong><fmt:message key="label.phoneNumber" />:</strong> <c:out value="${user.phoneNumber}" /><br>
        <c:if test="${user.birthday != null}">
            <strong><fmt:message key="label.birthday" />y:</strong> <fmt:formatDate value="${user.birthdayDate}" type="date" dateStyle="long" /><br>
        </c:if>
        <strong><fmt:message key="label.lastUpdated" />:</strong> <fmt:formatDate value="${user.lastUpdatedDate}" type="both" dateStyle="full" timeStyle="short" /><br>
        <strong><fmt:message key="label.balance" />:</strong> <fmt:formatNumber value="${user.balance}" type="currency" currencyCode="USD" /><br>
    </p>
    </c:forEach>
    </c:if>

<jsp:include page="../include/footer.jsp" />