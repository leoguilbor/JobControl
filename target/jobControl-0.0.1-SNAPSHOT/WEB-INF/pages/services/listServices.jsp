<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<script type="text/javascript" src="rs/service.js"></script>   
	<link rel="stylesheet" href="rs/basicStyle.css" >
	<link rel="stylesheet" href="rs/w3.css" >
	 
    <title>Lista de serviços Cadastrados</title>
</head>
<body onload='listEntity("Service");'>
<%@include file="../includes/header.jsp" %>
<%@include file="../includes/menu.jsp" %>

<div id="AddOrEdit" style="display:none; position:absolute; top:15%; left:15%; min-width:70%; padding:10px 10px 10px 10px" class="w3-white w3-card-4">
	<%@include file="service.jsp" %>
</div>


    <h2>Lista de serviços Cadastrados</h2>
<div class="w3-content">
	
	<p> <button onclick="showForm(document.getElementById('service'));" class="w3-btn">	&#127381; Cadastrar</button>  </p>	
	<p> <button onclick="listEntity('service');" class="w3-btn">	&#8635;</button>  </p>

        <table id="table_service" class="w3-table-all w3-card-4 w3-responsive">
        <thead>
            <tr class="w3-green" >
                <th id="name"><input type="text" class="w3-input w3-green w3-threequarter" oninput="w3.filterHTML('#table_service', '.item', this.value)" placeholder="Nome &#128269;" />	<a class="w3-quarter w3-center w3-padding" href=# onclick="w3.sortHTML('#table_service','.item', 'td:nth-child(1)')">&#8661;</a></th>
                <th id="description"><input type="text" class="w3-input w3-green w3-threequarter" oninput="w3.filterHTML('#table_service', '.item', this.value)" placeholder="Prazo &#128269;" />	<a class="w3-quarter w3-center w3-padding" href=# onclick="w3.sortHTML('#table_service','.item', 'td:nth-child(2)')">&#8661;</a></th>                
                <th id="term"><input type="text" class="w3-input w3-green w3-threequarter" oninput="w3.filterHTML('#table_service', '.item', this.value)" placeholder="Prazo &#128269;" />	<a class="w3-quarter w3-center w3-padding" href=# onclick="w3.sortHTML('#table_service','.item', 'td:nth-child(3)')">&#8661;</a></th>
                <th id="category.name"><input type="text" class="w3-input w3-green w3-threequarter" oninput="w3.filterHTML('#table_service', '.item', this.value)" placeholder="Prazo &#128269;" />	<a class="w3-quarter w3-center w3-padding" href=# onclick="w3.sortHTML('#table_service','.item', 'td:nth-child(3)')">&#8661;</a></th>

                <th id="action"> Ação </th>                
            </tr>
  		</thead>
  		<tbody>
            
		</tbody>         
        </table>
    
</div>
    
<%@include file="../includes/footer.jsp" %> 
    
</body>
</html>
