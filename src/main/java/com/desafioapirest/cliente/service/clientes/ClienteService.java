package com.desafioapirest.cliente.service.clientes;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Clientes;
import com.desafioapirest.cliente.dto.ClientesDTO;

import java.util.List;

public interface ClienteService {


    List<Clientes> mostrarTodos();

    List<ClientesDTO> mostrarClientesEdad();

    ClientesDTO mostrarByDNI(int dni) throws Exception;
    Clientes mostrarOriginalByID(Integer idcliente) throws Exception;
    ClientesDTO mostrarEdadByID(Integer idcliente) throws Exception;

    ClientesDTO nuevoCliente(Clientes cliente) throws ApiException, Exception;
    ClientesDTO actualizarCliente(Clientes cliente) throws Exception;
    String borrarCliente(int id);

    boolean buscarIDCliente(int idcliente);
    String getNombreApellido(int idcliente);
}
