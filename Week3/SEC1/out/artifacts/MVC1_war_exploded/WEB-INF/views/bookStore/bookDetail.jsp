<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a Car</title>
</head>
<body>
<form action="" method="post">
    <table>
        <tr>
            <td>Title:</td>
            <td><input type="text" name="title" value="${book.title}"/></td>
        </tr>
        <tr>
            <td>ISBN:</td>
            <td><input type="text" name="ISBN" value="${book.ISBN}"/></td>
        </tr>
        <tr>
            <td>Author:</td>
            <td><input type="text" name="author" value="${book.author}"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="text" name="price" value="${book.price}"/></td>
        </tr>
    </table>
    <c:choose>
        <c:when test="${book == null}">
            <input type="submit" value="Create"/>
        </c:when>
        <c:otherwise>
            <input type="submit" value="Update"/>
        </c:otherwise>
    </c:choose>

</form>

<c:if test="${book != null}">
    <form action="delete?bookId=${book.id}" method="post">
        <button type="submit">Delete</button>
    </form>
</c:if>

</body>
</html>