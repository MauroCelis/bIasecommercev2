package com.bi_as.biasApp.controller;


import com.bi_as.biasApp.FBInitialize;
import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.dto.PersonaDto;
import com.bi_as.biasApp.dto.ProductoDto;
import com.bi_as.biasApp.service.ProductService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NamedStoredProcedureQueries;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//@CrossOrigin(origins = "http://localhost:4200",maxAge=3600)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/k1/producto")
public class ProductController {
    private ProductService productService;
    private FBInitialize fbInitialize;
    private static final Logger LOGGER=LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController( ProductService productService, FBInitialize fbInitialize){
        this.productService= productService;
        this.fbInitialize=fbInitialize;
    }


    @RequestMapping("/idproducto/{id}")
    public ProductoDto encotraproducto(@PathVariable("id")String id){
        LOGGER.info("Obteniendo id graphic");
        int idNumber=Integer.parseInt(id);
        ProductoDto productoDto=new ProductoDto(productService.findidproduct(idNumber));
        return  productoDto;
    }

    @RequestMapping("/idtienda/{name}")
    public ProductoDto encotraproductonombre(@PathVariable("name")String id){
        LOGGER.info("Obteniendo id graphic");
        ProductoDto productoDto=new ProductoDto(productService.findidproductname(id));
        return  productoDto;
    }

/*
    @PostMapping("/idtienda/{id}")
    public int addproduct(@PathVariable("id")String id,@RequestBody ProductoDto productoDto){
        LOGGER.info("llego aqui xd");
        int idtienda =Integer.parseInt(id);
       // LOGGER.info(productService.nuevoproducto(productoDto,idtienda).toString());
        return productService.nuevoproducto(productoDto,idtienda);
    }
*/
    @PostMapping("/addproduct/{id}")
    public int addproductInCloud(@PathVariable("id")int id,@RequestBody ProductoDto productoDto){
/*        String cad=String.valueOf(productoDto.getName());
        cad=cad.replace(" ","");
        CollectionReference productCR=fbInitialize.getFirebase().collection("Product");
        productCR.document(cad).set(productoDto);
        int idtienda =Integer.parseInt(id);
        int i=productService.addProductDB(productoDto,idtienda);
        // LOGGER.info(productService.nuevoproducto(productoDto,idtienda).toString());
        return productoDto.getIdProduct();*/
        return productService.addProductGeneral(productoDto,id);
//        return productService.nuevoproducto(productoDto,idtienda);

    }


    @PutMapping("/editproducto")
    public int editPublication(@RequestBody ProductoDto productoDto){
        LOGGER.info("Realizando modificacion de editar user  esss "+productoDto.getIdProduct());
        return productService.ediproducto(productoDto);

    }

    @PutMapping("/editproductoInCloud")
    public int editProduct(@RequestBody ProductoDto productoDto) throws InterruptedException, ExecutionException{
/*        Firestore dbFirestore=fbInitialize.getFirebase();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection("Product").document(String.valueOf(productoDto.getIdProduct())).set(productoDto);
        LOGGER.info("Realizando modificacion de editar user  esss "+productoDto.getIdProduct());
        int i=productService.ediproducto(productoDto);
        return i;*/
        return productService.editProductGeneral(productoDto);
//        return collectionApiFuture.get().getUpdateTime().toString();
//        return productService.ediproducto(productoDto);
    }

    @PutMapping("/editproducto/{id}/{inv}")
    public ProductoDto invetarioroducto(@PathVariable("id") int idproducto,@PathVariable("inv") int inve){
        //LOGGER.info("Realizando modificacion de editar user  esss "+productoDto.getIdProduct());
        return productService.invetarioroducto(idproducto,inve);

    }
//invetarioroducto
    @PutMapping("/deleteproducto")
    public String delitPublication(@RequestBody ProductoDto productoDto){
        LOGGER.info("Realizando modificacion de editar user  esss "+productoDto.getIdProduct());


        return productService.deleteproduct(productoDto);

    }

    @RequestMapping("/productlist")
    public List<ProductoDto> getUserNicknameAndPassword() throws InterruptedException, ExecutionException{
        LOGGER.info("Obteniendo id graphic");
        return productService.getListProductsGeneral();
    }
    //getListProductsstore


    @RequestMapping("/productlist/{idstore}")
    public List<ProductoDto> glisproductostore(@PathVariable("idstore") int id) throws InterruptedException, ExecutionException{
        LOGGER.info("Obteniendo id graphic");
        return productService.getListProductsstore(id);
    }

    @RequestMapping("/productlistcloud")
    public List<ProductoDto> getProductListInCloud() throws InterruptedException, ExecutionException {
        List<ProductoDto> productoDtoList=new ArrayList<>();
        CollectionReference product=fbInitialize.getFirebase().collection("Product");
        ApiFuture<QuerySnapshot> querySnapshot=product.get();
        for(DocumentSnapshot doc:querySnapshot.get().getDocuments()){
            ProductoDto productoDto=doc.toObject(ProductoDto.class);
            productoDtoList.add(productoDto);
        }
        LOGGER.info("Obteniendo id graphic");
//        return productService.getlistproduct();
        return productoDtoList;
    }

}
