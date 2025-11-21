# 🚀 TechCommerce - Sistema de E-commerce Completo

Sistema completo de gestión de e-commerce desarrollado en **Java Spring Boot** con **frontend integrado**, para productos tecnológicos.

## 📋 Estado del Proyecto

### ✅ **COMPLETADO**
- **Backend API RESTful** completo con Spring Boot
- **Frontend integrado** servido desde el mismo backend
- **Base de datos H2** con datos de prueba
- **Gestión de productos** (CRUD completo)
- **Sistema de búsquedas** avanzadas
- **Arquitectura en capas** (Controller-Service-Repository)

### 🚧 **EN DESARROLLO**
- Carrito de compras
- Sistema de pedidos
- Gestión de categorías
- Panel de administración

## 🛠️ Tecnologías Implementadas

### Backend
- **Java 17**
- **Spring Boot 3.2.x**
- **Spring Data JPA**
- **H2 Database** (desarrollo)
- **Maven**

### Frontend
- **HTML5, CSS3, JavaScript ES6+**
- **Diseño responsive**
- **Consumo de API REST**
- **Arquitectura modular**

## 🚀 Ejecución del Proyecto

### Requisitos
- Java 17 o superior
- Maven 3.6+

### Pasos para ejecutar

# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/tech-commerce.git

# 2. Navegar al directorio
cd tech-commerce

# 3. Ejecutar la aplicación
mvn spring-boot:run

### Acceso a la aplicación

*   **Aplicación principal:** [http://localhost:8080](http://localhost:8080/)
    
*   **API REST:** [http://localhost:8080/api](http://localhost:8080/api)
    
*   **Consola H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    
    *   JDBC URL: jdbc:h2:mem:techcommercedb
        
    *   Usuario: sa
        
    *   Password: (vacío)
        

📚 API Endpoints Disponibles
----------------------------

### Productos

*   GET /api/productos - Listar todos los productos
    
*   GET /api/productos/{id} - Obtener producto por ID
    
*   POST /api/productos - Crear nuevo producto
    
*   PUT /api/productos/{id} - Actualizar producto
    
*   DELETE /api/productos/{id} - Eliminar producto
    
*   GET /api/productos/buscar?termino={valor} - Búsqueda general
    
*   GET /api/productos/buscar?nombre={valor} - Búsqueda por nombre
    
*   GET /api/productos/buscar?categoria={valor} - Búsqueda por categoría
    

### Pedidos

*   GET /api/pedidos - Listar todos los pedidos
    
*   GET /api/pedidos/{id} - Obtener pedido por ID
    
*   POST /api/pedidos - Crear nuevo pedido
    
*   PUT /api/pedidos/{id}/estado - Actualizar estado
    
*   GET /api/pedidos/usuario/{usuarioId} - Pedidos por usuario
*   🎯 Funcionalidades Implementadas
--------------------------------

### Backend

*   ✅ Arquitectura RESTful completa
    
*   ✅ Validaciones y manejo de excepciones
    
*   ✅ Operaciones CRUD para productos y pedidos
    
*   ✅ Control de stock automático
    
*   ✅ Búsquedas y filtros avanzados
    
*   ✅ Base de datos en memoria con datos de prueba
    

### Frontend

*   ✅ Interfaz de usuario responsive
    
*   ✅ Navegación entre secciones
    
*   ✅ Listado y búsqueda de productos
    
*   ✅ Formulario para agregar productos
    
*   ✅ Integración completa con API
    
*   ✅ Manejo de errores y loading states
    

🎮 Cómo Usar la Aplicación
--------------------------

1.  **Al acceder a** [**http://localhost:8080**](http://localhost:8080/) verás el menú principal
    
2.  **En "Gestión de Productos"** puedes:
    
    *   Ver todos los productos
        
    *   Buscar productos por nombre o categoría
        
    *   Agregar nuevos productos
        
3.  **Próximamente:** Carrito, pedidos, y más funcionalidades
    

🔧 Desarrollo
-------------

### Próximas Características

*   Carrito de compras
    
*   Sistema completo de pedidos
    
*   Gestión de categorías
    
*   Panel de administración
    
*   Sistema de usuarios
    
*   Tests unitarios e integración
    

### Estructura de Datos

La aplicación incluye datos de prueba de productos tecnológicos:

*   Laptops, smartphones, tablets
    
*   Audio, wearables, gaming
    
*   Precios y stock realistas
    

📝 Licencia
-----------

Este proyecto es desarrollado con fines educativos como parte del curso de Backend en Java.

👏 Autor
--------

Desarrollado como proyecto final del curso de Backend Java.
