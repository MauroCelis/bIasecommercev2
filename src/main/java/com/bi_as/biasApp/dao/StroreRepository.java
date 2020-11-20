package com.bi_as.biasApp.dao;

import com.bi_as.biasApp.domain.Persona;
import com.bi_as.biasApp.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StroreRepository extends JpaRepository<Store, Integer> {
    @Query(value="SELECT * FROM strore order by strore.id_store DESC LIMIT 1",nativeQuery = true)
    Store findLastStroreRegister();

    @Query(value = "select * from  store where id_store=?1",nativeQuery = true)
    Store findstoreidstore(int var1);


    @Query(value = "select * from  store",nativeQuery = true)
    List<Store> findstorelist();
}
