package com.bi_as.biasApp.service;

import com.bi_as.biasApp.dao.ProductoRepository;
import com.bi_as.biasApp.dao.StroreRepository;
import com.bi_as.biasApp.domain.Product;
import com.bi_as.biasApp.domain.Store;
import com.bi_as.biasApp.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<StoreDto> findstorelist(){
        List<Store> findstorelist=stroreRepository.findstorelist();
        List<StoreDto> storeDtoList=new ArrayList<>();
        for(Store store:findstorelist){
            storeDtoList.add(new StoreDto(store));
        }
        return storeDtoList;
    }


}
