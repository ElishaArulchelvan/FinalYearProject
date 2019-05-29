
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet"></link>
</head>

<div class="login-container">
		<c:if test="${param.logout != null}">
		<div class="alert alert-success fade in">
			<a class="close" data-dismiss="alert" href="#">&times;</a>
			<p>You've logged out</p>
		</div>
	</c:if>

	<c:if test="${param.register != null}">
		<div class="alert alert-info fade in">
			<a class="close" data-dismiss="alert" href="#">&times;</a>
			<p>Register successful. You can log in</p>
		</div>
	</c:if>

	<c:if test="${param.error != null}">
		<div class="alert alert-danger fade in">
			<a class="close" data-dismiss="alert" href="#">&times;</a>
			<p>Username or password is incorrect</p>
		</div>
	</c:if>

	<div class="card-card-container">
		<h4>Login to your account</h4>
		<c:url value="/login" var="loginURL" />
		<form action="${loginURL}" method="post" class="form-signin">
			<input name="email" type="email" id="inputEmail" class="form-control"
				placeholder="Email address" required autofocus> <input
				name="password" type="password" id="inputPassword"
				class="form-control" placeholder="Password" required>
			<div id="remember" class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block btn-signin"
				type="submit">Sign in</button>
		</form>
	</div>

</div>



