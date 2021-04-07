<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Boat To Go</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="css/jquery.datetimepicker.min.css" rel="stylesheet"/>


<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="js/jquery.datetimepicker.full.min.js"></script>



</head>
<body>

<div id="header">
	<jsp:include page="navbar.jsp"/>
</div>

<br>

<div class="container">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">Navigation Bar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">   
      <form class="collapse navbar-collapse" action="MainMenuServlet" method="get">
      	  <input class="form-control mr-sm-2" type="text" placeholder="Enter City" name="city" autocomplete="off" required>
	      <input type="text" id="startdate" class="form-control mr-sm-2 ml-sm-2" placeholder="Check-In" name="checkin" autocomplete="off" required>
		  <input type="text" id="enddate" class="form-control mr-sm-2 ml-sm-2" placeholder="Check-Out" name="checkout" autocomplete="off" required>
		  <h5><span class="badge badge-light ml-sm-2">How much people are you?</span></h5>      
	      <div class="container">        	
			<input type="number" class="form-control" value="1" min="1" max="100" step="1" name="personcount">
		  </div>
		   <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
      </form>
  </div>
</nav>
</div>
<br>

<div class="jumbotron container">
  <h1 class="display-3">Boat To Go</h1>
  <p class="lead">Rent a boat, a canoe or a dinghy with us</p>
  <hr class="my-4">
  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
  <p class="lead">
    <a class="btn btn-primary btn-lg" href="#" role="button">Get Started!</a>
  </p>
</div>

<script>
	$('#startdate').datetimepicker({
		timepicker:false,
		format:'d/m/Y'
	})
</script>
<script>
	$('#enddate').datetimepicker({
		timepicker:false,
		format:'d/m/Y'
	})
</script>
	

	
</body>
</html>