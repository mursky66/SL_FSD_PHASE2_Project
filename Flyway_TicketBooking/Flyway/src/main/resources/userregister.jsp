<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="Flyway Ticketbooking (Admin panel)" />
<meta name="author" content="Mayur Solanki" />

<title>Flyway-TicketBooking</title>
<jsp:include page="headerScript.jsp" />

</head>
<body>
	<!-- Responsive navbar-->
	<jsp:include page="header.jsp" />
	<!-- Page content-->
	<div class="container mt-5">
		<div class="row">
			<div class="col-lg-12">
				<!-- Post content-->
				<article>
					<!-- Post header-->
					<header class="mb-4">
						<!-- Post title-->
						<h1 class="fw-bolder mb-1">User Registration</h1>
					</header>
				</article>


				<div class="card w-75">
				<div class="card-head">
					<%if (request.getAttribute("MSG") != null) {
					
						if(request.getAttribute("MSGTYPE") != null){
							final String MSGTYPE = (String)request.getAttribute("MSGTYPE");
							if(MSGTYPE.equals("ERROR")){
								%><div class="alert alert-danger" role="alert"><%=request.getAttribute("MSG").toString()%></div><%
								
							}else{
								%><div class="alert alert-success" role="alert"><%=request.getAttribute("MSG").toString()%></div><%
								
							}
						}
					}
					%>
				</div>
					<div class="card-body">
						<form action="./UserServlet" method="POST">
							<div class="mb-3 row">
								<label for="firstName" class="col-sm-2 col-form-label">First Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="firstName" name="firstName">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="lastName" class="col-sm-2 col-form-label">Last Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="lastName" name="lastName">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="email" class="col-sm-2 col-form-label">Email</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="email" name="email">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="password" class="col-sm-2 col-form-label">Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password" name="password">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="age" class="col-sm-2 col-form-label">Your Age</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="age" name="age">
								</div>
							</div>

							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">&nbsp;</label>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">Register</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<br />

			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>
