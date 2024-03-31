<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Factura</title>
    <link rel="stylesheet" href="css/registroFactura.css">
</head>
<body>
<h1>Registro de Factura</h1>
<form action="#" method="post" th:action="@{/generarFactura}" th:object="${factura}">
    <div class="form-container">
        <div class="column">
            <label for="codigo">Código de Factura:</label>
            <input type="text" id="codigo" name="codigo" th:field="*{codigo}" required>
            <label for="fecha">Fecha de Factura:</label>
            <input type="date" id="fecha" name="fecha" th:field="*{fecha}" required>
            <label for="proveedorCedula">Cédula del Proveedor:</label>
            <input type="text" id="proveedorCedula" name="proveedorCedula" th:field="*{proveedorCedula}" required>
        </div>
        <div class="column">
            <label for="clienteCedula">Cédula del Cliente:</label>
            <input type="text" id="clienteCedula" name="clienteCedula" th:field="*{clienteCedula}" required>
            <label for="productoId">ID del Producto:</label>
            <input type="text" id="productoId" name="productoId" th:field="*{productoId}" required>
            <label for="productoCantidad">Cantidad del Producto:</label>
            <input type="number" id="productoCantidad" name="productoCantidad" th:field="*{productoCantidad}" required>
        </div>
    </div>
    <button class="button" style="vertical-align:middle"><span>Registrar Factura</span></button>
</form>
</body>
</html>



