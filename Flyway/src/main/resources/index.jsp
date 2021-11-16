<!DOCTYPE html>
<%@page import="com.flyway.models.Flight"%>
<%@page import="java.util.List"%>
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
<body onload="populatePlaces()">

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
						<h1 class="fw-bolder mb-1">Book Flight</h1>
					</header>
				</article>

				<form class="mb-4 input-group" action="./SearchFlightServlet" method="post">

					<div class="mb-3">
						<label for="fromPlace" class="form-label">From</label> <select
							class="form-select" id="fromPlace" name="fromPlaceId"
							aria-label="From">
						</select>
					</div>

					<div class="mb-3">
						<label for="toPlace" class="form-label">To</label> <select
							class="form-select" id="toPlace" name="toPlaceId" aria-label="To">
						</select>
					</div>

					<div class="mb-2">
						<label for="departureDate" class="form-label">Departure</label> <input
							type="text" id="departureDate" name="departureDate"
							class="form-control" placeholder="yyyy-MM-dd">
					</div>

					<div class="mb-2">
						<label for="noOfPerson" class="form-label">Number of
							Persons</label> <input type="text" id="noOfPerson" class="form-control"
							placeholder="i.e {1, 2, 3}">
					</div>

					<div class="mb-2">
						<label class="form-label">&nbsp;</label>
						<button class="btn btn-primary form-control" id="button-search"
							type="submit" onclick="">Search</button>
					</div>

				</form>

			</div>
		</div>

		<div id="searchResult">
		
			<%
			if(request.getAttribute("FLIGHTS")!=null){
				List<Flight> flights = (List<Flight>)request.getAttribute("FLIGHTS");
			
				for(Flight f: flights){
				%>
				<div class="card mb-3">
				<h5 class="card-header"><%=f.getAirlineName() %></h5>
				<div class="card-body">
					<h5 class="card-title">Bombay to Delhi</h5>
					<p class="card-text">Depart on 2021-10-04</p>
					<p class="card-text">Ticket price: 25$</p>
					<a href="./PaymentServlet" class="btn btn-primary">Book Ticket</a>
				</div>
			</div>
				<%	
				}
			}
			%>

			<!-- <div class="card mb-3">
				<h5 class="card-header">Air India</h5>
				<div class="card-body">
					<h5 class="card-title">Bombay to Delhi</h5>
					<p class="card-text">Depart on 2021-10-04</p>
					<p class="card-text">Ticket price: 25$</p>
					<a href="./PaymentServlet?fromPlaceId=3&toPlaceId=6&departureDate=2021-10-15" class="btn btn-primary">Book Ticket</a>
				</div>
			</div>

			<div class="card mb-3">
				<h5 class="card-header">Indigo</h5>
				<div class="card-body">
					<h5 class="card-title">Bombay to Delhi</h5>
					<p class="card-text">Depart on 2021-10-04</p>
					<p class="card-text">Ticket price: 68$</p>
					<a href="./PaymentServlet" class="btn btn-primary">Book Ticket</a>
				</div>
			</div>
 -->
		</div>

	</div>
	<jsp:include page="footer.jsp" />

	<script type="text/javascript">
		function populatePlaces() {
			$("#fromPlace").html("");
			$("#toPlace").html("");
			$("#fromPlace").append('<option value="NA">Select</option>');
			$("#toPlace").append('<option value="NA">Select</option>');

			$.ajax({
				url : "http://localhost:8080/Flyway/ListPlacesServlet",
				type : 'GET',
				success : function(res) {
					console.log(res);
					if (res) {
						$.each(res, function(key, value) {
							$("#fromPlace").append(
									'<option value="'+value.placeId+'">'
											+ value.placeName + '</option>');
							$("#toPlace").append(
									'<option value="'+value.placeId+'">'
											+ value.placeName + '</option>');
						});
					}

				}
			});
		}

		function searchFlights() {

			$.ajax({
				url : "http://localhost:8080/Flyway/SearchFlightServlet",
				type : 'POST',
				success : function(res) {
					console.log(res);
					/* if (res) {
						$.each(res, function(key, value) {
						});
					} */
				}
			});
		}
	</script>


</body>
</html>
