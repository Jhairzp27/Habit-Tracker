<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Habit Tracker</title>
    <link rel="stylesheet" href="Styles/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Boldonse&family=Cal+Sans&family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Lexend+Deca:wght@100..900&family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&family=Noto+Sans+Bhaiksuki&family=Space+Grotesk:wght@300..700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        <header class="dashboard-header">
            <div class="header-content">
                <h1>Habit Tracker</h1>
                <div class="user-info">
                    <span>
                        <% 
                            String nombreUsuario = (String) session.getAttribute("usuarioNombre");
                            String correoUsuario = (String) session.getAttribute("usuarioCorreo");
                            if (nombreUsuario != null) {
                                out.print(nombreUsuario);
                            } else if (correoUsuario != null) {
                                out.print(correoUsuario);
                            } else {
                                out.print("Usuario");
                            }
                        %>
                    </span>
                    <a href="<%= request.getContextPath() %>/LogoutController" class="btn-logout">Cerrar Sesión</a>
                </div>
            </div>
        </header>

        <main class="dashboard-main">
            <div class="welcome-section">
                <h2>Bienvenido a tu Dashboard</h2>
                <p>Gestiona tus hábitos de manera efectiva</p>
            </div>

            <div class="modules-grid">
                <div class="module-card">
                    <div class="module-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <circle cx="12" cy="12" r="10"></circle>
                            <line x1="12" y1="8" x2="12" y2="16"></line>
                            <line x1="8" y1="12" x2="16" y2="12"></line>
                        </svg>
                    </div>
                    <h3>Crear Hábito</h3>
                    <p>Define un nuevo hábito que quieras desarrollar</p>
                    <a href="../CrearHabitoController" class="module-btn">Crear</a>
                </div>

                <div class="module-card">
                    <div class="module-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                            <line x1="16" y1="2" x2="16" y2="6"></line>
                            <line x1="8" y1="2" x2="8" y2="6"></line>
                            <line x1="3" y1="10" x2="21" y2="10"></line>
                        </svg>
                    </div>
                    <h3>Planificar Hábito</h3>
                    <p>Organiza tus hábitos en un calendario</p>
                    <a href="../PlanificarHabitoController" class="module-btn">Planificar</a>
                </div>

                <div class="module-card">
                    <div class="module-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <line x1="18" y1="20" x2="18" y2="10"></line>
                            <line x1="12" y1="20" x2="12" y2="4"></line>
                            <line x1="6" y1="20" x2="6" y2="14"></line>
                        </svg>
                    </div>
                    <h3>Modificar Hábito</h3>
                    <p>Visualiza el progreso de tus hábitos</p>
                    <a href="../ModificarHabitoController" class="module-btn">Modificar</a>
                </div>
                
                <div class="module-card">
                    <div class="module-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                            <polyline points="14 2 14 8 20 8"></polyline>
                            <line x1="16" y1="13" x2="8" y2="13"></line>
                            <line x1="16" y1="17" x2="8" y2="17"></line>
                            <polyline points="10 9 9 9 8 9"></polyline>
                        </svg>
                    </div>
                    <h3>Eliminar Hábito</h3>
                    <p>Registra el cumplimiento de tus hábitos</p>
                    <a href="../EliminarHabitoController" class="module-btn">Eliminar</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>