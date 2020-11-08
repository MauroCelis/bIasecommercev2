package com.bi_as.biasApp.service;

import com.bi_as.biasApp.controller.userClientControler;
import com.bi_as.biasApp.dao.PersonaRepository;
import com.bi_as.biasApp.dao.StroreRepository;
import com.bi_as.biasApp.dao.UserClientRepository;
import com.bi_as.biasApp.domain.*;
import com.bi_as.biasApp.dto.PersonaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserClientService {
    PersonaRepository personaRepository;
    UserClientRepository userClientRepository;
    StroreRepository stroreRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClientService.class);

    @Autowired
    public UserClientService(UserClientRepository userClientRepository,PersonaRepository personaRepository,StroreRepository stroreRepository) {
        this.userClientRepository = userClientRepository;
        this.personaRepository=personaRepository;
        this.stroreRepository=stroreRepository;
    }
    public List<PersonaDto> getlistclient(){
        List<UserClient> userClientList =userClientRepository.findclientlist();
        List<PersonaDto> personaDtoList=new ArrayList<>();
            for(UserClient userClient:userClientList){
                personaDtoList.add(new PersonaDto(userClient.getPersonaIdUser()));
            }
        return  personaDtoList;
    }

    public PersonaDto addUserClient(PersonaDto personaDto) {
        Persona persona=new Persona();
        persona.setNicknameUser(personaDto.getNicknameUser());
        persona.setName(personaDto.getName());
        persona.setSecondName(personaDto.getSecondName());
        persona.setLastName(personaDto.getLastName());
        persona.setSecondLastName(personaDto.getSecondLastName());
        LOGGER.info("Second last name "+personaDto.getSecondLastName());
        persona.setMail(personaDto.getMail());
        persona.setPassword(personaDto.getPassword());
        persona.setUrlImage("urlsellerimage1");
        persona.setNameImage("Imageseller1");
        persona.setActive(1);
        personaRepository.save(persona);
        Persona persona1=personaRepository.findPersonabyNicknamePassword(personaDto.getNicknameUser(),personaDto.getPassword());
        Store store=stroreRepository.findstoreidstore(1);
        LOGGER.info("El nombre de la tienda es "+store.getNameImage());
        UserClient userClient=new UserClient();
        userClient.setPersonaIdUser(persona1);
        userClient.setStroreIdStore(store);
        userClient.setActive(1);
        userClientRepository.save(userClient);
        LOGGER.info("Guardado con exito");
        return personaDto;
    }


    public PersonaDto getLoginUserClient(PersonaDto personaDto) {
        Persona persona=personaRepository.findPersonabyNicknamePassword(personaDto.getNicknameUser(),personaDto.getPassword());
        PersonaDto personaDto1=new PersonaDto();
        int number=0;
        if(persona==null){
            LOGGER.info("No existe el usuario");
        }
        else {
            number=1;
            personaDto1=new PersonaDto(persona);
            LOGGER.info("Si existe el usuario");
        }
        return personaDto1;
    }

    public PersonaDto editUserClient(PersonaDto personaDto) {
        Persona persona=personaRepository.findPersonabyidUser(personaDto.getIdUser());
        persona.setNicknameUser(personaDto.getNicknameUser());
        persona.setName(personaDto.getName());
        persona.setSecondName(personaDto.getSecondName());
        persona.setLastName(personaDto.getLastName());
        persona.setSecondLastName(personaDto.getSecondLastName());
        persona.setMail(personaDto.getMail());
        persona.setPassword(personaDto.getPassword());
        persona.setUrlImage("urlsellerimage1");
        persona.setNameImage("Imageseller1");
        personaRepository.save(persona);
        return  personaDto;

    }

    public String deleteUserClient(PersonaDto personaDto) {

        String statua="no es cliente ";

       /* UserAdmin userClient = new UserAdmin();
        userClient=userClientRepository.findcliente(personaDto.getIdUser());
        System.out.println();
        System.out.println(userClient);
        System.out.println();
        */


        try {
            Persona persona=personaRepository.findPersonabyidUser(personaDto.getIdUser());

            UserClient userClient=new UserClient();
            userClient=userClientRepository.finduserclient(persona);
            Persona persona1=userClient.getPersonaIdUser();
            if(persona1.getIdUser() == persona.getIdUser()){
                persona.setActive(0);
                personaRepository.save(persona);
                userClient.setActive(0);
                userClientRepository.save(userClient);
                statua="se elimino al cliente";
            }
        }catch (Exception e){
            statua="no es un cliente";
        }

        return statua;

    }
}
