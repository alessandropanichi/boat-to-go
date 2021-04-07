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
    <link href="css/signin.css" rel="stylesheet">
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Boat To Go</title>
    
  </head>
  <body class="text-center">
    <form class="form-signin" action="LoginServlet" method="POST">
    	<h1 class="h3 mb-3 font-weight-normal">Sign-in</h1>
    	<label for="inputEmail" class="sr-only">Username</label>
    	<c:set var="valide" value="form-control"></c:set>
    	<c:if test="${error.equals('Username/password are wrong.') }">
    		<c:set var="valide" value="form-control is-invalid"></c:set>
    	</c:if>
    	<input type="text" id="inputEmail" class="${valide }" placeholder="Username" required="" autofocus="" name="username" autocomplete="off">
    	<label for="inputPassword" class="sr-only">Password</label>
    	<input type="password" id="inputPassword" class="${valide }" placeholder="Password" required="" name="password">
    	<c:if test="${error.equals('Username/password are wrong.') }">
    		<div class="invalid-feedback">
    			${error }<br>
    		</div>
    	</c:if>
    	<input type="hidden" name="prevurl" value="${param.prevurl }">
    	<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  </body>
</html>