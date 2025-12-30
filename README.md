# 🏍️ El Tumbómetro

**Rodar, medir y compartir.**

Bienvenido a **El Tumbómetro**, una aplicación web diseñada para moteros que quieren llevar un registro detallado de sus rutas, gestionar su garaje virtual y compartir experiencias con la comunidad.

---

## 🛠️ Stack Tecnológico

Este proyecto utiliza una arquitectura monolítica moderna basada en Java y Spring Boot para el backend, con Tailwind CSS para un diseño ágil y responsivo.

*   **Backend:** Java 21, Spring Boot 3 (Spring MVC, Spring Data JPA).
*   **Base de Datos:** PostgreSQL.
*   **Frontend:** Thymeleaf (Motor de plantillas), Tailwind CSS v3.4.
*   **Gestión de Dependencias:** Maven (Backend) y NPM (Frontend).

---

## 📋 Requisitos Previos

Para desplegar este proyecto en local, necesitas tener instalado:

1.  **Java JDK 21**: [Descargar Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java21) o OpenJDK.
2.  **Node.js & NPM**: Necesario para compilar los estilos de Tailwind. [Descargar Node.js](https://nodejs.org/).
3.  **PostgreSQL**: Base de datos relacional. [Descargar PostgreSQL](https://www.postgresql.org/download/).

---

## 🚀 Guía de Instalación y Despliegue

Sigue estos pasos en orden para poner en marcha la aplicación:

### 1. Clonar el repositorio
Descarga el proyecto en tu máquina local:

```bash
git clone https://github.com/TU_USUARIO/ElTumbometro.git
cd ElTumbometro
```

### 2. Configuración de Base de Datos
Crea una base de datos vacía en PostgreSQL llamada `eltumbometro`.

A continuación, abre el archivo de configuración del proyecto ubicado en:
`src/main/resources/application.properties`

Verifica que las credenciales coincidan con tu instalación local de PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/eltumbometro
spring.datasource.username=tu_usuario_postgres
spring.datasource.password=tu_contraseña_postgres
spring.jpa.hibernate.ddl-auto=update
```

### 3. Instalación de dependencias Frontend (Tailwind CSS)
El proyecto utiliza Tailwind CSS, por lo que es necesario compilar los estilos antes de iniciar la aplicación para que se vea correctamente.

Desde la raíz del proyecto, ejecuta:

```bash
# 1. Instalar dependencias de Node (creará la carpeta node_modules)
npm install

# 2. Generar el archivo CSS final
npx tailwindcss -i ./src/main/resources/static/css/input.css -o ./src/main/resources/static/css/style.css
```

> **Nota para desarrollo:** Si vas a modificar los estilos, puedes dejar corriendo el comando con la bandera `--watch` para que se actualice automáticamente:
> `npx tailwindcss -i ... -o ... --watch`

### 4. Ejecución del Backend
Una vez generados los estilos y configurada la base de datos, inicia el servidor de Spring Boot.

**Opción A: Usando Maven Wrapper (Terminal)**
```bash
# En Windows
./mvnw spring-boot:run

# En Linux / Mac
./mvnw spring-boot:run
```

**Opción B: Desde tu IDE (IntelliJ IDEA, Eclipse...)**
1.  Abre el proyecto como proyecto Maven.
2.  Localiza la clase principal: `com.jaime.eltumbometro.ElTumbometroApplication`.
3.  Ejecuta la clase (Run).

---

## 🌐 Acceso a la Aplicación

Una vez que veas en la consola que la aplicación ha arrancado correctamente, abre tu navegador favorito e ingresa a:

👉 **[http://localhost:8080](http://localhost:8080)**

---

## 📂 Estructura del Proyecto

```text
src/
├── main/
│   ├── java/com/jaime/eltumbometro/  # Código fuente Backend (Controladores, Modelos...)
│   └── resources/
│       ├── static/                   # Archivos estáticos (CSS generado, Imágenes, JS)
│       └── templates/                # Vistas HTML con Thymeleaf
└── ...
```

---

Hecho con ❤️ y mucho ✊💨 gas.