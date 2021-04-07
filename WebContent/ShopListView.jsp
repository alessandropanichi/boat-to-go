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
	<script src="js/jquery.datetimepicker.full.min.js"></script>
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>

	<div id="header">
		<jsp:include page="navbar.jsp"/>
	</div>

	<br>
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class=" card border-primary mb-3 bd-sidebar" style="max-width: 20rem;">
				  <div class="card-header">Search Bar</div>
				  <div class="card-body">
				  	  <form action="MainMenuServlet" method="POST">
						  <div class="form-group">
						    <label>City</label>
						    <input type="text" class="form-control" id="city"  placeholder="Enter city" name="city" value="${city}" autocomplete="off" required>
						  </div>
						  <div class="form-group">
						    <label>Check-In</label>
						    <input type="text" id="startdate" class="form-control" placeholder="Enter Check-In" name="checkin" value="${checkin}" autocomplete="off" required>
						  </div>
						  <div class="form-group">
						    <label>Check-Out</label>
						    <input type="text" id="enddate" class="form-control" placeholder="Enter Check-Out" name="checkout" value="${checkout}" autocomplete="off" required>
						  </div>
						  <div class="form-group">
						  	<label>How much people are you?</label>   						           	
							<input type="number" class="form-control" min="1" max="100" step="1" name="personcount" value="${personcount}" autocomplete="off" required>						 
						  </div>
						  <button type="submit" class="btn btn-primary">Search</button>
						</form>

				  </div>
				</div>			
			</div>
			<div class="col-md-9">
				<c:forEach var="boatShop" items="${boatShops}">
					<div class="jumbotron">
					  <h1 class="display-3">${boatShop.name }<br></h1>
					  <p class="lead">${boatShop.description }<br></p>
					  <hr class="my-4">
					  <p>${boatShop.address }<br></p>
					  <p class="lead">
					  	<c:url value="/ShopView" var="url">
					  		<c:param name="boatShopid" value="${boatShop.id}"/>
					  		<c:param name="city" value="${city}"/>
					  		<c:param name="checkin" value="${checkin}"/>
					  		<c:param name="checkout" value="${checkout}"/>
					  		<c:param name="personcount" value="${personcount}"/>
					  	</c:url>
					    <a class="btn btn-primary btn-lg" href="${url }"  role="button">Learn more</a>
					  </p>
					</div>
				</c:forEach>
			</div>
		</div>
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