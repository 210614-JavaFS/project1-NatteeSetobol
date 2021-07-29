/**
 * 
 */

function checkLogin()
{
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/dologin", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
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