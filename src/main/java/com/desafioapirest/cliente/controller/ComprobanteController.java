package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Comprobante;
import com.desafioapirest.cliente.service.comprobantes.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comprobantes")
public class ComprobanteController {
    //  En postman localhost:8080/comprobantes
    @Autowired
    ComprobanteService comprobanteService;



    @GetMapping("/all")
    public List<Comprobante> mostrarTodos(){
        return comprobanteService.mostrarTodos();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Object> mostrarOriginalByID( @PathVariable int id) throws Exception{
        Comprobante comprobante= comprobanteService.mostrarByID(id);
        if(comprobante==null){
            throw new ApiException("No se encontro ningun comprobante con ese ID :"+id);
        }
        return new ResponseEntity<>(comprobante, HttpStatus.OK);
    }



}
