# 🚀 TechCommerce - Sistema de E-commerce

Sistema de gestión de e-commerce desarrollado en Java con Spring Boot para productos tecnológicos.

## 📋 Características

- ✅ Gestión de productos
- ✅ Sistema de pedidos
- ✅ Control de stock
- ✅ API RESTful
- ✅ Base de datos H2 (desarrollo)
- ✅ Manejo de excepciones personalizadas
- ✅ Arquitectura en capas

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2.x**
- **Spring Data JPA**
- **H2 Database** (desarrollo)
- **Maven**
- **Jakarta Persistence**

La aplicación estará disponible en: [http://localhost:8080](http://localhost:8080/)

**Consola H2 Database:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

*   JDBC URL: jdbc:h2:mem:techcommercedb

*   Username: sa

*   Password: (vacío)


📚 API Endpoints
----------------

### Productos

*   GET /api/productos - Listar todos los productos

*   GET /api/productos/{id} - Obtener producto por ID

*   POST /api/productos - Crear nuevo producto

*   PUT /api/productos/{id} - Actualizar producto

*   DELETE /api/productos/{id} - Eliminar producto

*   GET /api/productos/buscar?nombre={nombre} - Buscar productos por nombre

*   GET /api/productos/buscar?categoria={categoria} - Buscar productos por categoría


### Pedidos (Próximamente)

*   GET /api/pedidos - Listar pedidos

*   POST /api/pedidos - Crear nuevo pedido

*   GET /api/usuarios/{id}/pedidos - Historial de pedidos por usuario



📊 Modelo de Datos
------------------

### Producto

*   id (Long) - Identificador único

*   nombre (String) - Nombre del producto

*   descripcion (String) - Descripción detallada

*   precio (Double) - Precio unitario

*   categoria (String) - Categoría del producto

*   imagenUrl (String) - URL de la imagen

*   stock (Integer) - Cantidad disponible


### Pedido

*   id (Long) - Identificador único

*   usuarioId (Long) - ID del usuario

*   fechaCreacion (LocalDateTime) - Fecha de creación

*   estado (EstadoPedido) - Estado del pedido

*   items (List) - Líneas de pedido


### LineaPedido

*   id (Long) - Identificador único

*   producto (Producto) - Producto asociado

*   cantidad (Integer) - Cantidad solicitada

*   pedido (Pedido) - Pedido asociado


🎯 Estados de Pedido
--------------------

*   **PENDIENTE** - Pedido creado pero no confirmado

*   **CONFIRMADO** - Pedido confirmado y stock reservado

*   **ENVIADO** - Pedido enviado al cliente

*   **ENTREGADO** - Pedido entregado exitosamente

*   **CANCELADO** - Pedido cancelado


🔧 Desarrollo
-------------

### Próximas características

*   Servicios de negocio

*   Controladores REST completos

*   Frontend en JavaScript

*   Validaciones avanzadas

*   Tests unitarios

*   Dockerización


### Requisitos

*   Java 17 o superior

*   Maven 3.6+

*   IntelliJ IDEA o Eclipse


📝 Licencia
-----------

Este proyecto es desarrollado con fines educativos como parte del curso de Backend en Java.

👨‍💻 Autor
-----------

Desarrollado como proyecto final del curso de Backend Java.
