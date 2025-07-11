# Laboratorio 1 - Taller de Base de Datos/Bases de datos Avanzadas
## Este laboratorio fue desarrollado por el grupo 2
### Integrantes de grupo 2
* Benjamin Sepulveda
* Alan Berrios
* Camila Gárate
* Sebastian De La Fuente
* Amalia Viaux

# Herramientas de desarrollo

## Base de datos
* Postgres SQL
* PgAdmin versión 4
* Postgis


## Backend
* IntelliJ IDEA Ultimate
* JDK versión 17
* Postman

## Frontend
* Visual Studio Code
* VUE versión 3
* nuxt 3

# Guía de Instalación y Ejecución
Esta guía te ayudará a configurar tu entorno de desarrollo para trabajar con IntelliJ IDEA, PostgreSQL, pgAdmin y ejecutar un proyecto de Vue.js en la carpeta de frontend.

## 1. Clonar los repositorios

git clone https://github.com/Sepitoooo19/Front-Lab2-TBD (para frontend)
git clone https://github.com/Sepitoooo19/Lab2-TBD (para backend)

## 2. Instalación de IntelliJ IDEA

1. Descarga IntelliJ IDEA desde el sitio web oficial: [Descargar IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

2. Instala IntelliJ IDEA siguiendo las instrucciones específicas para tu sistema operativo.

## 3. Instalación de PostgreSQL y pgAdmin

### 3.1 Instalación de PostgreSQL

1. Descarga PostgreSQL desde el sitio web oficial: [Descargar PostgreSQL](https://www.postgresql.org/download/).

2. Sigue las instrucciones de instalación para tu sistema operativo.

### 3.2 Instalación de pgAdmin

1. Descarga pgAdmin desde el sitio web oficial: [Descargar pgAdmin](https://www.pgadmin.org/download/).

2. Instala pgAdmin siguiendo las instrucciones para tu sistema operativo.

3. Instala la extensión de postgis desde la app Application Stack Builder

## 4. Crear base de datos y cargar información

### 4.1 Crear base de datos

1. Asegúrate de estar conectado a PostgreSQL con pgAdmin:
   Abre pgAdmin y conéctate a tu servidor PostgreSQL.
   Si aún no tienes una conexión configurada, puedes hacerlo siguiendo las instrucciones de pgAdmin.

2. Selecciona el servidor y la base de datos:
   En el panel izquierdo de pgAdmin, selecciona tu servidor PostgreSQL.
   Luego, selecciona la base de datos en la que deseas ejecutar el script SQL. Si no tienes una base de datos existente, puedes crear una haciendo clic derecho en "Databases" y seleccionando "Create" > "Database".

3. Ejecuta el Script SQL:
   Haz clic con el botón derecho en la base de datos seleccionada y elige "Query Tool" para abrir una ventana donde podrás ejecutar consultas SQL.

4. En la ventana del "Query Tool", abre el archivo SQL que contiene el script que deseas ejecutar. Puedes hacerlo seleccionando "File" > "Open" y navegando hasta el archivo.

5. Una vez abierto el archivo SQL, puedes ejecutarlo haciendo clic en el botón "Execute" o presionando la tecla F5.

6. Verifica la creación de la base de datos:
   Después de ejecutar el script, verifica que la base de datos y las tablas se hayan creado correctamente utilizando las funciones de pgAdmin.

### 4.2 Cargar la información

1. Abre pgAdmin y conéctate a tu servidor PostgreSQL.

2. Selecciona la base de datos en la que deseas cargar los datos.

3. Abre el "Query Tool" haciendo clic con el botón derecho en la base de datos seleccionada y elige "Query Tool".

4. Abre tu archivo SQL que contiene el script para cargar datos utilizando la opción "File" > "Open" en la ventana del "Query Tool".

5. Ejecuta el script SQL haciendo clic en el botón "Execute" o presionando la tecla `F5`.

### 4.3 Verificar la Carga de Datos

Después de ejecutar el script, verifica que los datos se hayan cargado correctamente en las tablas correspondientes. Puedes hacerlo consultando las tablas o ejecutando consultas SELECT para verificar que los datos estén presentes.

Asegúrate de que el script SQL esté formateado y escrito correctamente para evitar errores durante la carga de datos. Además, verifica que las tablas y columnas mencionadas en el script coincidan con la estructura de tu base de datos.

¡Listo! Ahora estás preparado para cargar datos en tu base de datos PostgreSQL desde un script SQL.


## 5. Ejecución del proyecto

### 5.1 Ejecución Frontend por vue

Asumiendo que ya tienes un proyecto de Vue.js en tu carpeta de frontend, sigue estos pasos para ejecutarlo:

1. Abre una terminal en la carpeta de tu proyecto Vue.js.

2. Asegúrate de haber instalado Node.js y npm. Si no los tienes instalados, puedes descargarlos desde [https://nodejs.org/](https://nodejs.org/).

3. Instala las dependencias del proyecto ejecutando el siguiente comando:

   ```bash
   npm install
   ```
   
4. Ejecuta el comando
   ```bash
   npm run dev
   ```
   
### 5.2 Ejecución backend por springboot

1. Abre IntelliJ IDEA y abre tu proyecto (backend) en tu carpeta api.

2. Asegúrate de que todas las dependencias de tu proyecto estén instaladas.

3. Inicia el proyecto backend, dirigiendote al *Lab1Application* y ejecutando la api mediante el botón verde (run) de arriba a la derecha.

4. Abre tu navegador web y accede a la URL proporcionada por el servidor de desarrollo (generalmente, [http://localhost:8090/](http://localhost:8090/)) para ver tu proyecto en funcionamiento.

## 6. Accede a la aplicacion

1. Dirigete a la URL desplegada por el front, [http://localhost:3000/](http://localhost:3000/).

2. Listo.

Ahora puedes realizar ejecuciones dentro de la aplicación mediante la vista del navegador.
