<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Planificar Hábito - Habit Tracker</title>
    
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
                    <a href="${pageContext.request.contextPath}/PlanificarHabitoController?ruta=listar" class="btn-back">← Volver</a>
                </div>
            </div>
        </header>

        <main class="dashboard-main">
            <div class="form-container">
                <div class="form-header">
                    <h2>Planificar: ${habito.nombre}</h2>
                    <p>Define los días, horarios y tareas para este hábito</p>
                </div>

                <form method="POST" 
                      action="${pageContext.request.contextPath}/PlanificarHabitoController" 
                      class="habit-form" 
                      id="planificarForm" 
                      novalidate>
                      
                    <input type="hidden" name="ruta" value="guardar">
                    <input type="hidden" name="id" value="${habito.idHabito}">

                    <div class="form-section">
                        <h3>Días de la Semana <span class="required">*</span></h3>
                        <div class="days-grid">
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="lunes"><span class="day-label">Lun</span></label>
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="martes"><span class="day-label">Mar</span></label>
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="miercoles"><span class="day-label">Mié</span></label>
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="jueves"><span class="day-label">Jue</span></label>
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="viernes"><span class="day-label">Vie</span></label>
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="sabado"><span class="day-label">Sáb</span></label>
                            <label class="day-checkbox"><input type="checkbox" name="dias" value="domingo"><span class="day-label">Dom</span></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="hora">Hora <span class="required">*</span></label>
                        <input type="time" id="hora" name="hora">
                    </div>

                    <div class="form-section">
                        <h3>Tareas Asociadas</h3>
                        <p class="section-description">Agrega acciones específicas para cumplir este hábito</p>
                        
                        <div id="tareasContainer">
                            <div class="tarea-item">
                                <input type="text" name="tareas" class="tarea-input" placeholder="Ej: Ir al gimnasio">
                                <button type="button" class="btn-remove-tarea" onclick="removeTarea(this)">✕</button>
                            </div>
                        </div>
                        
                        <button type="button" class="btn-add-tarea" onclick="addTarea()">+ Agregar otra tarea</button>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-submit">Guardar Planificación</button>
                    </div>
                </form>
            </div>
        </main>
    </div>

    <script>
        function addTarea() {
            const container = document.getElementById('tareasContainer');
            const tareaItem = document.createElement('div');
            tareaItem.className = 'tarea-item';
            tareaItem.innerHTML = `
                <input type="text" name="tareas" class="tarea-input" placeholder="Nueva tarea...">
                <button type="button" class="btn-remove-tarea" onclick="removeTarea(this)">✕</button>
            `;
            container.appendChild(tareaItem);
        }

        function removeTarea(button) {
            const tareasContainer = document.getElementById('tareasContainer');
            // Evitar borrar el último input para que siempre quede uno
            if (tareasContainer.children.length > 1) {
                button.parentElement.remove();
            } else {
                // Si es el último, solo limpiamos el valor
                button.parentElement.querySelector('input').value = '';
            }
        }
    </script>
</body>
</html>