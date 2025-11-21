// Configuración de la API
const API_BASE_URL = '/api';

// Estado de la aplicación
let estado = {
    productos: [],
    carrito: [],
    usuarioId: 1
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

// Función para mostrar loading
function mostrarLoading(mostrar) {
    const mainContent = document.querySelector('.main-content');
    if (mostrar) {
        mainContent.classList.add('cargando');
    } else {
        mainContent.classList.remove('cargando');
    }
}

// Navegación
function mostrarSeccionCompleta(seccionId) {
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

function mostrarSubseccionCompleta(subseccionId) {
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
        mostrarLoading(true);
        const respuesta = await fetch(`${API_BASE_URL}/productos`);

        if (!respuesta.ok) {
            throw new Error(`Error HTTP: ${respuesta.status}`);
        }

        estado.productos = await respuesta.json();
        renderizarProductos(estado.productos);

    } catch (error) {
        console.error('Error:', error);
        mostrarError('No se pudieron cargar los productos: ' + error.message);
    } finally {
        mostrarLoading(false);
    }
}

function renderizarProductos(productos) {
    const contenedor = document.getElementById('lista-productos');
    contenedor.innerHTML = '';

    if (productos.length === 0) {
        contenedor.innerHTML = '<div class="texto-centro"><p>No hay productos disponibles en este momento.</p></div>';
        return;
    }

    productos.forEach(producto => {
        const productoHTML = `
            <div class="producto-card">
                ${producto.imagenUrl ?
                    `<img src="${producto.imagenUrl}" alt="${producto.nombre}" class="producto-imagen" onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';">` :
                    ''
                }
                ${!producto.imagenUrl ?
                    `<div class="producto-imagen">📱</div>` :
                    '<div class="producto-imagen" style="display: none;">📱</div>'
                }
                <div class="producto-nombre">${producto.nombre}</div>
                <div class="producto-precio">$${producto.precio.toFixed(2)}</div>
                <span class="producto-categoria">${producto.categoria}</span>
                <div class="producto-stock">📦 Stock: ${producto.stock} unidades</div>
                <div class="producto-descripcion">${producto.descripcion || 'Sin descripción disponible'}</div>
                <button onclick="agregarAlCarrito(${producto.id})"
                        ${producto.stock === 0 ? 'disabled' : ''}
                        class="btn-agregar">
                    ${producto.stock === 0 ? '❌ Sin stock' : '🛒 Agregar al carrito'}
                </button>
            </div>
        `;
        contenedor.innerHTML += productoHTML;
    });
}

async function agregarProducto(evento) {
    evento.preventDefault();

    const producto = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: parseFloat(document.getElementById('precio').value),
        categoria: document.getElementById('categoria').value,
        imagenUrl: document.getElementById('imagenUrl').value,
        stock: parseInt(document.getElementById('stock').value)
    };

    try {
        mostrarLoading(true);
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
        document.getElementById('form-agregar-producto').reset();
        cargarProductos();

    } catch (error) {
        mostrarError('Error al crear producto: ' + error.message);
    } finally {
        mostrarLoading(false);
    }
}

async function buscarProductos() {
    const termino = document.getElementById('termino-busqueda').value.trim();

    if (!termino) {
        mostrarError('Por favor, ingresa un término de búsqueda');
        return;
    }

    try {
        mostrarLoading(true);
        const respuesta = await fetch(`${API_BASE_URL}/productos/buscar?termino=${encodeURIComponent(termino)}`);
        if (!respuesta.ok) throw new Error('Error en la búsqueda');

        const productos = await respuesta.json();
        const contenedor = document.getElementById('resultados-busqueda');

        if (productos.length === 0) {
            contenedor.innerHTML = '<div class="texto-centro"><p>No se encontraron productos que coincidan con tu búsqueda.</p></div>';
        } else {
            contenedor.innerHTML = '';
            productos.forEach(producto => {
                contenedor.innerHTML += `
                    <div class="producto-card">
                        <div class="producto-nombre">${producto.nombre}</div>
                        <div class="producto-precio">$${producto.precio.toFixed(2)}</div>
                        <span class="producto-categoria">${producto.categoria}</span>
                        <div class="producto-stock">Stock: ${producto.stock}</div>
                    </div>
                `;
            });
        }
    } catch (error) {
        mostrarError('Error en la búsqueda: ' + error.message);
    } finally {
        mostrarLoading(false);
    }
}

// Funciones del Carrito (próxima implementación)
function agregarAlCarrito(productoId) {
    mostrarMensaje('🛒 Funcionalidad de carrito en desarrollo - Próximamente');
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

function salirCompleto() {
    if (confirm('¿Estás seguro de que quieres salir?')) {
        mostrarMensaje('¡Gracias por usar TechCommerce! 👋');
        setTimeout(() => {
            document.body.innerHTML = `
                <div class="container" style="display: flex; align-items: center; justify-content: center; height: 100vh;">
                    <div class="texto-centro">
                        <h1 style="color: white; font-size: 2.5rem;">¡Hasta pronto! 👋</h1>
                        <p style="color: white; opacity: 0.8;">TechCommerce - Sistema de Gestión</p>
                    </div>
                </div>
            `;
        }, 2000);
    }
}

// Hacer funciones globales
window.mostrarSeccionCompleta = mostrarSeccionCompleta;
window.mostrarSubseccionCompleta = mostrarSubseccionCompleta;
window.salirCompleto = salirCompleto;

// Inicializar con la sección de productos
mostrarSeccionCompleta('gestion-productos');