package com.bi_as.biasApp.service;

import com.bi_as.biasApp.FBInitialize;
import com.bi_as.biasApp.dao.CompraRepository;
import com.bi_as.biasApp.dao.ProductCompraRepository;
import com.bi_as.biasApp.dao.ProductoRepository;
import com.bi_as.biasApp.dao.UserClientRepository;
import com.bi_as.biasApp.domain.Compra;
import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.domain.ProductCompra;
import com.bi_as.biasApp.domain.UserClient;
import com.bi_as.biasApp.dto.CompraDto;
import com.bi_as.biasApp.dto.CompraProductoDto;
import com.bi_as.biasApp.dto.ProductoDto;
import com.google.cloud.firestore.CollectionReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CompraService {

    CompraRepository compraRepository;
    ProductCompraRepository productCompraRepository;
    ProductoRepository productoRepository;
    UserClientRepository userClientRepository;
    FBInitialize fbInitialize;

    @Autowired
    public CompraService(CompraRepository compraRepository, ProductCompraRepository productCompraRepository,ProductoRepository productoRepository,UserClientRepository userClientRepository,FBInitialize fbInitialize) {
        this.compraRepository = compraRepository;
        this.productCompraRepository = productCompraRepository;
        this.productoRepository=productoRepository;
        this.userClientRepository=userClientRepository;
        this.fbInitialize=fbInitialize;
    }

    public int addCompra(CompraDto compraDto, int tipocompra, int idcliente){
        Compra compra=new Compra();
        compra.setDate(new Date().toString());
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String cad=dateFormat.format(date);
        compra.setDate(cad);
        compra.setActive(1);
        compra.setEstado(tipocompra);
        compra.setTxSeller(1);
        UserClient userClient=userClientRepository.finduserbyidclient(idcliente);
        if(userClient==null){
            System.out.println("No esta el cliente");
        }
        compra.setUserClientIdUserclient(userClient);
        compraRepository.save(compra);
        for(CompraProductoDto compraProductoDto:compraDto.getCompraProductoDtoList()){
            ProductCompra productCompra=new ProductCompra();

            Product product=productoRepository.findprodutbyidProduct(compraProductoDto.getId_producto());

            product.setQuantity(product.getQuantity()-compraProductoDto.getQuantity());
            productoRepository.save(product);

            productCompra.setActive(1);
            productCompra.setCompraIdCompra(compra);
            productCompra.setProductIdProduct(product);
            productCompraRepository.save(productCompra);
        }
        return  compra.getActive();
    }

    public int addCompraGeneral(int tipoCompra,int idUser,CompraDto compraDto){
        int i=addCompra(compraDto,tipoCompra,idUser);
        Compra compra=compraRepository.findlastcompraregistered();
        compraDto.setIdCompra(compra.getIdCompra());
        if(tipoCompra==0){
            addCompraCloud(compraDto);
        }
        else {
            addPedido(compraDto);
        }
//        int i=addCompra(compraDto,tipoCompra,idUser);
        return i;
    }

    public void addCompraCloud(CompraDto compraDto){
        CollectionReference compraCR=fbInitialize.getFirebase().collection("Compra");
        String cad="compra"+compraDto.getIdCompra();
        compraCR.document(cad).set(compraDto);
    }


    public void addPedido(CompraDto compraDto){
        CollectionReference compraCR=fbInitialize.getFirebase().collection("Pedidos");
        String cad="pedido"+compraDto.getIdCompra();
        compraCR.document(cad).set(compraDto);
    }

}
