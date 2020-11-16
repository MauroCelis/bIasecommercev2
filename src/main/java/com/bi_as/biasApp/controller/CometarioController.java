package com.bi_as.biasApp.controller;

import com.bi_as.biasApp.dto.CometarioDto;
import com.bi_as.biasApp.dto.ProductoDto;
import com.bi_as.biasApp.service.CometarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/k1/Comentario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CometarioController {

    CometarioService cometarioService;


    @Autowired
    public CometarioController(CometarioService cometarioService) {
        this.cometarioService = cometarioService;
    }

    @PostMapping("/{idUser}")
    public CometarioDto newcoment(@PathVariable("idUser") Integer iduser,@RequestBody CometarioDto cometarioDto){
     return cometarioService.newcomentario(cometarioDto,iduser);
    }
    @RequestMapping("/cometariolist")
    public List<CometarioDto> listcoments(){
        return cometarioService.getcometariolist();
    }

}
