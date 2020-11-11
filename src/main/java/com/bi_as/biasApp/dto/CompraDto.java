package com.bi_as.biasApp.dto;

import java.util.List;

public class CompraDto {
    private int idCompra;
    private int idUser_client;
    private String tx_seller;
    private double total;
    private int status;
    private List<CompraProductoDto> compraProductoDtoList;

    public CompraDto(){

    }

    public CompraDto(int idCompra, int idUser_client, String tx_seller, double total, int status, List<CompraProductoDto> compraProductoDtoList) {
        this.idCompra = idCompra;
        this.idUser_client = idUser_client;
        this.tx_seller = tx_seller;
        this.total = total;
        this.status = status;
        this.compraProductoDtoList = compraProductoDtoList;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdUser_client() {
        return idUser_client;
    }

    public void setIdUser_client(int idUser_client) {
        this.idUser_client = idUser_client;
    }

    public String getTx_seller() {
        return tx_seller;
    }

    public void setTx_seller(String tx_seller) {
        this.tx_seller = tx_seller;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CompraProductoDto> getCompraProductoDtoList() {
        return compraProductoDtoList;
    }

    public void setCompraProductoDtoList(List<CompraProductoDto> compraProductoDtoList) {
        this.compraProductoDtoList = compraProductoDtoList;
    }
}
