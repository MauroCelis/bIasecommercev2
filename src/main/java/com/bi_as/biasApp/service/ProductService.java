package com.bi_as.biasApp.service;

import com.bi_as.biasApp.FBInitialize;
import com.bi_as.biasApp.dao.ProductoRepository;
import com.bi_as.biasApp.dao.StroreRepository;
import com.bi_as.biasApp.domain.Persona;
import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.domain.Store;
import com.bi_as.biasApp.domain.UserSeller;
import com.bi_as.biasApp.dto.PersonaDto;
import com.bi_as.biasApp.dto.ProductoDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    ProductoRepository productoRepository;
    StroreRepository stroreRepository;
    FBInitialize fbInitialize;
    private static final Logger LOGGER= LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductoRepository productoRepository, StroreRepository stroreRepository,FBInitialize fbInitialize) {
        this.productoRepository = productoRepository;
        this.stroreRepository = stroreRepository;
        this.fbInitialize=fbInitialize;
    }

    public Product findidproduct(int id ){
        return productoRepository.findprodutbyidProduct(id);

    }
    public Product findidproductname(String name ){
        return productoRepository.findprodutbyidname(name);

    }

    public int addProductDB(ProductoDto productoDto,int idtienda){
        Store tienda = new Store();
        tienda=stroreRepository.findstoreidstore(idtienda);

        Product product= new Product();
        //product.setIdProduct(productoDto.getIdProduct());
        product.setName(productoDto.getName());
        product.setDescription(productoDto.getDescription());
        product.setCost(productoDto.getCost());
        product.setCode(productoDto.getCode());
        product.setQuantity(productoDto.getQuantity());
        product.setType(productoDto.getType());
        product.setUrlImage("urlimagenproducto");
        product.setNameImage("produtoimagen.jpg");
        product.setActive(1);
        //Store store= new Store();
        //store=stroreRepository.findstoreidstore(idtienda);
        //System.out.println(store);
        product.setStroreIdStore(tienda);
        productoRepository.save(product);
        ProductoDto productoDto1= new ProductoDto(product);
        return product.getActive();
    }

    public int addProductGeneral(ProductoDto productoDto,int idtienda){
        int i=addProductDB(productoDto,idtienda);
        Product product=productoRepository.findlastproductregistered();
        productoDto.setIdProduct(product.getIdProduct());
        addProductCloud(productoDto);
        return i;
    }


    public void addProductCloud(ProductoDto productoDto){
        String cad=String.valueOf(productoDto.getName());
        cad=cad.replace(" ","");
        CollectionReference productCR=fbInitialize.getFirebase().collection("Product");
        productCR.document(cad).set(productoDto);
    }

    public int ediproducto(ProductoDto productoDto){

        Product product=productoRepository.findprodutbyidProduct(productoDto.getIdProduct());
        //product.setIdProduct(productoDto.getIdProduct());
        product.setName(productoDto.getName());
        product.setDescription(productoDto.getDescription());
        product.setCost(productoDto.getCost());
        product.setType(productoDto.getType());
        product.setQuantity(productoDto.getQuantity());
        product.setCode(productoDto.getCode());
        productoRepository.save(product);
        return product.getActive();
    }

    public void editProductInCloud(ProductoDto productoDto){
        Firestore dbFirestore=fbInitialize.getFirebase();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection("Product").document(String.valueOf(productoDto.getIdProduct())).set(productoDto);
    }


    public int editProductGeneral(ProductoDto productoDto){
        editProductInCloud(productoDto);
        int i=ediproducto(productoDto);
        return i;
    }

    public ProductoDto invetarioroducto( int id ,int cant){

        Product product=productoRepository.findprodutbyidProduct(id);
        //product.setIdProduct(productoDto.getIdProduct());
        //product.setName(productoDto.getName());
        //product.setDescription(productoDto.getDescription());
        //product.setCost(productoDto.getCost());
       // product.setType(productoDto.getType());
        product.setQuantity(product.getQuantity()+cant);
       // product.setCode(productoDto.getCode());
        productoRepository.save(product);
        ProductoDto productoDto = new ProductoDto(product);
        return productoDto;
    }



    public List<ProductoDto> getlistproduct(){
        List<Product> productList = productoRepository.findproduclist();
        List<ProductoDto> productoDtos=new ArrayList<>();
        for(Product product:productList){
            productoDtos.add(new ProductoDto(product));
        }
        return  productoDtos;
    }


    public String deleteproduct(ProductoDto productoDto) {

        Product product=productoRepository.findprodutbyidProduct(productoDto.getIdProduct());
        product.setActive(0);
        productoRepository.save(product);
        String statua="se elimino el producto";

        return statua;

    }

}
