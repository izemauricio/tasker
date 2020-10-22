function taskFocus(element) {
	if(element.value=='Task') 
		element.value='';
};

function taskFocusOut(element) {
	if(element.value=='') 
		element.value='Task';
};

window.onload = function () {
};

function ssubmit(element) {	
	datte = document.getElementById("datee").value;
	
	if(datte == "") 
		return false;
	
	part = datte.split('-');
	
	if(part.length != 3) 
		return false;
	
	if(isNaN(part[0]))
		return false;
	
	if(isNaN(part[1]))
		return false;
	
	if(isNaN(part[2]))
		return false;
	
	if(part[0] < 0)
		return false;
	
	if(part[0] > 3000)
		return false;
	
	if(part[1] > 12)
		return false;
	
	if(part[1] <= 0)
		return false;
	
	if(part[2] <= 0)
		return false;
	
	if(part[2] > 31)
		return false;
	
	return true;
};