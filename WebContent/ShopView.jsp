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
				<div class="card border-primary" style="width: 18rem;">
				  <div class="card-body">
				    <h5 class="card-title">Boats Available</h5>
				    <h6 class="card-subtitle mb-2 text-muted">Select how many seats you need.</h6>
				    <form action="BookingServlet" method="GET">
					  <table class="table">
						  <thead>
						    <tr>
						      <th scope="col">#Seats</th>
						      <th scope="col">Available</th>
						      <th scope="col">Select boats</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach var="boat" items="${boats}">
						  		<tr>
							      <th scope="row">${boat.seats }</th>
							      <td>${boat.availability }</td>
							      <td><input type="number" min="0" value="0" class="form-control" name="${boat.seats}"></td>
							    </tr>
						  	</c:forEach>
						  </tbody>
						</table>
						<c:if test="${error.equals('Invalid combination.')}">
						    <div class="alert alert-danger" role="alert">
						    	${error}<br>
						    </div>
						</c:if>
					  <button type="submit" class="btn btn-primary">Book</button>
					</form>
				    
				  </div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="container">
					<h3>${boatShop.name}<br></h3>
					<p>${boatShop.address }<br></p>
					<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
					  <div class="carousel-inner">
					    <div class="carousel-item active">
					      <img src="https://source.unsplash.com/user/erondu/1600x900" class="d-block w-100" alt="...">
					    </div>
					    <div class="carousel-item">
					      <img src="https://source.unsplash.com/user/erondu/1600x900" class="d-block w-100" alt="...">
					    </div>
					    <div class="carousel-item">
					      <img src="https://source.unsplash.com/user/erondu/1600x900" class="d-block w-100" alt="...">
					    </div>
					  </div>
					  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
					    <span class="carousel-control-next-icon" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div>
					<p>${boatShop.description }</p>
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