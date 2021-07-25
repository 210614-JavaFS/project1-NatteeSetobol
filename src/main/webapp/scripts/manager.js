/**
 * 
 */

let approveTicketTable = document.getElementById('approveTickets');

function Approve(id)
{
	alert(id);
}

function AddToTicketTable(id,author, amount, description, timesubmitted,authorId)
{		
	let approveTicketTableRowCount = approveTicketTable.rows.length;

	let  approveTicketRow = approveTicketTable.insertRow(approveTicketTableRowCount);

	let nameColumn  =  approveTicketRow.insertCell(0);
	nameColumn.innerHTML = id;

	let nameColumn2  =  approveTicketRow.insertCell(1);
	nameColumn2.innerHTML = author;

	let nameColumn3  =  approveTicketRow.insertCell(2);
	nameColumn3.innerHTML = amount;

	let nameColumn4  =  approveTicketRow.insertCell(3);
	nameColumn4.innerHTML = description;

	let nameColumn5  =  approveTicketRow.insertCell(4);
	nameColumn5.innerHTML = timesubmitted;

	let nameColumn6  =  approveTicketRow.insertCell(5);
	nameColumn6.innerHTML = '<div class="dropdown"><button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">Action</button><ul class="dropdown-menu"aria-labelledby="dropdownMenuButton1"><li><a class="dropdown-item" href="#" onClick="Approve(' + authorId + ');">Approve</a></li><li><a class="dropdown-item" href="#">Deny</a></li></ul></div>';

}

function PopulateTicketTable()
{
	let xhr = new XMLHttpRequest();
xhr.open("GET", "/api/GetAllUnapproveTickets", true);


 xhr.onreadystatechange = function () {
    if(this.readyState===4 && this.status===200){
      let data = JSON.parse(xhr.responseText);

		if (data)
		{
			if (data.error)
			{
				alert(data.error);
			} else {
				for (ticketIndex = 0; ticketIndex < data.length; ticketIndex++)
				{
					AddToTicketTable(ticketIndex+1,data[ticketIndex].author, data[ticketIndex].amount,data[ticketIndex].descrip , data[ticketIndex].date, data[ticketIndex].authorId);
				}
			}
		}
    }
}
xhr.send();

}