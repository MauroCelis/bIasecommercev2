package com.bi_as.biasApp.dto;

import com.bi_as.biasApp.domain.Product;

public class ProductoDto {

    private Integer idProduct;
    private String name;
    private String description;
    private double cost;
    private int code;
    private int quantity;
    private String type;
    private String urlImage;
    private String nameImage;

    public ProductoDto(Product product) {
        this.idProduct = product.getIdProduct();
        this.name = product.getName();
        this.description=product.getDescription();
        this.cost = product.getCost();
        this.code = product.getCode();
        this.quantity = product.getQuantity();
        this.type=product.getType();
        this.urlImage=product.getUrlImage();
        this.nameImage=product.getNameImage();
    }
    public ProductoDto(){

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }
}
