package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "PRODUCTOS")
public class Productos {
    @Id
    @Column(name = "IDPRODUCTO")
    private Integer idproducto;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PRECIO")
    private Float precio;

    //CONSTRUCTORES
    public Productos() {
    }

    public Productos(Integer idproducto, String codigo, Integer cantidad, Float precio) {
        this.idproducto = idproducto;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    //GETTERS AND SETTERS


    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}