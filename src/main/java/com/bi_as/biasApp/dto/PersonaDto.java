package com.bi_as.biasApp.dto;

import com.bi_as.biasApp.domain.Persona;

public class PersonaDto {
    private Integer idUser;
    private String nicknameUser;
    private String name;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private String mail;
    private String password;
    private String urlImage;
    private String nameImage;

public PersonaDto(){
}
    public PersonaDto(Persona persona) {
        this.idUser=persona.getIdUser();
        this.nicknameUser = persona.getNicknameUser();
        this.name=persona.getName();
        this.secondName=persona.getSecondName();
        this.lastName=persona.getLastName();
        this.secondLastName=persona.getSecondLastName();
        this.mail=persona.getMail();
        this.password = persona.getPassword();
        this.urlImage=persona.getUrlImage();
        this.nameImage=persona.getNameImage();

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

    public String getNicknameUser() {
        return nicknameUser;
    }

    public void setNicknameUser(String nicknameUser) {
        this.nicknameUser = nicknameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
