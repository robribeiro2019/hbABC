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

<title>Corretora ABC</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>

	<table class="row justify-content-center">
		<tbody>
			<tr>
				<td><span style="font-size: 18px;">Magazine Luiza S.A 1D BMF BOVESPA</span></td>
			</tr>
			<tr>
				<td>
					<div id="chart_div" style="width: 1000px; height: 600px;">
						<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/chartGoogle.js"></script>
					</div> &nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<div id="number_format_chart" class="row justify-content-center">
						<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/chartGoogle.js"></script>
					</div> &nbsp;
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>