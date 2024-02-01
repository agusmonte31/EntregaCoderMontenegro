package com.desafioapirest.cliente.service.productos;

import com.desafioapirest.cliente.model.Productos;

import java.util.List;

public interface ProductoService {

    List<Productos> mostrarTodos();
    Productos mostrarByCODIGO(String codigo) throws Exception;
    Productos mostrarByID(int id) throws Exception;
    List<Productos> buscarProductos(String codigo) throws Exception;
    Productos nuevoProducto(Productos producto) throws Exception;
    Productos actualizarProducto(Productos producto) throws Exception;

    String borrarProducto(int id);
    boolean buscarIdProducto(int id);

    String VerifCantidad(Integer idproducto, int cantidad) throws Exception;

    String getDescripcion(Integer idproducto);

    void modifStock(Integer idproducto, int cantidad);

    void devolucionStock(Integer idproducto, int cantidad);
}
