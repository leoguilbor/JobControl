<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Exibe Detalhes do Cliente</title>
</head>
<body>
 <%@include file="../includes/menu.jsp" %>
<h2>Detalhes do Cliente Cadastrado</h2>
   <table>
    <tr>
        <td>Nome:</td>
        <td>${name}</td>
    </tr>  
    <tr>
        <td>Endereço:</td>
        <td>${address}</td>
    </tr>
    <tr>
        <td>Cidade:</td>
        <td>${city}</td>
    </tr>    
    <tr>
        <td>Telefone:</td>
        <td>${telephone}</td>
    </tr>


</table>  
<a href="client">Cadastro</a>
<a href="listClients">Lista</a>
 
</body>
</html>