<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">

<style>
table {
	border-collapse: collapse;
	border-spacing: 1;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}
</style>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<title>Corretora ABC</title>
</head>
<body>
	<jsp:include page="WEB-INF/template/header.jsp"></jsp:include>

	<table class="row justify-content-center">
		<tbody>
			<tr>
				<td><span style="font-size: 18px;">Magazine Luiza S.A 1D BMF BOVESPA</span></td>
			</tr>
			<tr>
				<td>
					<div id="chart_div" style="width: 1000px; height: 600px;">
						<script type="text/javascript" src="js/chartGoogle.js"></script>
					</div> &nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<div id="number_format_chart" class="row justify-content-center">
						<script type="text/javascript" src="js/chartGoogle2.js"></script>
					</div> &nbsp;
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>