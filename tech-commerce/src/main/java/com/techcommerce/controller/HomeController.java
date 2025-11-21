package com.techcommerce.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> serveFrontend() {
        try {
            Resource resource = new ClassPathResource("public/index.html");
            String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));
            return ResponseEntity.ok()
                    .header("Content-Type", "text/html")
                    .body(htmlContent);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<h1>Error: Archivo index.html no encontrado</h1>");
        }
    }

    @GetMapping(value = {"/gestion-productos", "/gestion-categorias", "/carrito",
            "/realizar-pedido", "/historial-pedidos", "/administracion"})
    public ResponseEntity<String> serveFrontendRoutes() {
        return serveFrontend();
    }
}