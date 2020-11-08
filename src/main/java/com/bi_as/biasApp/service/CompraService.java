package com.bi_as.biasApp.service;

import com.bi_as.biasApp.dao.CompraRepository;
import com.bi_as.biasApp.dao.ProductCompraRepository;
import com.bi_as.biasApp.dao.ProductoRepository;
import com.bi_as.biasApp.dao.UserClientRepository;
import com.bi_as.biasApp.domain.Compra;
import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.domain.ProductCompra;
import com.bi_as.biasApp.domain.UserClient;
import com.bi_as.biasApp.dto.ProductoDto;
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

    @Autowired
    public CompraService(CompraRepository compraRepository, ProductCompraRepository productCompraRepository,ProductoRepository productoRepository,UserClientRepository userClientRepository) {
        this.compraRepository = compraRepository;
        this.productCompraRepository = productCompraRepository;
        this.productoRepository=productoRepository;
        this.userClientRepository=userClientRepository;
    }

    public List<ProductoDto> addCompra(List<ProductoDto> productoDtoList,int tipocompra,int idcliente){
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
        compra.setUserClientIdUserclient(userClient);
        compraRepository.save(compra);
        for(ProductoDto productoDto:productoDtoList){
            ProductCompra productCompra=new ProductCompra();

            Product product=productoRepository.findprodutbyidProduct(productoDto.getIdProduct());

            product.setQuantity(product.getQuantity()-productoDto.getQuantity());
            productoRepository.save(product);

            productCompra.setActive(1);
            productCompra.setCompraIdCompra(compra);
            productCompra.setProductIdProduct(product);
            productCompraRepository.save(productCompra);
        }
        return  productoDtoList;
    }

}
