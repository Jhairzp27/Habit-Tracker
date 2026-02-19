<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hábito Guardado</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/Styles/styles.css">

<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f4f7f6;
	font-family: 'Arial', sans-serif;
	margin: 0;
}

.success-card {
	background: white;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
	text-align: center;
	max-width: 400px;
	width: 100%;
}

.icon-circle {
	width: 80px;
	height: 80px;
	background-color: #e8f5e9;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 auto 20px;
}

.check-icon {
	color: #4caf50;
	font-size: 40px;
	font-weight: bold;
}

h1 {
	color: #2c3e50;
	margin-bottom: 10px;
	font-size: 24px;
}

p {
	color: #7f8c8d;
	margin-bottom: 30px;
}

.btn-continue {
	background-color: #4caf50;
	color: white;
	padding: 12px 30px;
	border: none;
	border-radius: 25px;
	font-size: 16px;
	cursor: pointer;
	text-decoration: none;
	transition: background-color 0.3s;
	display: inline-block;
}

.btn-continue:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
<body>
	<div class="success-card">
		<div class="icon-circle">
			<span class="check-icon">&#10003;</span>
		</div>

		<h1>¡Excelente!</h1>

		<p>
			<c:choose>
				<c:when test="${not empty param.mensaje}">
                    ${param.mensaje}
                </c:when>
				<c:otherwise>
                    La operación se realizó con éxito.
                </c:otherwise>
			</c:choose>
		</p>

		<a
			href="${not empty param.urlDestino ? param.urlDestino : pageContext.request.contextPath.concat('/view/GestionarHabitos.jsp')}"
			class="btn-continue"> Aceptar </a>
	</div>
</body>

</body>
</html>