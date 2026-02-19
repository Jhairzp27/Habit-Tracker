<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aviso - Habit Tracker</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/Styles/styles.css">
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&display=swap" rel="stylesheet">

    <style>
        /* Estilos específicos para centrar la tarjeta de error */
        body {
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Ocupa toda la altura de la pantalla */
            margin: 0;
            font-family: 'DM Sans', sans-serif;
        }

        .error-card {
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            text-align: center;
            max-width: 400px;
            width: 90%;
            border-top: 6px solid #e74c3c; /* Línea roja arriba */
        }

        .icon-circle {
            width: 70px;
            height: 70px;
            background-color: #fdeaea;
            color: #e74c3c;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
            font-weight: bold;
            margin: 0 auto 20px;
        }

        h1 {
            color: #2c3e50;
            margin: 0 0 10px 0;
            font-size: 1.5rem;
        }

        p {
            color: #64748b;
            margin-bottom: 30px;
            line-height: 1.6;
            font-size: 1rem;
        }

        .btn-accept {
            display: inline-block;
            background-color: #2c3e50;
            color: white;
            padding: 12px 40px;
            border-radius: 30px;
            text-decoration: none;
            font-weight: 600;
            transition: transform 0.2s, background-color 0.2s;
            border: none;
            cursor: pointer;
        }

        .btn-accept:hover {
            background-color: #1a252f;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

    <div class="error-card">
        <div class="icon-circle">!</div>

        <h1>Atención</h1>

        <p>
            <c:choose>
                <c:when test="${not empty mensajeError}">
                    ${mensajeError}
                </c:when>
                <c:otherwise>
                    Ha ocurrido un error inesperado al procesar tu solicitud.
                </c:otherwise>
            </c:choose>
        </p>

        <a href="${not empty urlDestino ? urlDestino : pageContext.request.contextPath.concat('/view/GestionarHabitos.jsp')}"
           class="btn-accept">
            Aceptar
        </a>
    </div>

</body>
</html>