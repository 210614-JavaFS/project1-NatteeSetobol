/**
 * 
 */

let approveTicketTable = document.getElementById('approveTickets');
let approveTicketHistoryTable = document.getElementById('ticketsHistory');

function Approve(id)
{
	let xhr = new XMLHttpRequest();
	xhr.open("POST",'/approveticket', true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() { 
    	if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
			DeleteRows();
			PopulateTicketTable();
			PopulateTicketHistoryTable();
    	}
	}
	let data = "ticketId=" + id;
	xhr.send(data);
}

function Disapprove(id)
{
	let xhr = new XMLHttpRequest();
	xhr.open("POST",'/disapproveticket', true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() { 
    	if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
			DeleteRows();
			PopulateTicketTable();
			 PopulateTicketHistoryTable();
    	}
	}
	let data = "ticketId=" + id;
	xhr.send(data);
}


function AddToTicketTable(id,author, amount, description, timesubmitted,ticketNumber)
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
	nameColumn6.innerHTML = '<div class="dropdown"><button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">Action</button><ul class="dropdown-menu"aria-labelledby="dropdownMenuButton1"><li><a class="dropdown-item" href="#" onClick="Approve(' + ticketNumber + ');">Approve</a></li><li><a class="dropdown-item" href="#" onClick="Disapprove(' + ticketNumber + ');"">Deny</a></li></ul></div>';

}

function AddToTicketHistoryTable(num,data)
{		
	let approveTicketTableRowCount = approveTicketHistoryTable.rows.length;

	let  approveTicketRow = approveTicketHistoryTable.insertRow(approveTicketTableRowCount);

	let nameColumn  =  approveTicketRow.insertCell(0);
	nameColumn.innerHTML = num+1;

	let nameColumn2  =  approveTicketRow.insertCell(1);
	nameColumn2.innerHTML = data.author;

	let nameColumn3  =  approveTicketRow.insertCell(2);
	nameColumn3.innerHTML = data.amount;

	let nameColumn4  =  approveTicketRow.insertCell(3);
	nameColumn4.innerHTML = data.descrip;

	let nameColumn5  =  approveTicketRow.insertCell(4);
	nameColumn5.innerHTML = data.date;

	let nameColumn6  =  approveTicketRow.insertCell(5);
	nameColumn6.innerHTML = data.ticketStatus; 
	
	let nameColumn7  =  approveTicketRow.insertCell(6);
	nameColumn7.innerHTML = data.resolver;
	
	let nameColumn8  =  approveTicketRow.insertCell(7);
	nameColumn8.innerHTML = data.ticketTimeResolve;
	
}

function PopulateTicketTable()
{
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "/api/GetAllUnapproveTickets", true);
	
 	xhr.onreadystatechange = function ()
 	{
    	if(this.readyState===4 && this.status===200)
    	{
      		let data = JSON.parse(xhr.responseText);

			if (data)
			{
				if (data.error)
				{
					alert(data.error);
				} else {
					for (ticketIndex = 0; ticketIndex < data.length; ticketIndex++)
					{
						
						AddToTicketTable(ticketIndex+1,data[ticketIndex].author, data[ticketIndex].amount,data[ticketIndex].descrip , data[ticketIndex].date,data[ticketIndex].number);
						
					}
				}
			}
		}
    }

	xhr.send();

}

function PopulateTicketHistoryTable()
{
	let xhr = new XMLHttpRequest();
	xhr.open("GET", "/api/GetAllTickets", true);

 	xhr.onreadystatechange = function ()
 	{
    	if(this.readyState===4 && this.status===200)
    	{
      		let data = JSON.parse(xhr.responseText);

			if (data)
			{
				if (data.error)
				{
					alert(data.error);
				} else {
					DeleteHistoryRows();
					console.log(data);
					for (ticketIndex = 0; ticketIndex < data.length; ticketIndex++)
					{
						AddToTicketHistoryTable(ticketIndex,data[ticketIndex]);
					}
				}
			}
		}
    }

	xhr.send();

}


function DeleteRows() {
	var rowCount = approveTicketTable.rows.length;
 	for (var i = rowCount - 1; i > 0; i--) {
    	approveTicketTable.deleteRow(i);
    }
}

function DeleteHistoryRows() {
	var rowCount = approveTicketHistoryTable.rows.length;
 	for (var i = rowCount - 1; i > 0; i--) {
    	approveTicketHistoryTable.deleteRow(i);
    }
}

function PopulateTables()
{
	PopulateTicketHistoryTable();
	PopulateTicketTable();
}