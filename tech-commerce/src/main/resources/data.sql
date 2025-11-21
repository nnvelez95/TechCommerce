-- Insertar productos tecnológicos
INSERT INTO productos (id, nombre, descripcion, precio, categoria, imagen_url, stock) VALUES
(1, 'MacBook Pro 16"', 'Laptop profesional de Apple con chip M3 Pro', 2499.99, 'Laptops', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400', 15),
(2, 'iPhone 15 Pro', 'Smartphone flagship de Apple con Dynamic Island', 1199.99, 'Smartphones', 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400', 25),
(3, 'Samsung Galaxy S24', 'Android premium con inteligencia artificial', 999.99, 'Smartphones', 'https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400', 20),
(4, 'iPad Air', 'Tablet versátil para trabajo y creatividad', 749.99, 'Tablets', 'https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400', 18),
(5, 'AirPods Pro', 'Audífonos inalámbricos con cancelación activa de ruido', 249.99, 'Audio', 'https://images.unsplash.com/photo-1600294037681-c80b4cb5b434?w=400', 30),
(6, 'Apple Watch Series 9', 'Smartwatch con monitor avanzado de salud', 399.99, 'Wearables', 'https://images.unsplash.com/photo-1551816230-ef5deaed4a26?w=400', 22),
(7, 'Dell XPS 13', 'Laptop ultraportátil con pantalla InfinityEdge', 1299.99, 'Laptops', 'https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?w=400', 12),
(8, 'Sony WH-1000XM5', 'Audífonos over-ear con mejor cancelación de ruido', 399.99, 'Audio', 'https://images.unsplash.com/photo-1583394838336-acd977736f90?w=400', 16),
(9, 'PlayStation 5', 'Consola de videojuegos de última generación', 499.99, 'Gaming', 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db?w=400', 8),
(10, 'Nintendo Switch OLED', 'Consola híbrida para gaming en cualquier lugar', 349.99, 'Gaming', 'https://images.unsplash.com/photo-1556009114-f865fbca3c0a?w=400', 14);

-- Insertar algunos pedidos de ejemplo
INSERT INTO pedidos (id, usuario_id, fecha_creacion, estado) VALUES
(1, 1, '2024-01-15 10:30:00', 'CONFIRMADO'),
(2, 2, '2024-01-16 14:45:00', 'PENDIENTE');

-- Insertar líneas de pedido
INSERT INTO linea_pedido (id, cantidad, producto_id, pedido_id) VALUES
(1, 1, 1, 1),  -- 1 MacBook Pro en pedido 1
(2, 2, 2, 1),  -- 2 iPhone 15 Pro en pedido 1
(3, 1, 3, 2);  -- 1 Samsung Galaxy en pedido 2