<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hello World</title>
</head>
<body>
 <c:if test="${sessionScope.login == null}">
    <c:redirect url="/login.html"></c:redirect>
</c:if>
Hello World!!!
</body>
</html>