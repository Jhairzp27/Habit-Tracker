<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Sistema de Gestión de Hábitos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/view/Styles/styles.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #0C2B4E 0%, #1A3D64 50%, #1D546C 100%);
            min-height: 100vh;
            position: relative;
        }

        .login-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 40px 20px 80px 20px;
        }

        .login-container {
            background: white;
            padding: 60px 80px 70px 80px;
            border-radius: 25px;
            box-shadow: 0 25px 70px rgba(0, 0, 0, 0.5);
            max-width: 650px;
            width: 100%;
        }

        .login-header {
            text-align: center;
            margin-bottom: 80px;
            padding-bottom: 30px;
        }

        .login-logo {
            width: 80px;
            height: 80px;
            background: linear-gradient(135deg, #0C2B4E 0%, #1D546C 100%);
            border-radius: 50%;
            margin: 0 auto 35px auto;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 8px 20px rgba(12, 43, 78, 0.3);
        }

        .login-logo svg {
            width: 45px;
            height: 45px;
            color: white;
        }

        .login-header h1 {
            color: var(--primary);
            font-size: 38px;
            margin-bottom: 15px;
            font-weight: 700;
        }

        .login-header p {
            color: #666;
            font-size: 17px;
        }

        .form-group {
            margin-bottom: 30px;
        }

        .form-group label {
            display: block;
            color: var(--primary);
            font-weight: 600;
            margin-bottom: 12px;
            font-size: 16px;
        }

        .form-group input {
            width: 100%;
            padding: 18px 20px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 16px;
            transition: all 0.3s;
            box-sizing: border-box;
        }

        .form-group input:focus {
            outline: none;
            border-color: var(--accent);
            box-shadow: 0 0 0 3px rgba(29, 84, 108, 0.1);
        }

        .btn-login {
            width: 100%;
            padding: 18px;
            background: linear-gradient(135deg, #0C2B4E 0%, #1D546C 100%);
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            margin-top: 15px;
            box-shadow: 0 8px 20px rgba(12, 43, 78, 0.3);
        }

        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 12px 30px rgba(12, 43, 78, 0.4);
        }

        .btn-login:active {
            transform: translateY(0);
        }

        .error-message {
            background: #fee;
            color: #c33;
            padding: 15px 18px;
            border-radius: 10px;
            margin-bottom: 25px;
            font-size: 15px;
            text-align: center;
            border: 1px solid #fcc;
        }

        .login-footer {
            position: fixed;
            bottom: 0;
            left: 0;
            right: 0;
            text-align: center;
            padding: 20px;
            color: rgba(255, 255, 255, 0.8);
            font-size: 14px;
        }

        .divider {
            text-align: center;
            margin: 35px 0;
            position: relative;
        }

        .divider::before {
            content: '';
            position: absolute;
            left: 0;
            right: 0;
            top: 50%;
            height: 1px;
            background: #e0e0e0;
        }

        .divider span {
            background: white;
            padding: 0 15px;
            position: relative;
            color: #999;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="login-wrapper">
        <div class="login-container">
            <div class="login-header">
                <div class="login-logo">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                        <circle cx="12" cy="7" r="4"></circle>
                    </svg>
                </div>
                <h1>Iniciar Sesión</h1>
                <p>Sistema de Gestión de Hábitos</p>
            </div>

            <% 
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
                <div class="error-message">
                    <%= error %>
                </div>
            <% } %>

            <form action="<%= request.getContextPath() %>/LoginController" method="post">
                <div class="form-group">
                    <label for="correoElectronico">Correo Electrónico</label>
                    <input type="email" 
                           id="correoElectronico" 
                           name="correoElectronico" 
                           placeholder="usuario@ejemplo.com"
                           required>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" 
                           id="password" 
                           name="password" 
                           placeholder="••••••••"
                           required>
                </div>

                <button type="submit" class="btn-login">Iniciar Sesión</button>
            </form>
        </div>
    </div>
    
    <div class="login-footer">
        <p>&copy; 2026 Sistema de Gestión de Hábitos. Todos los derechos reservados.</p>
    </div>
</body>
</html>
