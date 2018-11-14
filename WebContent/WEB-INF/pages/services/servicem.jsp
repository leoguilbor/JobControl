<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Formulário de Cadastro de Cliente</h2>
<form:form method="POST" action="a/addServicem" modelAttribute="servicem">
   <form:hidden name="id" path="id" />

	    <p>
	        <span><form:label path="name">Nome</form:label></span>
	        <span><form:input class="w3-input" name="name" path="name" /></span>
	    </p>   
	    <p>
	        <span><form:label path="description">Descrição:</form:label></span>
	        <span><form:textarea class="w3-input" name="description" path="description" /></td>
	    </p>
	    <p>
	        <span><form:label path="category">Categoria:</form:label></td>
        <span><input type="text" class="w3-input" id="categorySearch" name="category.name" onkeyup="searchEntity('Category','name',document.getElementById('categorySearch').value);"/><form:hidden path="category.id"/></td>
        <div id="category_resultSearch" class="searchBox"> </div>
	    </p>
	    <p>
	        <span><form:label path="term">Prazo:</form:label></td>
	        <span><form:textarea class="w3-input" name="term" path="term" /></td>
	    </p>
	
	    <p>
	    
        <span>
            <input type="button" value="Save" onclick='addOrEditEntity(document.getElementById("id").value, "Servicem");return false;'/>
        </span>
        <span>
            <input type="button" value="Cancel"  onclick='hideForm(document.getElementById("servicem"), "Servicem");return false;'/>
        <span>
    </p>
  
</form:form>
