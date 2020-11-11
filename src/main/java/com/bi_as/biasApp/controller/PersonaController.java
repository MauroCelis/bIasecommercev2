package com.bi_as.biasApp.controller;

import com.bi_as.biasApp.FBInitialize;
import com.bi_as.biasApp.domain.User;
import com.bi_as.biasApp.dto.PersonaDto;
import com.bi_as.biasApp.service.PersonaService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/k1/persona")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaController {

    private PersonaService personaService;

    private FBInitialize fbInitialize;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    public PersonaController(PersonaService personaService, FBInitialize fbInitialize) {
        this.personaService = personaService;
        this.fbInitialize = fbInitialize;
    }





    @RequestMapping("/idUser/{id}")
    public PersonaDto getUserNicknameAndPassword(@PathVariable("id")String id){
        LOGGER.info("Obteniendo id graphic");
        int idNumber=Integer.parseInt(id);
        PersonaDto personaDto=new PersonaDto(personaService.findPersonaId(idNumber));
        return  personaDto;
    }


    @PostMapping("/login")
    public PersonaDto loginPersona(@RequestBody PersonaDto personaDto){
//        UserDto userDto=new UserDto(userService.verifyUser(user));
        LOGGER.info("Obteniendo id "+personaDto.getNicknameUser()+"    "+ personaDto.getPassword());
        return personaService.getLoginUserAdmin(personaDto);

    }

//findbynick

    @RequestMapping("/usernick/{nick}")
    public PersonaDto getUserNickname(@PathVariable("nick")String nick){
        return  personaService.findbynick(nick);
    }

    @PostMapping("/addUserSeller")
    public PersonaDto addUserSeller(@RequestBody PersonaDto personaDto){
//        UserDto userDto=new UserDto(userService.verifyUser(user));
//        LOGGER.info("Obteniendo id "+personaDto.getNicknameUser()+"    "+ personaDto.getPassword());
        return personaService.saveUserSeller(personaDto);

    }


    @PutMapping("/editUserSeller")
    public PersonaDto editPublication(@RequestBody PersonaDto personaDto){
//        LOGGER.info("Realizando modificacion de editar user  esss "+productoDto.getIdProduct());
        return personaService.editUserSeller(personaDto);

    }
    @PostMapping("/adduser")
    public int addUserCloud(@RequestBody PersonaDto personaDto){
        CollectionReference userCR=fbInitialize.getFirebase().collection("Users");
        userCR.document(String.valueOf(personaDto.getIdUser())).set(personaDto);
        return personaDto.getIdUser();
    }
    @RequestMapping("/userlist")
    public List<PersonaDto> getUserCloud()throws InterruptedException, ExecutionException {
       List<PersonaDto> personaDtoList=new ArrayList<>();
       CollectionReference user=fbInitialize.getFirebase().collection("Users");
        ApiFuture<QuerySnapshot> querySnapshot=user.get();
        for(DocumentSnapshot doc:querySnapshot.get().getDocuments()){
            PersonaDto personaDto=doc.toObject(PersonaDto.class);
            personaDtoList.add(personaDto);
        }
        return personaDtoList;
    }


}
