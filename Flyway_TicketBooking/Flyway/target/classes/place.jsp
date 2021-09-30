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


			<div class="col-lg-4 mb-5">
				<jsp:include page="sidebar.jsp" />
			</div>
			<div class="col-lg-8">
				<article>
					<header class="mb-4">
						<h1 class="fw-bolder mb-1">Add Place</h1>
					</header>
				</article>


				<div class="card w-75">
					<div class="card-head">
						<%
							if (request.getAttribute("MSG") != null) {

								if (request.getAttribute("MSGTYPE") != null) {
									final String MSGTYPE = (String) request.getAttribute("MSGTYPE");
									if (MSGTYPE.equals("ERROR")) {
						%><div class="alert alert-danger" role="alert"><%=request.getAttribute("MSG").toString()%></div>
						<%
							} else {
						%><div class="alert alert-success" role="alert"><%=request.getAttribute("MSG").toString()%></div>
						<%
							}
								}
							}
						%>
					</div>
					<div class="card-body">
						<form action="./PlaceSevlet" method="POST">
							<div class="mb-3 row">
								<label for="placeName" class="col-sm-2 col-form-label">Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="placeName"
										name="placeName">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="placeCode" class="col-sm-2 col-form-label">Place
									Code</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="placeCode"
										name="placeCode">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="latitude" class="col-sm-2 col-form-label">Latitude</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="latitude"
										name="latitude">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="longitude" class="col-sm-2 col-form-label">Longitude</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="longitude"
										name="longitude">
								</div>
							</div>

							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">&nbsp;</label>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">Add
										Place</button>
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
