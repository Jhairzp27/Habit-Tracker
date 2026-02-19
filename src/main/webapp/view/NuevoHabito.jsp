<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Nuevo Hábito - Habit Tracker</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/Styles/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Boldonse&family=Cal+Sans&family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&display=swap" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        <header class="dashboard-header">
            <div class="header-content">
                <h1>Habit Tracker</h1>
                <div class="user-info">
                    <a href="${pageContext.request.contextPath}/GestionarHabitoController" class="btn-back">← Volver</a>
                </div>
            </div>
        </header>

        <main class="dashboard-main">
            <div class="form-container">
                <div class="form-header">
                    <h2>Crear Nuevo Hábito</h2>
                    <p>Completa los campos para definir tu nuevo hábito</p>
                </div>

                <form method="POST"
                    action="${pageContext.request.contextPath}/CrearHabitoController"
                    class="habit-form" id="habitForm" novalidate>

                    <input type="hidden" name="ruta" value="guardar">

                    <div class="form-group">
                        <label for="nombre">Nombre del Hábito <span class="required">*</span></label> 
                        <input type="text" id="nombre" name="nombre" placeholder="Ej: Hacer ejercicio">
                    </div>

                    <div class="form-group">
                        <label for="categoria">Categoría <span class="required">*</span></label>
                        <select id="categoria" name="categoria">
                            <option value="">Selecciona una categoría</option>
                            <c:forEach items="${categorias}" var="cat">
                                <option value="${cat.idCategoria}">${cat.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="descripcion">Descripción (Opcional)</label>
                        <textarea id="descripcion" name="descripcion" rows="4" placeholder="Describe tu hábito..."></textarea>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-submit">Crear Hábito</button>
                    </div>
                </form>
            </div>
        </main>
        </div>
</body>
</html>