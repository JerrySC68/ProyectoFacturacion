<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Registro de Clientes</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Registro de Clientes</h1>
<form action="#" th:action="@{/guardarCliente}" method="post">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required><br>

    <label for="apellido">Apellido:</label>
    <input type="text" id="apellido" name="apellido" required><br>

    <label for="cedula">Cédula:</label>
    <input type="text" id="cedula" name="cedula" required><br>

    <label for="direccion">Dirección:</label>
    <input type="text" id="direccion" name="direccion" required><br>

    <label for="telefono">Teléfono:</label>
    <input type="text" id="telefono" name="telefono" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <button type="submit">Guardar</button>
</form>
</body>
</html>

