package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.model.Productos;
import com.desafioapirest.cliente.service.productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")// En Postman: localhost:8080/productos
public class ProductoController {

    @Autowired
    ProductoService productoService;




    @GetMapping("/all")
    public List<Productos> mostrarTodos(){
        return productoService.mostrarTodos();
    }


    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Object> mostrarByDCODIGO( @PathVariable String codigo) throws Exception{
        Productos producto= productoService.mostrarByCODIGO(codigo);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID( @PathVariable int id) throws Exception{
        Productos producto= productoService.mostrarByID(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }







    @PostMapping("/crear")
    public ResponseEntity<Object> nuevoProducto(@RequestBody Productos producto) throws Exception {
        producto= productoService.nuevoProducto(producto);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Object> actualizarProducto(@RequestBody Productos producto) throws Exception {
        producto= productoService.actualizarProducto(producto);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }



    @DeleteMapping("/borrar/{id}")
    public String borrarProducto(@PathVariable int id){
        String texto = productoService.borrarProducto(id);
        return texto;
    }

}
