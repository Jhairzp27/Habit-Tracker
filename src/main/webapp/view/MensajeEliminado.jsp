<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hábito Eliminado - Habit Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/Styles/styles.css">
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
            <div class="form-container">
                <div class="form-header">
                    <h2>Hábito eliminado</h2>
                    <p>La operación se completó correctamente.</p>
                </div>

                <div class="habit-stat-card">
                    <p style="color:var(--primary); font-weight:600; margin-bottom:12px;">
                        ${not empty mensaje ? mensaje : 'El hábito se eliminó correctamente.'}
                    </p>
                    
                    <div style="display:flex; gap:12px;">
                        
                        <a href="${pageContext.request.contextPath}/EliminarHabitoController?accion=listar" class="module-btn">
                            Volver a eliminar
                        </a>

                        <a href="${pageContext.request.contextPath}/view/GestionarHabitos.jsp" class="btn-cancel">
                            Ir al Dashboard
                        </a>
                    </div>
                </div>

            </div>
        </main>
    </div>
</body>
</html>