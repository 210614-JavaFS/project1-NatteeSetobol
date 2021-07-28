<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<style>
@font-face {
  font-family: "MedyanScript";
  src: url("fonts/MedyanScript.otf");
}
#logo {
	vertical-align:middle;
}
.navbar-brand font
{
	font-family: MedyanScript;
}
.navbar-brand span {
	font-size:2rem;
	line-height:1.2;
	font-weight: 200;
	display:inline-block;
	vertical-align:middle;
	padding:0 0 0 10px;
}
.navbar-brand b {
	display:block;
	font-size:50%;
	line-height:1;
}
.navbar-nav {
	margin-left:auto;
}
</style>
<meta charset="ISO-8859-1">
<title>Login page</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
   <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<nav class="navbar navbar-dark bg-dark">
  <div class="container-fluid">
    <span><img id="logo" alt="Logo" src="images/ticket.png" width="120" height="80"> <a class="navbar-brand" href="#">Tick-it</a></span>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
</head>
<body>
<div class="row" style="height: 250px;">
  
</div>
<div class="row">
  	 <div class="col px-sm-4">
  	 </div>
 	 <div class="col px-sm-4">
 	 
 	 <a class="navbar-brand" href="#"> <img id="logo" alt="Logo" src="images/ticket.png" width="120" height="80"> <span><font>Tick-It</font><b><i>Ticket reimbursement system</i></b></span> </a>
 	<br>
 	<br>
<%
	String hasErrorMessage = request.getParameter("error");
	if (hasErrorMessage != null)
	{
		if (hasErrorMessage.equals("yes"))
		{
%>
		<div class="alert alert-primary" role="alert">
 			${error}
		</div>
<% 		}
	}
%>
		<form action="dologin" method="post">
  			<div class="row mb-3">
    			<label for="inputEmail3" class="col-sm-2 col-form-label">Username</label>
    			<div class="col-sm-10">
      				<input type="text" class="form-control" id="username" name="username">
    			</div>
  			</div>
  			<div class="row mb-3">
    			<label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" id="password" name="password">
    			</div>
  			</div>
  			<button type="submit" class="btn btn-primary" id="signInButton">Sign in</button>
		</form>
	</div>
	 <div class="col px-sm-4">
  	</div>
</div>


  <script src="/scripts/login.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>