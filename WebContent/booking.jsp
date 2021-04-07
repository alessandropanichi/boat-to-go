<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" >
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Boat To Go</title>
  </head>
  <body>
    <div id="header">
		<jsp:include page="navbar.jsp"/>
	</div>
	<hr>
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="card border-primary mb-3" style="max-width: 18rem;">
				  <div class="card-body text-primary">
				    <h5 class="card-title">Selected Boats</h5>
				    	<table class="table">
						  <thead>
						    <tr>
						      <th scope="col">#Seats</th>
						      <th scope="col">Selected Boats</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach var="boat" items="${boats}">
						  		<tr>
							      <th scope="row">${boat.seats }</th>
							      <td>${boat.boatChoise}</td>
							    </tr>
						  	</c:forEach>
						  </tbody>
						</table>
				  </div>
				</div>
			</div>
			<div class="col-md-9">
				<form class="needs-validation" method="POST" action="BookingServlet" novalidate>
					<c:forEach var="person" begin="1" end="${fields.personCount}">					
					  <div class="card border-primary mb-3 form-column">
					  	<div class="card-body">					    
					      <label for="validationCustom01">First name</label>
					      <input type="text" class="form-control" id="validationCustom01" name="${person}fname" autocomplete="off" required>
					      <div class="invalid-feedback">
					        Please fill this field.
					      </div>					    
					      <label for="validationCustom02">Last name</label>
					      <input type="text" class="form-control" id="validationCustom02" name="${person}lname" autocomplete="off" required>
					      <div class="invalid-feedback">
					        Please fill this field.
					      </div>					    
					      <label for="validationCustomUsername">Fiscal Code</label>					      
					      <input type="text" class="form-control" id="validationCustomUsername" name="${person}cf"  autocomplete="off" required>
					      <div class="invalid-feedback">
					        Please fill this field.
					      </div>					    
					 	</div>
					  </div>
					</c:forEach>
					<button class="btn btn-primary" type="submit">Submit form</button>
				</form>
			</div>
		</div>
	</div>

    <!-- Optional JavaScript -->
    <script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
	  'use strict';
	  window.addEventListener('load', function() {
	    // Fetch all the forms we want to apply custom Bootstrap validation styles to
	    var forms = document.getElementsByClassName('needs-validation');
	    // Loop over them and prevent submission
	    var validation = Array.prototype.filter.call(forms, function(form) {
	      form.addEventListener('submit', function(event) {
	        if (form.checkValidity() === false) {
	          event.preventDefault();
	          event.stopPropagation();
	        }
	        form.classList.add('was-validated');
	      }, false);
	    });
	  }, false);
	})();
	</script>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  </body>
</html>