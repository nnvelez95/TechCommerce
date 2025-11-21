// Configuración de la API
const API_BASE_URL = '/api';

// Estado de la aplicación
let estado = {
    productos: [],
    carrito: [],
    usuarioId: 1 // Temporal - luego implementaremos autenticación
};

// Inicialización
document.addEventListener('DOMContentLoaded', function() {
    console.log('🚀 TechCommerce Frontend inicializado');
    cargarProductos();
    configurarEventos();
});

// Configurar eventos
function configurarEventos() {
    // Formulario agregar producto
    document.getElementById('form-agregar-producto').addEventListener('submit', agregarProducto);
}

// Navegación
function mostrarSeccion(seccionId) {
    // Ocultar todas las secciones
    document.querySelectorAll('.seccion').forEach(seccion => {
        seccion.classList.remove('activa');
        seccion.classList.add('oculta');
    });

    // Mostrar sección seleccionada
    const seccion = document.getElementById(seccionId);
    if (seccion) {
        seccion.classList.remove('oculta');
        seccion.classList.add('activa');
    }

    // Cargar datos específicos de la sección
    switch(seccionId) {
        case 'gestion-productos':
            cargarProductos();
            break;
        case 'carrito':
            actualizarCarrito();
            break;
    }
}

function mostrarSubseccion(subseccionId) {
    // Ocultar todas las subsecciones
    document.querySelectorAll('.subseccion').forEach(sub => {
        sub.classList.remove('activa');
    });

    // Mostrar subsección seleccionada
    const subseccion = document.getElementById(subseccionId);
    if (subseccion) {
        subseccion.classList.add('activa');
    }
}

// Funciones de Productos
async function cargarProductos() {
    try {
        const respuesta = await fetch(`${API_BASE_URL}/productos`);
        if (!respuesta.ok) throw new Error('Error al cargar productos');

        estado.productos = await respuesta.json();
        renderizarProductos(estado.productos);
    } catch (error) {
        mostrarError('Error al cargar productos: ' + error.message);
    }
}

function renderizarProductos(productos) {
    const contenedor = document.getElementById('lista-productos');
    contenedor.innerHTML = '';

    if (productos.length === 0) {
        contenedor.innerHTML = '<p>No hay productos disponibles.</p>';
        return;
    }

    productos.forEach(producto => {
        const productoHTML = `
            <div class="producto-card">
                ${producto.imagenUrl ?
                    `<img src="${producto.imagenUrl}" alt="${producto.nombre}" class="producto-imagen" onerror="this.style.display='none'">` :
                    '<div class="producto-imagen" style="background: #ddd; display: flex; align-items: center; justify-content: center; color: #666;">📷 Sin imagen</div>'
                }
                <div class="producto-nombre">${producto.nombre}</div>
                <div class="producto-precio">$${producto.precio}</div>
                <span class="producto-categoria">${producto.categoria}</span>
                <div class="producto-stock">Stock: ${producto.stock}</div>
                <div class="producto-descripcion">${producto.descripcion || 'Sin descripción'}</div>
                <button onclick="agregarAlCarrito(${producto.id})"
                        ${producto.stock === 0 ? 'disabled' : ''}
                        class="btn-agregar">
                    ${producto.stock === 0 ? 'Sin stock' : '➕ Agregar al carrito'}
                </button>
            </div>
        `;
        contenedor.innerHTML += productoHTML;
    });
}

async function agregarProducto(evento) {
    evento.preventDefault();

    const formData = new FormData(evento.target);
    const producto = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: parseFloat(document.getElementById('precio').value),
        categoria: document.getElementById('categoria').value,
        imagenUrl: document.getElementById('imagenUrl').value,
        stock: parseInt(document.getElementById('stock').value)
    };

    try {
        const respuesta = await fetch(`${API_BASE_URL}/productos`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(producto)
        });

        if (!respuesta.ok) {
            const error = await respuesta.json();
            throw new Error(error.message || 'Error al crear producto');
        }

        const nuevoProducto = await respuesta.json();
        mostrarMensaje(`✅ Producto "${nuevoProducto.nombre}" creado exitosamente`);
        evento.target.reset();
        cargarProductos(); // Recargar lista
    } catch (error) {
        mostrarError('Error al crear producto: ' + error.message);
    }
}

async function buscarProductos() {
    const termino = document.getElementById('termino-busqueda').value.trim();

    if (!termino) {
        mostrarError('Por favor, ingresa un término de búsqueda');
        return;
    }

    try {
        const respuesta = await fetch(`${API_BASE_URL}/productos/buscar?termino=${encodeURIComponent(termino)}`);
        if (!respuesta.ok) throw new Error('Error en la búsqueda');

        const productos = await respuesta.json();
        const contenedor = document.getElementById('resultados-busqueda');

        if (productos.length === 0) {
            contenedor.innerHTML = '<p>No se encontraron productos.</p>';
        } else {
            contenedor.innerHTML = '';
            productos.forEach(producto => {
                contenedor.innerHTML += `
                    <div class="producto-card">
                        <div class="producto-nombre">${producto.nombre}</div>
                        <div class="producto-precio">$${producto.precio}</div>
                        <span class="producto-categoria">${producto.categoria}</span>
                    </div>
                `;
            });
        }
    } catch (error) {
        mostrarError('Error en la búsqueda: ' + error.message);
    }
}

// Funciones del Carrito (próxima implementación)
function agregarAlCarrito(productoId) {
    mostrarMensaje('Funcionalidad de carrito en desarrollo');
}

function actualizarCarrito() {
    // Próxima implementación
}

// Utilidades
function mostrarMensaje(mensaje) {
    document.getElementById('modal-mensaje').textContent = mensaje;
    document.getElementById('modal').classList.remove('oculta');
}

function mostrarError(mensaje) {
    document.getElementById('modal-mensaje').textContent = '❌ ' + mensaje;
    document.getElementById('modal').classList.remove('oculta');
}

function cerrarModal() {
    document.getElementById('modal').classList.add('oculta');
}

function salir() {
    if (confirm('¿Estás seguro de que quieres salir?')) {
        mostrarMensaje('¡Hasta pronto! 👋');
        setTimeout(() => {
            // En una aplicación real, aquí redirigiríamos o limpiaríamos la sesión
            document.body.innerHTML = '<h1 style="text-align: center; margin-top: 50px;">¡Gracias por usar TechCommerce! 🛒</h1>';
        }, 2000);
    }
}

// Inicializar con la sección de productos
mostrarSeccion('gestion-productos');