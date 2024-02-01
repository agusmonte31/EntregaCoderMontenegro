package com.desafioapirest.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "COMPROBANTE")
public class Comprobante {
    @Id
    @Column(name = "IDCOMPROBANTE")
    private Integer idcomprobante;

    @Column(name = "IDVENTA")
    private Integer idventa;

    @Column(name = "IDCLIENTE")
    private Integer idcliente;

    @Column(name = "NOMBREAPELLIDO")
    private String nombreapellido;

    @Column(name ="IDPRODUCTO")
    private Integer idproducto;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="FECHA")
    private Date fecha;

    @Column(name="CANTIDAD")
    private Integer cantidad;


    @Column(name="PRECIONETO")
    private Float precioneto;

    @Column(name="PRECIOTOTAL")
    private Float preciototal;


    //CONSTRUCTORES

    public Comprobante() {
    }

    public Comprobante(Integer idcomprobante, Integer idventa, Integer idcliente, String nombreapellido, Integer idproducto, String descripcion, Date fecha, Integer cantidad, Float precioneto, Float preciototal) {
        this.idcomprobante = idcomprobante;
        this.idventa = idventa;
        this.idcliente = idcliente;
        this.nombreapellido = nombreapellido;
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precioneto = precioneto;
        this.preciototal = preciototal;
    }

// GETTERS AND SETTERS

    public Integer getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(Integer idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombreapellido() {
        return nombreapellido;
    }

    public void setNombreapellido(String nombreapellido) {
        this.nombreapellido = nombreapellido;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioneto() {
        return precioneto;
    }

    public void setPrecioneto(Float precioneto) {
        this.precioneto = precioneto;
    }

    public Float getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(Float preciototal) {
        this.preciototal = preciototal;
    }




    @Override
    public String toString(){
        return  "idcomprobante : "+this.idcomprobante+
                "\nidventa : "+this.idventa+
                "\nidcliente : "+this.idcliente+
                "\nnombreapellido : "+this.nombreapellido+
                "\nidproducto : "+this.idproducto+
                "\ndescirpcion : "+this.descripcion+
                "\nfecha : "+this.fecha+
                "\ncantidad : "+this.cantidad+
                "\nprecioneto : "+this.precioneto+
                "\npreciototal : "+this.preciototal;
    }
}
