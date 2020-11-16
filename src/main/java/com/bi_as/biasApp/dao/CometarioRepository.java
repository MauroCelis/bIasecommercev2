package com.bi_as.biasApp.dao;

import com.bi_as.biasApp.domain.Cometario;
import com.bi_as.biasApp.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CometarioRepository extends JpaRepository<Cometario,Integer> {

    @Query(value = "select * from cometario",nativeQuery = true)
    List<Cometario> listcomentario();

}
