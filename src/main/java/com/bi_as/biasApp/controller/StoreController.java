package com.bi_as.biasApp.controller;

import com.bi_as.biasApp.FBInitialize;
import com.bi_as.biasApp.domain.Store;
import com.bi_as.biasApp.dto.ProductoDto;
import com.bi_as.biasApp.dto.StoreDto;
import com.bi_as.biasApp.service.ProductService;
import com.bi_as.biasApp.service.StoreService;
import com.google.api.client.googleapis.notifications.StoredChannel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/k1/store")
public class StoreController {
    private StoreService storeService;
    private FBInitialize fbInitialize;
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public StoreController(StoreService storeService, FBInitialize fbInitialize) {
        this.storeService = storeService;
        this.fbInitialize = fbInitialize;
    }



    @RequestMapping("/idstore/{id}")
    public StoreDto encotratienda(@PathVariable("id")String id){
        LOGGER.info("Obteniendo id store");
        int idNumber=Integer.parseInt(id);
        return  storeService.findidstore(idNumber);
    }


    @PostMapping("/addStoreCloud")
    public int addproductInCloud(@RequestBody StoreDto storeDto){
        LOGGER.info("llego aqui xd");
        CollectionReference productCR=fbInitialize.getFirebase().collection("Stores");
        productCR.document(String.valueOf(storeDto.getIdStore())).set(storeDto);
        return storeDto.getIdStore();
    }
    @RequestMapping("/storelistcloud")
    public List<StoreDto> getstorelistcloud()throws InterruptedException, ExecutionException {
        List<StoreDto> storeDtoList=new ArrayList<>();
        CollectionReference store=fbInitialize.getFirebase().collection("Stores");
        ApiFuture<QuerySnapshot> querySnapshot=store.get();
        for(DocumentSnapshot doc:querySnapshot.get().getDocuments()){
            StoreDto storeDto=doc.toObject(StoreDto.class);
            storeDtoList.add(storeDto);
        }
        return  storeDtoList;
    }

}
