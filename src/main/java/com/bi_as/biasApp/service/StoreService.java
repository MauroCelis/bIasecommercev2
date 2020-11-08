package com.bi_as.biasApp.service;

import com.bi_as.biasApp.dao.ProductoRepository;
import com.bi_as.biasApp.dao.StroreRepository;
import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.domain.Store;
import com.bi_as.biasApp.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    StroreRepository stroreRepository;
    @Autowired
    public StoreService(StroreRepository stroreRepository) {
        this.stroreRepository = stroreRepository;
    }
    public StoreDto findidstore(int id ){
        return new StoreDto(stroreRepository.findstoreidstore(id));
    }

}
