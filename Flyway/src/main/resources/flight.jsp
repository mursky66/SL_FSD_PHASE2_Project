<!DOCTYPE html>
<%@page import="com.flyway.models.Place"%>
<%@page import="com.flyway.models.Airline"%>
<%@page import="java.util.List"%>
<%@page import="com.flyway.constants.FlywayConstant"%>
<%@page import="java.util.Objects"%>
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
				<!-- Post content-->
				<article>
					<!-- Post header-->
					<header class="mb-4">
						<!-- Post title-->
						<h1 class="fw-bolder mb-1">Add Flight</h1>
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
						<form action="./FlightServlet" method="POST">

							<div class="mb-3">
								<label for="airlineId" class="form-label">Airline Name</label> <select
									class="form-select" id="airlineId" name="airlineId"
									aria-label="airlineId">
									<%
									if (Objects.nonNull(request.getAttribute(FlywayConstant.AIRLINES))) {
										final List<Airline> airlines = (List<Airline>) request
										.getAttribute(FlywayConstant.AIRLINES);
										for (Airline airline : airlines) {
									%><option value="<%=airline.getAirlineId()%>"><%=airline.getAirlineName()%></option>
									<%
									}
									}
									%>
								</select>
							</div>

							<div class="mb-3 row">
								<label for="flightCode" class="col-sm-2 col-form-label">Flight
									Code</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="flightCode"
										name="flightCode">
								</div>
							</div>

							<div class="mb-3">
								<label for="fromPlace" class="form-label">From</label> <select
									class="form-select" id="fromPlaceId" name="fromPlaceId"
									aria-label="From">
									<option value="NA">Select</option>
									<%
									if (Objects.nonNull(request.getAttribute(FlywayConstant.PLACES))) {
										final List<Place> places = (List<Place>) request
										.getAttribute(FlywayConstant.PLACES);
										for (Place place : places) {
									%><option value="<%=place.getPlaceId()%>">
										<%=place.getPlaceName()%>
									</option>
									<%
									}
									}
									%>
								</select>
							</div>

							<div class="mb-3">
								<label for="toPlace" class="form-label">To</label> <select
									class="form-select" id="toPlaceId" name="toPlaceId"
									aria-label="To">
									<option value="NA">Select</option>
									<%
									if (Objects.nonNull(request.getAttribute(FlywayConstant.PLACES))) {
										final List<Place> places = (List<Place>) request
										.getAttribute(FlywayConstant.PLACES);
										for (Place place : places) {
									%><option value="<%=place.getPlaceId()%>">
										<%=place.getPlaceName()%>
									</option>
									<%
									}
									}
									%>
								</select>
							</div>

							<div class="mb-3 row">
								<label for="passengerCapacity" class="col-sm-2 col-form-label">Passenger
									Capacity</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="passengerCapacity"
										name="passengerCapacity"
										placeholder="i.e {5, 10, 50, 100, 200}">
								</div>
							</div>

							<div class="mb-2">
								<label for="departureDate" class="form-label">Departure</label>
								<input type="text" id="departureDate" name="departureDate"
									class="form-control" placeholder="yyyy-MM-dd">
							</div>


							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">&nbsp;</label>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">Add
										Flight</button>
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
