<%@page import="com.flyway.models.User"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="./HomeServlet"> <i
			class="fa-solid fa-plane-arrival"></i> &nbsp;&nbsp; Flyway
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="./HomeServlet">Book
						Flight</a></li>
						
				<li class="nav-item"><a class="nav-link" href="./UserServlet">Register</a></li>

				<%
					if (session.getAttribute("USER") != null) {
						User user = (User) session.getAttribute("USER");
						String displayName = user.getFirstName() + " " + user.getLastName();
				%>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">
						<%=displayName%>
				</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="./LogoutServlet">Logout</a></li>
					</ul></li>
				<%
					} else {
				%>
				<li class="nav-item"><a class="nav-link" href="./LoginServlet">Login</a></li>
				<%
					}
				%>


			</ul>
		</div>
	</div>
</nav>