package com.bi_as.biasApp.service;

import com.bi_as.biasApp.dao.PersonaRepository;
import com.bi_as.biasApp.dao.StroreRepository;
import com.bi_as.biasApp.dao.UserAdminRepository;
import com.bi_as.biasApp.dao.UserSellerRepository;
import com.bi_as.biasApp.domain.Persona;
import com.bi_as.biasApp.domain.Store;
import com.bi_as.biasApp.domain.UserAdmin;
import com.bi_as.biasApp.domain.UserSeller;
import com.bi_as.biasApp.dto.PersonaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    PersonaRepository personaRepository;
    UserAdminRepository userAdminRepository;
    StroreRepository stroreRepository;
    UserSellerRepository userSellerRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(PersonaService.class);

    @Autowired
    public PersonaService(PersonaRepository personaRepository,UserAdminRepository userAdminRepository,StroreRepository stroreRepository,UserSellerRepository userSellerRepository) {
        this.personaRepository = personaRepository;
        this.userAdminRepository=userAdminRepository;
        this.stroreRepository=stroreRepository;
        this.userSellerRepository=userSellerRepository;
    }


    public Persona  findPersonaId(int id){
        return personaRepository.findPersonabyidUser(id);
    }


    //---------------------------------------------------------------------------


    public Persona updatePersonalDataByIdPersona(PersonaDto personaDto){
//        Persona persona=personaRepository.findPersonabyidUser(personaDto.getIdUser());
        Persona persona=new Persona();
        persona.setName(personaDto.getName());
        persona.setSecondName(persona.getSecondName());
        persona.setLastName(persona.getLastName());
        persona.setSecondLastName(persona.getSecondLastName());
        personaRepository.save(persona);
        return persona;
    }

    public UserAdmin saveUserAdmin(PersonaDto personaDto){
        Persona persona=new Persona();
        persona.setName(personaDto.getName());
        persona.setSecondName(personaDto.getSecondName());
        persona.setLastName(personaDto.getLastName());
        persona.setMail(personaDto.getMail());
        persona.setPassword(personaDto.getPassword());
        persona.setNicknameUser(personaDto.getNicknameUser());
        persona.setActive(1);
        personaRepository.save(persona);
        Persona persona1=personaRepository.findPersonabyNicknamePassword(personaDto.getNicknameUser(),personaDto.getPassword());
        UserAdmin userAdmin=new UserAdmin();
        userAdmin.setPersonaIdUser(persona1);
        userAdminRepository.save(userAdmin);
        UserAdmin userAdmin1=userAdminRepository.findLastUserAdminRegister();
        Store strore=new Store();
        strore.setUserAdminIdUseradmin(userAdmin1);
        stroreRepository.save(strore);
        return userAdmin;
    }

    public PersonaDto saveUserSeller(PersonaDto personaDto){
        Persona persona=new Persona();
        persona.setNicknameUser(personaDto.getNicknameUser());
        persona.setName(personaDto.getName());
        persona.setSecondName(personaDto.getSecondName());
        persona.setLastName(personaDto.getLastName());
        persona.setSecondLastName(personaDto.getSecondLastName());
        LOGGER.info("Second last name "+personaDto.getSecondLastName());
        persona.setMail(personaDto.getMail());
        persona.setPassword(personaDto.getPassword());
        if(personaDto.getUrlImage().isEmpty()){

            persona.setUrlImage("urlsellerimage1");
            persona.setNameImage("Imageseller1");
        }else {

            persona.setUrlImage(personaDto.getUrlImage());
            persona.setNameImage(personaDto.getNameImage());

        }
        persona.setActive(1);
        personaRepository.save(persona);
        Persona persona1=personaRepository.findPersonabyNicknamePassword(personaDto.getNicknameUser(),personaDto.getPassword());

//        UserAdmin userAdmin=userAdminRepository.finduseradmin(1);
//        LOGGER.info("User admin existente "+userAdmin);
        Store store=stroreRepository.findstoreidstore(1);
        LOGGER.info("El nombre de la tienda es "+store.getNameImage());
        UserSeller userSeller=new UserSeller();
        userSeller.setPersonaIdUser(persona1);
        userSeller.setStroreIdStore(store);
        userSeller.setActive(1);
        userSellerRepository.save(userSeller);
        LOGGER.info("Guardado con exito");
        return personaDto;
    }


    public PersonaDto getLoginUserAdmin(PersonaDto personaDto){
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

    public PersonaDto editUserSeller(PersonaDto personaDto) {
        Persona persona=personaRepository.findPersonabyidUser(personaDto.getIdUser());
        persona.setNicknameUser(personaDto.getNicknameUser());
        persona.setName(personaDto.getName());
        persona.setSecondName(personaDto.getSecondName());
        persona.setLastName(personaDto.getLastName());
        persona.setSecondLastName(personaDto.getSecondLastName());
        persona.setMail(personaDto.getMail());
        persona.setPassword(personaDto.getPassword());
        if(personaDto.getUrlImage().isEmpty()){

            persona.setUrlImage("urlsellerimage1");
            persona.setNameImage("Imageseller1");
        }else {

            persona.setUrlImage(personaDto.getUrlImage());
            persona.setNameImage(personaDto.getNameImage());

        }

        personaRepository.save(persona);
        return  personaDto;
    }
    //-------------------------------------------------------------------------------------------

    public PersonaDto findbynick(String nick){
        PersonaDto personaDto= new PersonaDto();
        if(personaRepository.findPersonabyNickname(nick) != null){
            personaDto=new PersonaDto(personaRepository.findPersonabyNickname(nick));
        }

        return  personaDto;
    }

}
