# 🚀 TechCommerce - Sistema de E-commerce

Sistema completo de gestión de e-commerce desarrollado en Java con Spring Boot para productos tecnológicos, con API RESTful y pronto frontend en JavaScript.

## 📋 Características Implementadas

### ✅ Backend Completo
- **Gestión de Productos**: CRUD completo con validaciones
- **Sistema de Pedidos**: Creación con control de stock automático
- **Búsquedas Avanzadas**: Por nombre, categoría o término general
- **Manejo de Excepciones**: Personalizadas para errores específicos
- **Validaciones de Stock**: Prevención de pedidos con stock insuficiente
- **Estados de Pedido**: PENDIENTE, CONFIRMADO, ENVIADO, ENTREGADO, CANCELADO
- **Base de Datos H2**: Con datos de prueba de productos tecnológicos

### 🚧 Próximamente (Frontend)
- Interfaz web responsive
- Carrito de compras
- Historial de pedidos
- Panel de administración

## 🛠️ Tecnologías

### Backend
- **Java 17**
- **Spring Boot 3.2.x**
- **Spring Data JPA**
- **H2 Database** (desarrollo)
- **Maven**
- **Jakarta Persistence**

### Frontend (Próximo)
- HTML5, CSS3, JavaScript ES6+
- Consumo de API REST
- Arquitectura modular

## 🚀 Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/tech-commerce.git

# Navegar al directorio
cd tech-commerce

# Ejecutar la aplicación
mvn spring-boot:run

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
    
*   GET /api/productos/buscar?nombre={nombre} - Buscar por nombre
    
*   GET /api/productos/buscar?categoria={categoria} - Buscar por categoría
    
*   GET /api/productos/buscar?termino={termino} - Búsqueda general
    
*   GET /api/productos/stock-bajo?stockMinimo=10 - Productos con stock bajo
    

### Pedidos

*   GET /api/pedidos - Listar todos los pedidos
    
*   GET /api/pedidos/{id} - Obtener pedido por ID
    
*   POST /api/pedidos - Crear nuevo pedido
    
*   PUT /api/pedidos/{id}/estado - Actualizar estado del pedido
    
*   POST /api/pedidos/{id}/cancelar - Cancelar pedido
    
*   GET /api/pedidos/usuario/{usuarioId} - Pedidos por usuario
    
*   GET /api/pedidos/estado/{estado} - Pedidos por estado
    
*   GET /api/pedidos/recientes - Pedidos últimos 7 días
    
*   GET /api/pedidos/estadisticas - Estadísticas de pedidos
    

🗂️ Estructura del Proyecto
---------------------------

text

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   tech-commerce/  ├── src/main/java/com/techcommerce/  │   ├── TechCommerceApplication.java      # Clase principal  │   ├── controller/                       # Controladores REST  │   │   ├── ProductoController.java  │   │   └── PedidoController.java  │   ├── service/                          # Lógica de negocio  │   │   ├── ProductoService.java  │   │   └── PedidoService.java  │   ├── repository/                       # Acceso a datos  │   │   ├── ProductoRepository.java  │   │   └── PedidoRepository.java  │   ├── model/                            # Entidades JPA  │   │   ├── Producto.java  │   │   ├── Pedido.java  │   │   ├── LineaPedido.java  │   │   └── EstadoPedido.java  │   └── exception/                        # Excepciones personalizadas  │       ├── ProductoNoEncontradoException.java  │       ├── StockInsuficienteException.java  │       ├── PedidoNoEncontradoException.java  │       └── GlobalExceptionHandler.java  ├── src/main/resources/  │   ├── application.properties            # Configuración  │   └── import.sql                        # Datos de prueba  ├── frontend/                             # (Próxima implementación)  ├── pom.xml                              # Dependencias Maven  └── README.md   `

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
    
*   **CANCELADO** - Pedido cancelado (stock devuelto si estaba confirmado)
    

🔧 Ejemplos de Uso
------------------

### Crear un producto

bash

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   POST /api/productos  Content-Type: application/json  {    "nombre": "Nuevo Producto",    "descripcion": "Descripción del producto",    "precio": 299.99,    "categoria": "Tecnología",    "imagenUrl": "https://ejemplo.com/imagen.jpg",    "stock": 50  }   `

### Crear un pedido

bash

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   POST /api/pedidos  Content-Type: application/json  {    "usuarioId": 1,    "items": {      "1": 2,   // 2 unidades del producto ID 1      "3": 1    // 1 unidad del producto ID 3    }  }   `

### Buscar productos

bash

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   # Por nombre  GET /api/productos/buscar?nombre=macbook  # Por categoría  GET /api/productos/buscar?categoria=smartphones  # Búsqueda general  GET /api/productos/buscar?termino=apple   `

🐛 Solución de Problemas
------------------------

### Error de puerto en uso

properties

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   # En application.properties  server.port=8081   `

### Error de conexión a H2

Verificar que la URL sea: jdbc:h2:mem:techcommercedb

### Error de mapeo ambiguo

Asegurarse de no tener endpoints duplicados en los controllers

👨‍💻 Desarrollo
----------------

### Próximas características

*   Frontend en JavaScript
    
*   Sistema de autenticación
    
*   Carrito de compras persistente
    
*   Panel de administración
    
*   Tests unitarios e integración
    
*   Dockerización
    

### Requisitos de desarrollo

*   Java 17 o superior
    
*   Maven 3.6+
    
*   IntelliJ IDEA o Eclipse
    

📝 Licencia
-----------

Este proyecto es desarrollado con fines educativos como parte del curso de Backend en Java.
