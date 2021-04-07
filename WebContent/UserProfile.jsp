<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

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
    			<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Main Menu</a>
    		</div>
    		<div class="col-md-9">
			    	<div class="card border-primary">
	    		 <c:forEach var="booking" items="${bookings}">
	    		 
					  <table class="table">
						  <thead>
						    <tr>
						      <th scope="col">Boat Shop Name</th>
						      <th scope="col">Check-In</th>
						      <th scope="col">Check-Out</th>
						      <th scope="col">State</th>
						      <th scope="col">People Registered</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr>
						      <td>${booking.boatShop}</td>
						      <td>${booking.checkIn}</td>
						      <td>${booking.checkOut}</td>
						      <td>${booking.state}</td>
						      <td>
						      	
						      	<div class="btn-group">
								  <button type="button" class="btn btn-secondary dropdown-toggle " data-toggle="dropdown" data-display="static" aria-haspopup="true" aria-expanded="true">
								    	Show people
								  </button>
								  <div class="dropdown-menu  text-muted " style="width: 300px;">
									  <c:forEach var="person" items="${booking.people}">
									  	<p> CF: ${person.fiscalCode} <br>Name: ${person.name } <br>Last Name: ${person.lastname}</p>
									  	<hr>
									  </c:forEach>
									</div>
								</div>						      	
						      </td>
						      <td>
						      	<form action="UserProfile" method="POST">
						      		<input type="hidden" name="bookingid" value="${booking.bookingId}">
						      		<button class="btn btn-warning my-2 my-sm-0" type="submit">Delete</button>
						      	</form>
						      </td>
						    </tr>
						    
						  </tbody>
						  
						</table>
			    </c:forEach>
					</div>
					
					
    		</div>
    	</div>   
    	 	   
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  </body>
</html>