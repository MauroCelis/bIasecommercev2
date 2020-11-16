package com.bi_as.biasApp.service;

import com.bi_as.biasApp.controller.UserController;
import com.bi_as.biasApp.dao.CometarioRepository;
import com.bi_as.biasApp.dao.ProductoRepository;
import com.bi_as.biasApp.dao.UserClientRepository;
import com.bi_as.biasApp.domain.Cometario;
import com.bi_as.biasApp.domain.PermissionUserSeller;
import com.bi_as.biasApp.dto.CometarioDto;
import com.bi_as.biasApp.dto.PermissionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CometarioService {

    CometarioRepository cometarioRepository;
    ProductoRepository productoRepository;
    UserClientRepository userClientRepository;
    private static final Logger LOGGER= LoggerFactory.getLogger(CometarioService.class);



    @Autowired
    public CometarioService(CometarioRepository cometarioRepository, ProductoRepository productoRepository, UserClientRepository userClientRepository) {
        this.cometarioRepository = cometarioRepository;
        this.productoRepository = productoRepository;
        this.userClientRepository = userClientRepository;
    }

    public CometarioDto newcomentario( CometarioDto cometarioDto,Integer iduser){

        Cometario cometario=new Cometario();
        cometario.setCalificacion(cometarioDto.getCalificacion());
        cometario.setCometario(cometarioDto.getComentario());
        cometario.setProductIdProduct(productoRepository.findprodutbyidProduct(cometarioDto.getProducId()));
        cometario.setUserClientIdUserclient(userClientRepository.finduserbyidclient(iduser));
        LOGGER.info(cometario.toString());

        cometarioRepository.save(cometario);

        return cometarioDto;
    }


    public List<CometarioDto> getcometariolist(){

        List<Cometario> cometarioList=cometarioRepository.listcomentario();
        List<CometarioDto> cometarioDtoList=new ArrayList<>();

        for(Cometario  cometario:cometarioList){
            cometarioDtoList.add(new CometarioDto(cometario));
        }

        return cometarioDtoList;
    }

}
