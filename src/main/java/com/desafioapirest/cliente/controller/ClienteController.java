package com.desafioapirest.cliente.controller;

import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.dto.ClientesDTO;
import com.desafioapirest.cliente.service.clientes.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes") // En Postman: localhost:8080/clientes
public class ClienteController {

    @Autowired
    ClienteService clienteService;




    @GetMapping("/all") // En Postman: localhost:8080/clientes/all
    public List<Clientes> mostrarTodos(){
        return clienteService.mostrarTodos();
    }

    @GetMapping("/alledad") // En Postman: localhost:8080/clientes/alledad
    public List<ClientesDTO> mostrarTodosDNI(){
        return clienteService.mostrarClientesEdad();
    }

    @GetMapping("/dni/{dni}")// En Postman: localhost:8080/clientes/dni/numero de dni
    public ResponseEntity<Object> mostrarbyDNI( @PathVariable int dni) throws Exception {
        ClientesDTO cliente = clienteService.mostrarByDNI(dni);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")// En Postman: localhost:8080/clientes/id/numero de id
    public ResponseEntity<Object> mostrarOriginalByID( @PathVariable int id) throws Exception {
        Clientes cliente = clienteService.mostrarOriginalByID(id);
            return new ResponseEntity<>(cliente,HttpStatus.OK);

    }

    @GetMapping("/idedad/{id}")// En Postman: localhost:8080/clientes/idedad/numero de id
    public ResponseEntity<Object> mostrarEdadByID(@PathVariable int id) throws Exception {
        ClientesDTO cliente = clienteService.mostrarEdadByID(id);
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }




    @PostMapping("/crear")  // En Postman: localhost:8080/clientes/crear
    public ResponseEntity<Object> nuevoCliente(@RequestBody Clientes cliente) throws Exception  {
        ClientesDTO nuevoCliente= clienteService.nuevoCliente(cliente);
        return new ResponseEntity<>(nuevoCliente,HttpStatus.OK);
    }

    @PostMapping("/actualizar")// En Postman: localhost:8080/clientes/actualizar
    public ResponseEntity<Object> actualizarCliente(@RequestBody Clientes cliente) throws Exception {
        ClientesDTO nuevoCliente = clienteService.actualizarCliente(cliente);
        return new ResponseEntity<>(nuevoCliente,HttpStatus.OK);
    }



    @DeleteMapping("/borrar/{id}")// En Postman: localhost:8080/clientes/borrar/numero de id
    public String borrarCliente(@PathVariable int id){
        String texto =clienteService.borrarCliente(id);
        return texto;
    }
}
