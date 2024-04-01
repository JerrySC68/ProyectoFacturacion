<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Factura</title>
    <link rel="stylesheet" href="css/facturaFinal.css">
</head>
<body>
<div class="page-container">
    Page <span class="page"></span> of <span class="pages"></span>
</div>

<table class="invoice-info-container">
    <tr>
        <td rowspan="2" class="client-name">
            <span th:text="${nombreProveedor}">Nombre Proveedor</span>
        </td>
        <td>
            Factura Electrónica
        </td>
    </tr>
    <tr>
        <td>
            <span th:text="${direccionProveedor}">Direccion Proveedor</span>
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
            <span th:text="${cedulaProveedor}">Cedula Proveedor</span>
        </td>
    </tr>
    <tr>
        <td>
            Fecha: <strong><span th:text="${fechaFactura}">Fecha de Factura</span></strong>
        </td>
        <td>
            <span th:text="${telefonoProveedor}">Telefono Proveedor</span>
        </td>
    </tr>
    <tr>
        <td>
            Código: <strong><span th:text="${idFactura}">Id Factura</span></strong>
        </td>
        <td>
            <span th:text="${emailProveedor}">Email Proveedor</span>
        </td>
    </tr>
</table>


<table class="line-items-container">
    <thead>
    <tr>
        <th class="cantidad">Cantidad</th>
        <th class="heading-description">Producto</th>
        <th class="precio">Precio</th>
        <th class="subtotal">Subtotal</th>
    </tr>
    </thead>
    <tbody>
    <tr th:each="item : ${lineasFactura}">
        <td th:text="${item.cantidad}">2</td>
        <td th:text="${item.producto}">Blue large widgets</td>
        <td class="right" th:text="${item.precio}">$15.00</td>
        <td class="bold" th:text="${item.subtotal}">$30.00</td>
    </tr>
    </tbody>
</table>


<table class="line-items-container has-bottom-border">
    <thead>
    <tr>
        <th>Info del pago</th>
        <th>Hecho el</th>
        <th>Precio Total</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="payment-info">
            <div>
                Account No: <strong>123567744</strong>
            </div>
            <div>
                Routing No: <strong>120000547</strong>
            </div>
        </td>
        <td class="large" th:text="${fechaFactura}">"Fecha Factura"</td>
        <td class="large total" th:text="${precioTotal}">$105.00</td>
    </tr>
    </tbody>
</table>

<div class="footer">
    <div class="footer-info">
        <span th:text="${emailProveedor}">"email"</span> |
        <span th:text="${telefonoProveedor}">"telefono"</span>
    </div>
    <div class="footer-thanks">
        <span>Gracias!</span>
    </div>
</div>
</body>
</html>
