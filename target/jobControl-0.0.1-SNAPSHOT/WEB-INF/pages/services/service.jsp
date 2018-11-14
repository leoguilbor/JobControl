<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Formulário de Cadastro de Cliente</h2>
<form:form method="POST" action="a/addClient" modelAttribute="service">
   <form:hidden name="id" path="id" />

	    <p>
	        <span><form:label path="name">Nome</form:label></span>
	        <span><form:input class="w3-input" name="name" path="name" /></span>
	    </p>   
	    <p>
	        <span><form:label path="description">Endereço:</form:label></span>
	        <span><form:textarea class="w3-input" name="description" path="description" /></td>
	    </p>
	    <p>
	        <span><form:label path="category">Data de Nascimento:</form:label></td>
	        <span><form:input class="w3-input" name="category" path="category" /></td>
	    </p>
	    <p>
	        <span><form:label path="term">Telefone:</form:label></td>
	        <span><form:textarea class="w3-input" name="term" path="term" /></td>
	    </p>
	
	    <p>
	    
        <span>
            <input type="button" value="Save" onclick='addOrEditEntity(document.getElementById("id").value, "Client");return false;'/>
        </span>
        <span>
            <input type="button" value="Cancel"  onclick='hideForm(document.getElementById("client"), "Client");return false;'/>
        <span>
    </p>
  
</form:form>
