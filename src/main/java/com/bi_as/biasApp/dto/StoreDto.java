package com.bi_as.biasApp.dto;

import com.bi_as.biasApp.domain.Store;

public class StoreDto {
    private Integer idStore;
    private String nameStore;
    private String locationStore;
    private String telefono;

    private String urlImage;
    private String nameImage;

    public StoreDto(Store store) {
        this.idStore = store.getIdStore();
        this.nameStore = store.getNameStore();
        this.locationStore = store.getLocationStore();
        this.telefono = store.getTelefono();
        this.urlImage=store.getUrlImage();
        this.nameImage=store.getNameImage();
    }
    public StoreDto(){

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

    public Integer getIdStore() {
        return idStore;
    }

    public void setIdStore(Integer idStore) {
        this.idStore = idStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getLocationStore() {
        return locationStore;
    }

    public void setLocationStore(String locationStore) {
        this.locationStore = locationStore;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
