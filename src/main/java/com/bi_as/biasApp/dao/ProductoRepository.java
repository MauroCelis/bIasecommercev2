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

    @Query(value = "select * from product where active=1",nativeQuery = true)
    List<Product> findproduclist();

    @Query(value = "select * from product where active=1 and strore_id_store=?1 ",nativeQuery = true)
    List<Product> findproducliststore(int var1);


    @Query(value = "select * from product ORDER BY id_product DESC LIMIT 1",nativeQuery = true)
    Product findlastproductregistered();



}
