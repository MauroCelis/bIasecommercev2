package com.bi_as.biasApp.controller;

import com.bi_as.biasApp.dto.ProductoDto;
import com.bi_as.biasApp.dto.StoreDto;
import com.bi_as.biasApp.service.ProductService;
import com.bi_as.biasApp.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/k1/store")
public class StoreController {
    private StoreService storeService;
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductController.class);


    @Autowired
    public StoreController( StoreService storeService){
        this.storeService= storeService;
    }
    @RequestMapping("/idstore/{id}")
    public StoreDto encotratienda(@PathVariable("id")String id){
        LOGGER.info("Obteniendo id store");
        int idNumber=Integer.parseInt(id);
        return  storeService.findidstore(idNumber);
    }
}
