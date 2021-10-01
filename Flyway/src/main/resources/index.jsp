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

				<form class="mb-4 input-group">

					<div class="mb-3">
						<label for="fromPlace" class="form-label">From</label> <select
							class="form-select" id="fromPlace" name="fromPlace"
							aria-label="From">
						</select>
					</div>

					<div class="mb-3">
						<label for="toPlace" class="form-label">To</label> <select
							class="form-select" id="toPlace" name="toPlace" aria-label="To">
						</select>
					</div>

					<div class="mb-2">
						<label for="departureDate" class="form-label">Departure</label> <input
							type="text" id="departureDate" class="form-control">
					</div>

					<div class="mb-2">
						<label for="noOfPerson" class="form-label">Number of
							Persons</label> <input type="text" id="noOfPerson" class="form-control">
					</div>

					<div class="mb-2">
						<label class="form-label">&nbsp;</label>
						<button class="btn btn-primary form-control" id="button-search"
							type="button">Search</button>
					</div>

				</form>

			</div>
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
							$("#fromPlace").append('<option value="'+value.placeId+'">'+value.placeName+'</option>');
							$("#toPlace").append('<option value="'+value.placeId+'">'+value.placeName+'</option>');
						});
					}

				}
			});
		}
	</script>


</body>
</html>