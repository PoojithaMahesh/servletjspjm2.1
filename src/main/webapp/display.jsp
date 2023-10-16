<%@page import="studentwithjspm2.dto.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%List<Student> list=(List)request.getAttribute("list"); %>
<table border="2px">
<tr>
<th>Id</th>
<th>Name</th>
<th>Email</th>
<th>Password</th>
<th>Address</th>
<th>PhoneNumber</th>
<th>Fees</th>
<th>Delete</th>
<th>Update</th>
</tr>

<%for(Student student:list){ %>
<tr>
<td><%=student.getId() %></td>
<td><%=student.getName() %></td>
<td><%=student.getEmail() %></td>
<td><%=student.getPassword() %></td>
<td><%=student.getAddress() %></td>

<td><%=student.getPhone() %></td>
<td><%=student.getFees() %></td>


<td><a href="delete?id=<%=student.getId() %>">delete</a></td>
<td><a href="update?id=<%=student.getId() %>">update</a></td>
</tr>
<%} %>

</table>

</body>
</html>