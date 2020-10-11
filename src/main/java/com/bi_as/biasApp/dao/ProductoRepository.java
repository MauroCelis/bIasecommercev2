package com.bi_as.biasApp.dao;

import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.domain.UserSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select * from  product where id_product=?1",nativeQuery = true)
    Product findprodutbyidProduct(int var1);

    @Query(value = "select * from  product where name=?1",nativeQuery = true)
    Product findprodutbyidname(String var1);

    @Query(value = "select * from product",nativeQuery = true)
    List<Product> findproduclist();


}
