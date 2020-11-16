package com.bi_as.biasApp.dao;

import com.bi_as.biasApp.domain.Compra;
import com.bi_as.biasApp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompraRepository extends JpaRepository<Compra,Integer> {
    @Query(value = "select * from compra ORDER BY id_compra DESC LIMIT 1",nativeQuery = true)
    Compra findlastcompraregistered();
}
