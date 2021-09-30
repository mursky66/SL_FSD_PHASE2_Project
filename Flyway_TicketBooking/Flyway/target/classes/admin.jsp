<!DOCTYPE html>
<%@page import="com.flyway.models.User"%>
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
					<!-- Post header-->
					<header class="mb-4">
						<h1 class="fw-bolder mb-1">Welcome to Administrator panel</h1>
					</header>
				</article>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>
