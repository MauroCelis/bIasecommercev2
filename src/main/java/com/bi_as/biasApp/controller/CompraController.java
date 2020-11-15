package com.bi_as.biasApp.controller;

import com.bi_as.biasApp.FBInitialize;
import com.bi_as.biasApp.dto.CompraDto;
import com.bi_as.biasApp.dto.ProductoDto;
import com.bi_as.biasApp.service.CompraService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/k1/compra/producto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompraController {

    CompraService compraService;
    FBInitialize fbInitialize;
    private static final Logger LOGGER= LoggerFactory.getLogger(CompraController.class);

    @Autowired
    public CompraController(CompraService compraService,FBInitialize fbInitialize) {
        this.compraService = compraService;
        this.fbInitialize=fbInitialize;
    }
/*
    @PostMapping("/addcompra/{tipocompra}/{iduser}")
    public int addCompra(@PathVariable("tipocompra")int tipocompra,@PathVariable("iduser")int iduser, @RequestBody List<ProductoDto> productoDto){
        // LOGGER.info(productService.nuevoproducto(productoDto,idtienda).toString());
        return compraService.addCompra(productoDto,tipocompra,iduser);
    }*/

    @PostMapping("/addCompraCloud/{tipocompra}/{iduser}")
    public int addCompraInCloud(@PathVariable("tipocompra")int tipocompra,@PathVariable("iduser")int iduser,@RequestBody CompraDto compraDto){
        CollectionReference compraCR=fbInitialize.getFirebase().collection("Compra");
        String cad="compra"+compraDto.getIdCompra();
        compraCR.document(cad).set(compraDto);
        int i=compraService.addCompra(compraDto,tipocompra,iduser);
        // LOGGER.info(productService.nuevoproducto(productoDto,idtienda).toString());
        return compraDto.getIdCompra();
//        return productService.nuevoproducto(productoDto,idtienda);
    }


    @RequestMapping("/compralistcloud")
    public List<CompraDto> getProductListInCloud() throws InterruptedException, ExecutionException {
        List<CompraDto> compraDtoList=new ArrayList<>();
        CollectionReference compra=fbInitialize.getFirebase().collection("Compra");
        ApiFuture<QuerySnapshot> querySnapshot=compra.get();
        for(DocumentSnapshot doc:querySnapshot.get().getDocuments()){
            CompraDto compraDto=doc.toObject(CompraDto.class);
            compraDtoList.add(compraDto);
        }
        return compraDtoList;
    }

    @PutMapping("/editCompraInCloud")
    public String editCompra(@RequestBody CompraDto compraDto) throws InterruptedException, ExecutionException{
        Firestore dbFirestore=fbInitialize.getFirebase();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection("Compra").document("1").set(compraDto);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @PostMapping("/addPedidoCloud")
    public int addPedidoInCloud(@PathVariable("tipocompra")int tipocompra,@PathVariable("iduser")int iduser,@RequestBody CompraDto compraDto){
        CollectionReference compraCR=fbInitialize.getFirebase().collection("Pedidos");
        String cad="pedido"+compraDto.getIdCompra();
        compraCR.document(cad).set(compraDto);
        int i=compraService.addCompra(compraDto,tipocompra,iduser);
        // LOGGER.info(productService.nuevoproducto(productoDto,idtienda).toString());
        return compraDto.getIdCompra();
//        return productService.nuevoproducto(productoDto,idtienda);
    }


    @RequestMapping("/pedidolistcloud")
    public List<CompraDto> getPedidoListInCloud() throws InterruptedException, ExecutionException {
        List<CompraDto> compraDtoList=new ArrayList<>();
        CollectionReference compra=fbInitialize.getFirebase().collection("Pedidos");
        ApiFuture<QuerySnapshot> querySnapshot=compra.get();
        for(DocumentSnapshot doc:querySnapshot.get().getDocuments()){
            CompraDto compraDto=doc.toObject(CompraDto.class);
            compraDtoList.add(compraDto);
        }
        return compraDtoList;
    }

    @PutMapping("/editPedidoInCloud")
    public String editPedido(@RequestBody CompraDto compraDto) throws InterruptedException, ExecutionException{
        Firestore dbFirestore=fbInitialize.getFirebase();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection("Pedidos").document("2").set(compraDto);
        return collectionApiFuture.get().getUpdateTime().toString();
    }










}
