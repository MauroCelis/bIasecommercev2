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
import com.google.cloud.firestore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        if(productoDto.getUrlImage().equals("vacio")){
            product.setUrlImage("vacio");
            product.setNameImage("vacio");
        }
        else {
            product.setUrlImage(productoDto.getUrlImage());
            product.setNameImage(productoDto.getNameImage());
        }
//        product.setNameImage("produtoimagen.jpg");
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


    public List<ProductoDto> getListProductsGeneral() throws InterruptedException, ExecutionException{
        compareListOfProducts();
        return  getlistproduct();
    }

    public List<ProductoDto> getListProductsstore(Integer id) {
        List<Product> productList = productoRepository.findproducliststore(id);
        List<ProductoDto> productoDtos=new ArrayList<>();
        for(Product product:productList){
            productoDtos.add(new ProductoDto(product));
        }
        return  productoDtos;
    }

    public List<ProductoDto> getlistproduct() {
        List<Product> productList = productoRepository.findproduclist();
        List<ProductoDto> productoDtos=new ArrayList<>();
        for(Product product:productList){
            productoDtos.add(new ProductoDto(product));
        }
        return  productoDtos;
    }

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

    public void updateListOfProducts(int flag) throws InterruptedException, ExecutionException{
        List<ProductoDto> productListDBproducts=getlistproduct();
        List<ProductoDto> productListCloud=getProductListInCloud();
        ProductoDto productoDto=productListDBproducts.get(flag);
        ProductoDto productoDto1=productListCloud.get(flag);
        productoDto.setQuantity(productoDto1.getQuantity());
        productoDto.setCode(productoDto1.getCode());
        productoDto.setDescription(productoDto1.getDescription());
        productoDto.setCost(productoDto1.getCost());
        productoDto.setName(productoDto1.getName());
        productoDto.setType(productoDto1.getType());
        ediproducto(productoDto);
    }

    public void compareListOfProducts() throws InterruptedException, ExecutionException{
        int flag=-1;
        List<ProductoDto> productListDBproducts=getlistproduct();
        List<ProductoDto> productListCloud=getProductListInCloud();
        if(productListDBproducts.size()==productListCloud.size()){
            for(int i=0;i<productListDBproducts.size();i++){
                ProductoDto productoDto=productListDBproducts.get(i);
                ProductoDto productoDto1=productListCloud.get(i);
                if(productoDto.getQuantity()!=productoDto1.getQuantity()){
                    flag=i;
                }
                if(productoDto.getCode()!=productoDto1.getCode()){
                    flag=i;
                }
                if(!productoDto.getDescription().equals(productoDto1.getDescription())){
                    flag=i;
                }
                BigDecimal numbigdecimal= new BigDecimal(productoDto.getCost());
                BigDecimal numbigdecimal2= new BigDecimal(productoDto1.getCost());
                if((numbigdecimal.compareTo(numbigdecimal2))!=0){
                    flag=i;
                }
                if(!productoDto.getName().equals(productoDto1.getName())){
                    flag=i;
                }
                if(!productoDto.getType().equals(productoDto1.getType())){
                    flag=i;
                }

                if(flag!=-1){
                    updateListOfProducts(flag);
                    flag=-1;
                }
            }
        }
        else {
            int diferenceamonglist=productListCloud.size()-productListDBproducts.size();
            for(int i=1;i<=diferenceamonglist;i++){
                ProductoDto productoDto=productListCloud.get(productListCloud.size()-i);
                addProductDB(productoDto,1);
            }
        }
    }


    public String deleteproduct(ProductoDto productoDto) {

        Product product=productoRepository.findprodutbyidProduct(productoDto.getIdProduct());
        product.setActive(0);
        productoRepository.save(product);
        String statua="se elimino el producto";

        return statua;

    }


}
