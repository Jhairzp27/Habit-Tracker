#!/bin/bash
set -e

echo "📂 Iniciando script de despliegue..."

# Navegar a la carpeta
cd /usr/local/tomcat/webapps/

# --- DEBUG: MOSTRAR QUÉ HAY AQUÍ ---
echo "👀 Contenido de la carpeta webapps:"
ls -la
# -----------------------------------

# Verificar si el archivo existe antes de intentar descomprimir
if [ -f "ROOT.war" ]; then
    echo "✅ ROOT.war encontrado. Desempaquetando..."
    unzip -o ROOT.war -d ROOT > /dev/null
    rm ROOT.war
else
    echo "❌ ERROR CRÍTICO: No se encuentra ROOT.war en $(pwd)"
    echo "Esto puede pasar si Maven no generó el .war o Docker copió una carpeta."
    exit 1
fi

# Inyectar credenciales
echo "🔒 Inyectando credenciales de base de datos..."
CONFIG_FILE="ROOT/WEB-INF/classes/META-INF/persistence.xml"

if [ -f "$CONFIG_FILE" ]; then
    # Usamos | como separador para evitar errores con las barras de la URL
    sed -i "s|{{DB_URL}}|$DB_URL|g" "$CONFIG_FILE"
    sed -i "s|{{DB_USER}}|$DB_USER|g" "$CONFIG_FILE"
    sed -i "s|{{DB_PASSWORD}}|$DB_PASSWORD|g" "$CONFIG_FILE"
    echo "✅ Credenciales inyectadas."
else
    echo "⚠️ ADVERTENCIA: No se encontró persistence.xml"
fi

echo "🚀 Iniciando Tomcat..."
catalina.sh run