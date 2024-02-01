package com.desafioapirest.cliente.service.productos;

import com.desafioapirest.cliente.exception.ApiException;
import com.desafioapirest.cliente.model.Productos;
import com.desafioapirest.cliente.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    ProductoRepository productoRepository;

    List<Productos> productoscopia;
    Productos productoAMostrar = new Productos();
    String descripcionOriginal;



    @Override
    public List<Productos> mostrarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Productos mostrarByCODIGO(String codigo) throws Exception {
        productoscopia=productoRepository.findAll();

        for(int i =0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getCodigo().equalsIgnoreCase(codigo)){
                productoAMostrar=productoscopia.get(i);
                return productoAMostrar;
            }
        }
        throw new ApiException("No se encontro ningun producto con Codigo "+codigo);
    }

    @Override
    public Productos mostrarByID(int id) throws Exception {
        productoAMostrar= productoRepository.findById(id).orElse(null);
        if(productoAMostrar==null){throw new ApiException("No se encontro ningun producto con ID :"+id);}
        return productoAMostrar;
    }

    @Override
    public List<Productos> buscarProductos(String descripcion) throws Exception{
        List<Productos> listaproductosAMostrar = new ArrayList<>();
        productoscopia = productoRepository.findAll();
        for(int i =0;i<productoscopia.size();i++){
            descripcionOriginal = productoscopia.get(i).getCodigo().toLowerCase();
            if(descripcionOriginal.contains(descripcion.toLowerCase())){
                listaproductosAMostrar.add(productoscopia.get(i));
            }
        }
        if(listaproductosAMostrar.isEmpty()){throw new ApiException("No se encontro ningun producto con una descripcion que contenga :"+descripcion);}
        return listaproductosAMostrar;
    }



    @Override
    public Productos nuevoProducto(Productos producto) throws Exception {
        boolean codigoRepetido = buscarCodigoRepetido(producto);
        if(codigoRepetido){
            throw new ApiException("El codigo de su producto pertenece a otro ya existente");
        }else {
            int id = calcularID();
            producto.setIdproducto(id);
            productoRepository.save(producto);
            return (producto);
        }
    }

    @Override
    public Productos actualizarProducto(Productos producto) throws Exception {
        int idfinal = productoRepository.findAll().size();
        if(producto.getIdproducto()<=idfinal && producto.getIdproducto()>0){
            producto.setCodigo(producto.getCodigo().toUpperCase());
            productoRepository.save(producto);
            return producto;
        }
        throw new ApiException(" ID Producto no existe");
    }



    @Override
    public String borrarProducto(int id) {
        String texto = "No se encontró el Producto con el id: "+id;
        if(id<1){return texto;}
        if(buscarIdProducto(id)){
            productoAMostrar=productoRepository.findById(id).orElse(null);
            texto = "El producto: ["+productoAMostrar.getCodigo()+"], con el id: "+id+" ha sido eliminado";
            productoRepository.deleteById(id);
        }
        return texto;
    }


    public boolean buscarIdProducto(int id){
        productoscopia=productoRepository.findAll();
        for(int i=0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getIdproducto()==id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String VerifCantidad(Integer idproducto, int cantidad) throws Exception{
        productoAMostrar = productoRepository.findById(idproducto).orElse(null);
        if(productoAMostrar.getCantidad()>=cantidad){
            return String.valueOf(cantidad*productoAMostrar.getPrecio());
        }
        return "Stock Insuficiente para la compra. El stock del producto :"+productoAMostrar.getCodigo()+" es de : "+productoAMostrar.getCantidad();
    }

    @Override
    public String getDescripcion(Integer idproducto) {
        productoAMostrar = productoRepository.findById(idproducto).orElse(null);
        return productoAMostrar.getCodigo();
    }

    @Override
    public void modifStock(Integer idproducto, int cantidad) {
        productoAMostrar = productoRepository.findById(idproducto).orElse(null);
        int nuevoStock= productoAMostrar.getCantidad()-cantidad;
        productoAMostrar.setCantidad(nuevoStock);
        productoRepository.save(productoAMostrar);
        return;
    }

    @Override
    public void devolucionStock(Integer idproducto, int cantidad) {
        productoAMostrar = productoRepository.findById(idproducto).orElse(null);
        int stockEntabla= productoAMostrar.getCantidad();
        productoAMostrar.setCantidad(stockEntabla+cantidad);
        productoRepository.save(productoAMostrar);
        return;
    }


    private boolean buscarCodigoRepetido(Productos producto){
        productoscopia=productoRepository.findAll();
        for(int i=0;i<productoscopia.size();i++){
            if(productoscopia.get(i).getCodigo().equalsIgnoreCase(producto.getCodigo())) {
            return true;
            }
        }
        return false;
    }

    private int calcularID(){
        return productoRepository.findAll().size()+1;
    }
}
