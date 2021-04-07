<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav class="navbar navbar-expand-lg navbar-fixed-top navbar-dark bg-primary">
	<div class="container">
		<a class="navbar-brand" href="#">Boat To Go</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01" >  
		<c:set var = "username" scope="session" value='<%=session.getAttribute("username")%>'/>
		<c:choose>
		 	<c:when test="${username == null}">
		 		<c:url value="/login.jsp" var="loginurl">
					<c:param name="prevurl" value="${pageContext.request.requestURL}?${pageContext.request.queryString }"/>
				</c:url>
				<a class="btn btn-secondary ml-auto" href="${loginurl}" role="button">Login</a>
		 	</c:when>
		 	<c:otherwise>
		 		<a class="btn btn-secondary ml-auto" href="UserProfile" role="button">User Profile</a>
		 	</c:otherwise>
		</c:choose> 
	</div>
</div>
</nav>
