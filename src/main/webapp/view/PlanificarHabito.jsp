<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Planificar Hábitos - Habit Tracker</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/Styles/styles.css">
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&display=swap" rel="stylesheet">

    <style>
        .habitos-list {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }

        .habito-item {
            background: white;
            border: 1px solid #e2e8f0; /* Borde gris suave */
            border-radius: 16px;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            transition: all 0.2s ease;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
            /* YA NO TIENE CURSOR POINTER EN TODA LA TARJETA */
        }

        .habito-item:hover {
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.05);
            border-color: #cbd5e1;
        }

        .habito-info h3 {
            margin: 0 0 8px 0;
            color: #1e293b;
            font-size: 1.2rem;
            font-weight: 700;
        }

        .habito-badge {
            background-color: #f1f5f9;
            color: #475569;
            padding: 4px 12px;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
            display: inline-block;
        }

        /* --- AQUÍ ARREGLAMOS EL COLOR NARANJA --- */
        .habito-schedule {
            text-align: right;
            font-size: 0.9rem;
            margin-right: 20px; /* Espacio antes del botón */
        }

        .text-date {
            color: #94a3b8;
            margin-bottom: 4px;
            display: block;
        }

        .status-warning {
            /* Forzamos el color naranja fuerte */
            color: #ea580c !important; 
            font-weight: 700;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            gap: 6px;
            background-color: #ffedd5; /* Fondo naranja muy suave */
            padding: 4px 10px;
            border-radius: 8px;
        }

        /* --- BOTÓN "SELECCIONAR HÁBITO" --- */
        .btn-select {
            background-color: #2c3e50; /* Color oscuro elegante */
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 10px;
            font-weight: 600;
            font-size: 0.95rem;
            transition: background-color 0.2s;
            white-space: nowrap; /* Evita que el texto se parta */
        }

        .btn-select:hover {
            background-color: #1a252f;
            transform: translateY(-1px);
        }

        /* Estado Vacío */
        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: #64748b;
        }
        .empty-icon {
            font-size: 4rem;
            margin-bottom: 15px;
            display: block;
            opacity: 0.5;
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
                    <h2>Planificar Hábitos</h2>
                    <p>Selecciona un hábito pendiente para definir sus días y horarios</p>
                </div>

                <div class="habitos-list">
                    
                    <c:if test="${empty habitosPendientes}">
                        <div class="empty-state">
                            <span class="empty-icon">🎉</span>
                            <h3>¡Todo al día!</h3>
                            <p>No tienes hábitos pendientes de planificar.</p>
                            <!-- 
                            <a href="${pageContext.request.contextPath}/view/GestionarHabitos.jsp" class="btn-select" style="margin-top:20px; display:inline-block;">Volver al Inicio</a>
                             -->
                        </div>
                    </c:if>

                    <c:forEach items="${habitosPendientes}" var="h">
                        <div class="habito-item">
                            
                            <div class="habito-info">
                                <h3>${h.nombre}</h3>
                                <span class="habito-badge">${h.categoria.nombre}</span>
                            </div>

                            <div class="habito-schedule">
                                <span class="text-date">Creado: ${h.fechaInicio}</span>
                                <span class="status-warning">
                                    <span>⚠</span> Sin Planificar
                                </span>
                            </div>

                            <a href="${pageContext.request.contextPath}/PlanificarHabitoController?ruta=seleccionarHabito&id=${h.idHabito}" 
                               class="btn-select">
                                Seleccionar hábito
                            </a>

                        </div>
                    </c:forEach>

                </div>
            </div>
        </main>
    </div>
</body>
</html>