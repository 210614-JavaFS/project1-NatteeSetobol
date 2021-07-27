
let reimburseButton = document.getElementById("reimburseButton");
reimburseButton.addEventListener("click", reimburse);

function reimburse()
{
	let rebursementAmount = document.getElementById("rebursementAmount").value;
	let rebursementType = document.getElementById("rebursementType").value;
	let rebursementDescription = document.getElementById("rebursementDescription").value;
	
	let xhr = new XMLHttpRequest();
	xhr.open("POST",'/doReimbursment', true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() { // Call a function when the state changes.
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
    	alert('success!');
    	document.getElementById("rebursementAmount").disabled = false;
		document.getElementById("rebursementType").disabled = false;
		document.getElementById("rebursementDescription").disabled = false;
	    document.getElementById("rebursementAmount").value = "";
		document.getElementById("rebursementType").value = "";
		document.getElementById("rebursementDescription").value = "";
	
    }
}
	let data = "rebursementAmount=" + rebursementAmount + "&" + 
				"rebursementType=" + rebursementType + "&" +
				"rebursementDescription=" + rebursementDescription
				;
	xhr.send(data);
	document.getElementById("rebursementAmount").disabled = true;
	document.getElementById("rebursementType").disabled = true;
	document.getElementById("rebursementDescription").disabled = true;
	
}