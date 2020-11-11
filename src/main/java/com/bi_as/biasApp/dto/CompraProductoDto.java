package com.bi_as.biasApp.dto;

public class CompraProductoDto {

    private int idCompra;
    private int id_producto;
    private int estado;
    private int precio;

    public CompraProductoDto(){

    }

    public CompraProductoDto(int idCompra, int id_producto, int estado, int precio) {
        this.idCompra = idCompra;
        this.id_producto = id_producto;
        this.estado = estado;
        this.precio = precio;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
