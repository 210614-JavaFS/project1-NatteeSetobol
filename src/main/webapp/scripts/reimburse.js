
let reimburseButton = document.getElementById("reimburseButton");
reimburseButton.addEventListener("click", reimburse);

function reimburse()
{
	let rebursementAmount = document.getElementById("rebursementAmount").value;
	let rebursementType = document.getElementById("rebursementType").value;
	let rebursementDescription = document.getElementById("rebursementDescription").value;
	
	let xhrRequest = new XMLRequest();
	xhr.onload = success;
	let data = "rebursementAmount=" + rebursementAmount + "&" + 
				"rebursementType=" + rebursementType + "&" +
				"rebursementDescription=" + rebursementDescription
				;
	xhrRequest.open('POST', data);
	xhr.send();
}