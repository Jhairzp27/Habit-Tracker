<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Verificar que el usuario esté autenticado y sea admin
    if (session.getAttribute("usuarioId") == null) {
        response.sendRedirect(request.getContextPath() + "/LoginController");
        return;
    }
    
    String rol = (String) session.getAttribute("usuarioRol");
    if (!"admin".equalsIgnoreCase(rol)) {
        response.sendRedirect(request.getContextPath() + "/view/GestionarHabitos.jsp");
        return;
    }
    
    String nombreUsuario = (String) session.getAttribute("usuarioNombre");
    String apellidoUsuario = (String) session.getAttribute("usuarioApellido");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administración - Sistema de Gestión de Hábitos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/view/Styles/styles.css">
    <style>
        body {
            background: #f5f5f5;
        }

        .dashboard-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            background: var(--primary);
            color: white;
            padding: 20px 30px;
            border-radius: 10px;
            margin-bottom: 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header h1 {
            font-size: 24px;
            margin-bottom: 5px;
        }

        .header p {
            font-size: 14px;
            opacity: 0.9;
        }

        .user-info {
            text-align: right;
        }

        .btn-logout {
            background: white;
            color: var(--primary);
            padding: 8px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }

        .btn-logout:hover {
            background: #e0e0e0;
        }

        .cards-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .card {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .card h3 {
            color: var(--primary);
            margin-bottom: 15px;
            font-size: 18px;
        }

        .card p {
            color: #666;
            line-height: 1.6;
            margin-bottom: 15px;
        }

        .card-actions {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s;
        }

        .btn-primary {
            background: var(--primary);
            color: white;
        }

        .btn-primary:hover {
            background: var(--secondary);
        }

        .btn-secondary {
            background: var(--accent);
            color: white;
        }

        .btn-secondary:hover {
            background: #1A3D64;
        }

        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .stat-card .number {
            font-size: 36px;
            font-weight: bold;
            color: var(--primary);
            margin-bottom: 5px;
        }

        .stat-card .label {
            color: #666;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="header">
            <div>
                <h1>Panel de Administración</h1>
                <p>Bienvenido al sistema de gestión de hábitos</p>
            </div>
            <div class="user-info">
                <p><strong><%= nombreUsuario %> <%= apellidoUsuario %></strong></p>
                <p>Rol: Administrador</p>
                <a href="<%= request.getContextPath() %>/LogoutController" class="btn-logout">Cerrar Sesión</a>
            </div>
        </div>

        <div class="stats-grid">
            <div class="stat-card">
                <div class="number">0</div>
                <div class="label">Usuarios Totales</div>
            </div>
            <div class="stat-card">
                <div class="number">0</div>
                <div class="label">Hábitos Activos</div>
            </div>
            <div class="stat-card">
                <div class="number">0</div>
                <div class="label">Categorías</div>
            </div>
            <div class="stat-card">
                <div class="number">0</div>
                <div class="label">Tareas Completadas</div>
            </div>
        </div>

        <div class="cards-container">
            <div class="card">
                <h3>Gestión de Usuarios</h3>
                <p>Administra los usuarios del sistema, crea nuevos usuarios, modifica sus datos o elimina cuentas.</p>
                <div class="card-actions">
                    <a href="#" class="btn btn-primary">Ver Usuarios</a>
                    <a href="#" class="btn btn-secondary">Nuevo Usuario</a>
                </div>
            </div>

            <div class="card">
                <h3>Gestión de Hábitos</h3>
                <p>Visualiza y gestiona todos los hábitos del sistema, revisa estadísticas y tendencias.</p>
                <div class="card-actions">
                    <a href="<%= request.getContextPath() %>/view/GestionarHabitos.jsp" class="btn btn-primary">Ver Hábitos</a>
                </div>
            </div>

            <div class="card">
                <h3>Gestión de Categorías</h3>
                <p>Administra las categorías de hábitos disponibles en el sistema.</p>
                <div class="card-actions">
                    <a href="#" class="btn btn-primary">Ver Categorías</a>
                    <a href="#" class="btn btn-secondary">Nueva Categoría</a>
                </div>
            </div>

            <div class="card">
                <h3>Reportes y Estadísticas</h3>
                <p>Consulta reportes detallados sobre el uso del sistema y el progreso de los usuarios.</p>
                <div class="card-actions">
                    <a href="#" class="btn btn-primary">Ver Reportes</a>
                </div>
            </div>

            <div class="card">
                <h3>Configuración del Sistema</h3>
                <p>Configura parámetros generales del sistema y opciones de administración.</p>
                <div class="card-actions">
                    <a href="#" class="btn btn-primary">Configuración</a>
                </div>
            </div>

            <div class="card">
                <h3>Logs y Actividad</h3>
                <p>Revisa los registros de actividad del sistema y acciones de los usuarios.</p>
                <div class="card-actions">
                    <a href="#" class="btn btn-primary">Ver Logs</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
