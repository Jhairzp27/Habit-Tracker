<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Hábito - Habit Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/Styles/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&display=swap" rel="stylesheet">
    
    <style>
        /* Estilos específicos para la lista */
        .habitos-list { display: flex; flex-direction: column; gap: 15px; margin-top: 20px; }
        
        .habito-item {
            background: white; 
            border: 1px solid #e2e8f0; 
            border-radius: 12px; 
            padding: 20px;
            display: flex; 
            justify-content: space-between; 
            align-items: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05); 
            transition: transform 0.2s, border-color 0.2s;
        }
        
        .habito-item:hover { 
            transform: translateY(-2px); 
            border-color: #94a3b8; 
        }
        
        .habito-badge { 
            background-color: #e0f2fe; 
            color: #0284c7; 
            padding: 4px 10px; 
            border-radius: 20px; 
            font-size: 0.8rem; 
            font-weight: bold; 
        }
        
        .habito-schedule { 
            text-align: right; 
            font-size: 0.9rem; 
            color: #64748b; 
        }
        
        .btn-edit {
            background-color: #2c3e50; 
            color: white; 
            padding: 8px 16px; 
            border-radius: 8px;
            text-decoration: none; 
            font-size: 0.9rem; 
            font-weight: 600; 
            display: inline-block;
            transition: background-color 0.2s;
        }
        
        .btn-edit:hover { 
            background-color: #1e293b; 
        }
        
        .empty-state { 
            text-align: center; 
            padding: 40px; 
            color: #94a3b8; 
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <header class="dashboard-header">
            <div class="header-content">
                <h1>Habit Tracker</h1>
                <div class="user-info">
                    <a href="${pageContext.request.contextPath}/view/GestionarHabitos.jsp" class="btn-back">← Volver</a>
                </div>
            </div>
        </header>

        <main class="dashboard-main">
            <div class="form-container" style="max-width: 900px; width: 95%;">
                <div class="form-header">
                    <h2>Modificar Hábitos</h2>
                    <p>Selecciona un hábito de la lista para editar sus detalles</p>
                </div>

                <div class="habitos-list">
                    
                    <c:if test="${empty listaHabitos}">
                        <div class="empty-state">
                            <h3>No hay hábitos registrados</h3>
                            <p>Crea un nuevo hábito primero para poder modificarlo.</p>
                            <a href="${pageContext.request.contextPath}/view/GestionarHabitos.jsp" class="btn-back" style="display:inline-block; margin-top:10px;">Ir al Inicio</a>
                        </div>
                    </c:if>

                    <c:forEach items="${listaHabitos}" var="h">
                        <div class="habito-item">
                            <div class="habito-info">
                                <h3 style="margin: 0 0 5px 0; color: #334155;">${h.nombre}</h3>
                                <span class="habito-badge">${h.categoria.nombre}</span>
                            </div>
                            
                            <div class="habito-schedule">
                                <c:choose>
                                    <c:when test="${h.frecuencia > 0}">
                                        <p>📅 ${h.dia}</p>
                                        <p>⏰ <fmt:formatDate value="${h.horario}" pattern="hh:mm a" /></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p style="color: #f59e0b;">⚠ Sin planificar</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <a href="${pageContext.request.contextPath}/ModificarHabitoController?ruta=cargar&id=${h.idHabito}" class="btn-edit">
                                Editar →
                            </a>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </main>
    </div>
</body>
</html>