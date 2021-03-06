<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<nav class="navbar navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Dropdown </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="#">Action</a></li>
						<li><a class="dropdown-item" href="#">Another action</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#">Something else here</a></li>
					</ul></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li>
			</ul>
			<form class="d-flex">
				<input class="form-control me-2" type="search" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>
</head>
<body>

	<div class="row" style="height: 50px;"></div>
	<div class="row">
		<span class="col px-lg-1"></span>
		<span class="col px-lg-2">
			<div class="row">
				<label for="inputReimbursement" class="col-sm-4 col-form-label"><h3>Reimbursement</h3></label>
			</div>
			<div class="row" style="height: 3px;"></div>
			<div class="row">
				<label for="reimbursementAmount" class="col-sm-8 col-form-label">Reimbursement
					amount</label>
			</div>
			<div class="row">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">$</span>
					</div>
					<div>
						<input type="text" class="form-control" id="rebursementAmount"
							aria-describedby="basic-addon3">
					</div>
				</div>
			</div>
			<div class="row">
				<label for="reimbursementAmount" class="col-sm-8 col-form-label">Reimbursement
					type</label>
			</div>
			<div class="row">
				<div class="input-group mb-5">
					<div>
						<input type="text" class="form-control" id="rebursementType"
							aria-describedby="basic-addon3">
					</div>
				</div>
			</div>
			<div class="row">
				<label for="reimbursementAmount" class="col-sm-8 col-form-label">Reimbursement
					Description</label>
			</div>
			<div class="row">
				<div class="input-group mb-5">
					<div>
						<textarea class="form-control" aria-label="With textarea"
							id="rebursementDescription"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="input-group mb-5">
					<div>
						<button class="btn btn-primary" type="submit" id="reimburseButton">Reimbursed</button>
					</div>
				</div>
			</div>
		</span>
		<span class="col px-lg-8">
			<div class="accordion accordion-flush" id="accordionFlushExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingOne">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
							aria-expanded="false" aria-controls="flush-collapseOne">
							Pending Tickets</button>
					</h2>
					<div id="flush-collapseOne" class="accordion-collapse collapse"
						aria-labelledby="flush-headingOne"
						data-bs-parent="#accordionFlushExample">
						<div class="accordion-body">
							<table style="width: 100%">
								<thead>
									<tr>
										<th>Type</th>
										<th>Amount</th>
										<th>Date</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${pendingTickets}" var="pendingTicket">
										<tr>
											<td>${pendingTicket.get("type")}</td>
											<td>${pendingTicket.get("amount")}</td>
											<td>${pendingTicket.get("date")}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingTwo">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo"
							aria-expanded="false" aria-controls="flush-collapseTwo">
							Approved Tickets</button>
					</h2>
					<div id="flush-collapseTwo" class="accordion-collapse collapse"
						aria-labelledby="flush-headingTwo"
						data-bs-parent="#accordionFlushExample">
						<div class="accordion-body">
							<table style="width: 100%">
								<thead>
									<tr>
										<th>Type</th>
										<th>Amount</th>
										<th>Date</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${approvedTickets}" var="approvedTicket">
										<tr>
											<td>${approvedTicket.get("type")}</td>
											<td>${approvedTicket.get("amount")}</td>
											<td>${approvedTicket.get("date")}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</span>

				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingThree">
						<button class="accordion-button collapsed" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseThree"
							aria-expanded="false" aria-controls="flush-collapseThree">
							Denied Tickets</button>
					</h2>
					<div id="flush-collapseThree" class="accordion-collapse collapse"
						aria-labelledby="flush-headingThree"
						data-bs-parent="#accordionFlushExample">
						<div class="accordion-body">
							<table style="width: 100%">
								<thead>
									<tr>
										<th>Type</th>
										<th>Amount</th>
										<th>Date</th>
									</tr>

								</thead>
								<tbody>
									<c:forEach items="${disapprovedTickets}"
										var="disapprovedTicket">
										<tr>
											<td>${disapprovedTicket.get("type")}</td>
											<td>${disapprovedTicket.get("amount")}</td>
											<td>${disapprovedTicket.get("date")}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/scripts/reimburse.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>
</html>