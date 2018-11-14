/**
 *Generic Functions 
 *
 */

function addRowToTable(tableObj,array,entity){
	var head = tableObj.getElementsByTagName("th");
	var row = document.createElement("TR");
	var commandColumn = document.createElement("TD");
	row.id = "row_"+array['id'];
	row.setAttribute("class", "item");

	
	for (var prop of head) {
		console.log(prop);
		if (prop.id!="action"){
			var column = document.createElement("TD"); 
			var textnode = document.createTextNode(array[prop.id]);

			column.setAttribute("onClick", "loadEditPage("+array['id']+",'"+entity+"');");
			column.setAttribute("id", prop.id);
			
			if(prop.id.indexOf('.')!=-1){
				column.innerHTML = goFuther(prop.id,array,1);
			}else{
				column.innerHTML = array[prop.id];
			}
			row.appendChild(column);
			
		}else{	
			commandColumn.innerHTML = '<a onClick="deleteEntity('+ array[prop.id] +',\''+ entity +'\'); return false;" href="#"> &#128465; </a>';
		}
	}
	
	row.appendChild(commandColumn);
	console.log(tableObj.name); 
	tableObj.getElementsByTagName('tbody')[0].appendChild(row);
	
}


function goFuther(prop,array,i){
	
		

		var first = prop.substring(0,prop.indexOf('.'));
		var last = prop.substring(prop.indexOf('.')+1 , prop.length);
		
		var json = JSON.parse(array[first]);

		if(last.indexOf('.')!=-1){	
			i++;
			return goFuther(last,json,i);			
		}
		
		return json[last];			
		
}

function editRowInTable(row, array){

	var commandColumn = document.createElement("TD");
	
	for (var prop in array) {
		
		if (prop!="id"){
			var cell = row.getElementsByTagName("TD")[prop];
			cell.innerText = array[prop];
		}
	}	

};

function listEntity(entity){
	var xhr = new XMLHttpRequest();

	var table = document.getElementById("table_"+entity.toLowerCase());
	var tbody =table.getElementsByTagName('tbody')[0];
	xhr.open('GET', 'a/list'+entity);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

	xhr.onload = function() {
	    if (xhr.status === 200) {
	    	console.log(xhr.responseText);
	    	
	    	while (tbody.hasChildNodes()) {  
	    		tbody.removeChild(tbody.firstChild);
	    	} 
	    	var responseArray = JSON.parse(xhr.responseText);
	    	console.log(responseArray[0]);
	    	
	    	for(var val in responseArray) {
	    		addRowToTable(table,responseArray[val],entity);
	 		}
	    	
	    }
	    else {
	        alert('Request failed.  Returned status of ' + xhr.responseText);
	    }
	};
	xhr.send();
};



function loadEditPage(id,entity){
	var xhr = new XMLHttpRequest();
	
	xhr.open('PUT', 'a/edit'+entity+'/'+id);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onload = function() {
	    if (xhr.status === 200) {	    	
	    	console.log(xhr.responseText);
	    	
	    	showForm(document.getElementById(entity.toLowerCase()));
	    	
	    	formLoad(document.getElementById(entity.toLowerCase()), JSON.parse(xhr.responseText));
	    	console.log(xhr.responseText);
	    }
	    else {
	        alert('Request failed.  Returned status of ' + xhr.responseText );
	    }
	};
	xhr.send();
};

function addOrEditEntity(id,entity){
	var xhr = new XMLHttpRequest();
	console.log(entity);
	console.log(entity.toLowerCase());
	
	var table = document.getElementById("table_"+entity.toLowerCase());
	var formData = new FormData(document.getElementById(entity.toLowerCase()));
	var data = ""; 

	for(var pair of formData.entries()) {
		   data += pair[0]+ '='+ encodeURI(pair[1]) + '&'; 
		}
	
	data = data.slice(0, data.length-1);
	console.log(data);
	
	xhr.open('POST', 'a/add'+entity);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
	xhr.onload = function() {
	    if (xhr.status === 200) {
	    	console.log(xhr.responseText);
	    	var responseArray = JSON.parse(xhr.responseText);
	    	
	    	if (id+""!="") {
	    		var row = document.getElementById("row_"+responseArray.id);
	    		editRowInTable(row,responseArray);	
	    	}else{
	    		console.log(responseArray.id);
	    		addRowToTable(table,responseArray,entity);
	    	}
	    	
	    	hideForm(document.getElementById(entity.toLowerCase()));

	    }
	    else {
	        alert('Request failed.  Returned status of ' + xhr.responseText);
	    }
	};
	xhr.send(data);

};

function deleteEntity(id,entity){
	var xhr = new XMLHttpRequest();

	var row = document.getElementById("row_"+id);
	xhr.open('DELETE', 'a/delete'+entity+'/'+id);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onload = function() {
	    if (xhr.status === 200) {	    	
	    	
	    	console.log(JSON.parse(xhr.responseText));
	    	
	    	row.remove();
	    	
	    }
	    else {
	        alert('Request failed.  Returned status of ' + xhr.responseText );
	    }
	};
	xhr.send();

};

function formLoad(formObj,array){	
	for (var prop in array) {
		console.log(prop);
		console.log(formObj[prop]);
		console.log(array[prop]);
		
		formObj[prop].value = array[prop];
	}	

};

function resetForm(form) {
    // clearing inputs
    var inputs = form.getElementsByTagName('input');
    for (var i = 0; i<inputs.length; i++) {
        switch (inputs[i].type) {
            case 'hidden':
            case 'text':
                inputs[i].value = '';
                break;
            case 'radio':
            case 'checkbox':
                inputs[i].checked = false;   
        }
    }

    // clearing selects
    var selects = form.getElementsByTagName('select');
    for (var i = 0; i<selects.length; i++)
        selects[i].selectedIndex = 0;

    // clearing textarea
    var text= form.getElementsByTagName('textarea');
    for (var i = 0; i<text.length; i++)
        text[i].value= '';

    return false;
}


function showForm(form){
	form.parentElement.style.display="inline";
	resetForm(form);
	console.log(form);
};


function hideForm(form){
	form.parentElement.style.display="none";
	resetForm(form);
	console.log(form);
};

