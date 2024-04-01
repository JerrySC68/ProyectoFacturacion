<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Factura</title>
    <link rel="stylesheet" href="css/formularioFactura.css">
</head>
<body>
<h1>Formulario de Factura</h1>
<form action="#" method="post" id="invoice-form">
    <fieldset>
        <legend>Proveedor</legend>
        <div>
            <label for="provider-name">Nombre:</label>
            <input type="text" id="provider-name" name="provider-name" required>
        </div>
        <div>
            <label for="provider-id">Cédula:</label>
            <input type="text" id="provider-id" name="provider-id" required>
        </div>
        <div>
            <label for="provider-address">Dirección:</label>
            <input type="text" id="provider-address" name="provider-address" required>
        </div>
        <div>
            <label for="provider-phone">Teléfono:</label>
            <input type="tel" id="provider-phone" name="provider-phone" required>
        </div>
        <div>
            <label for="provider-email">Email:</label>
            <input type="email" id="provider-email" name="provider-email" required>
        </div>
    </fieldset>

    <fieldset>
        <legend>Cliente</legend>
        <div>
            <label for="customer-name">Nombre:</label>
            <input type="text" id="customer-name" name="customer-name" required>
        </div>
        <div>
            <label for="customer-lastname">Apellidos:</label>
            <input type="text" id="customer-lastname" name="customer-lastname" required>
        </div>
        <div>
            <label for="customer-id">Cédula:</label>
            <input type="text" id="customer-id" name="customer-id" required>
        </div>
        <div>
            <label for="customer-address">Dirección:</label>
            <input type="text" id="customer-address" name="customer-address" required>
        </div>
        <div>
            <label for="customer-phone">Teléfono:</label>
            <input type="tel" id="customer-phone" name="customer-phone" required>
        </div>
        <div>
            <label for="customer-email">Email:</label>
            <input type="email" id="customer-email" name="customer-email" required>
        </div>
    </fieldset>

    <fieldset>
        <legend>Producto o Servicio</legend>
        <div>
            <label for="product-name">Nombre:</label>
            <input type="text" id="product-name" name="product-name" required>
        </div>
        <div>
            <label for="product-quantity">Cantidad:</label>
            <input type="number" id="product-quantity" name="product-quantity" min="1" required>
        </div>
        <div>
            <label for="product-description">Descripción:</label>
            <textarea id="product-description" name="product-description" required></textarea>
        </div>
        <div>
            <label for="product-price">Precio:</label>
            <input type="number" id="product-price" name="product-price" min="0" step="0.01" required>
        </div>
    </fieldset>

    <button type="submit">Generar Factura</button>
</form>
</body>
</html>


