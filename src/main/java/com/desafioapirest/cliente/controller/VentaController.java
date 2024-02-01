package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.model.Venta;
import com.desafioapirest.cliente.service.venta.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventas")//  En Postman localshost:8080/ventas
public class VentaController {

    @Autowired
    VentaService ventaService;


    @GetMapping("/all")
    public List<Venta> mostrarTodos(){
        return ventaService.mostrarTodos();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID(@PathVariable int id) throws Exception{
        Venta venta = ventaService.mostrarByID(id);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }




    @PostMapping("/nueva")
    public ResponseEntity<Object> nuevaVenta(@RequestBody Venta nueva)throws Exception{
        Venta ventaOk= ventaService.nuevaVenta(nueva);
        return new ResponseEntity<>(ventaOk, HttpStatus.CREATED);
    }





    @DeleteMapping("/borrar/{id}")
    public String anularVenta(@PathVariable int id) throws Exception{
        String texto = ventaService.anularVenta(id);
        return texto;
    }
}
