<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h2>Formulário de Cadastro de Cliente</h2>
<form:form method="POST" action="a/addJob" commandName="job">
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
        <span><form:label path="servicem.id">Serviço:</form:label></td>
        <span><input type="text" class="w3-input" id="servicemSearch" name="servicem.name" onkeyup="searchEntity('Servicem','name',document.getElementById('servicemSearch').value);"/><form:hidden path="servicem.id"/></td>
        <div id="servicem_resultSearch" class="searchBox"> </div>
    </p>
    
    <p>
        <span><form:label path="client.id">Cliente:</form:label></td>
        <span><input type="text" class="w3-input" id="clientSearch" name="client.name" onkeyup="searchEntity('Client','name',document.getElementById('clientSearch').value);"/><form:hidden path="client.id"/></td>
        <div id="client_resultSearch"  class="searchBox"> </div>
    </p>
    
    <p>
        <span><form:label path="date">Data:</form:label></td>
        <span><form:input class="w3-input" name="date" path="date" /></td>
        
    </p>
    
    <p>
        <span>
            <input type="button" value="Save" onclick='addOrEditEntity(document.getElementById("id").value, "Job");return false;'/>

        </span>
        
        <span>
            <input type="button" value="Cancel"  onclick='hideForm(document.getElementById("job"), "Job");return false;'/>
        <span>
    </p>
  
</form:form>
