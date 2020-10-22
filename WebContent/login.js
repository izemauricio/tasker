function loginfocus(element) {
	if(element.value=='Email address') 
		element.value=''
}
function loginfocusout(element) {
	if(element.value=='') 
		element.value='Email address'
}
function passwordfocus(element) {
	if(element.value=='Password') 
		element.value=''
}
function passwordfocusout(element) {
	if(element.value=='') 
		element.value='Password'
}

function GET(variable)
{
	var query = window.location.search.substring(1);

	var vars = query.split("&");

	for (var i=0;i<vars.length;i++) {
		var pair = vars[i].split("=");
		if(pair[0] == variable) {
			return pair[1];
		}
	}

	return(false);
}

function errors() {
	if(GET("error") == "1") {
		document.getElementById('error1').style.display = "block";
	} else {
		document.getElementById('error1').style.display = "none";
	}

	if(GET("error") == "2") {
		document.getElementById('error2').style.display = "block";
	} else {
		document.getElementById('error2').style.display = "none";
	}
}
