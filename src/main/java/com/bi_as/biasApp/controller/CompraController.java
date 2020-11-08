package com.bi_as.biasApp.controller;

import com.bi_as.biasApp.dto.ProductoDto;
import com.bi_as.biasApp.service.CompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/k1/compra/producto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompraController {

    CompraService compraService;
    private static final Logger LOGGER= LoggerFactory.getLogger(CompraController.class);

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/addcompra/{tipocompra}/{iduser}")
    public List<ProductoDto> addCompra(@PathVariable("tipocompra")int tipocompra,@PathVariable("iduser")int iduser, @RequestBody List<ProductoDto> productoDto){
        // LOGGER.info(productService.nuevoproducto(productoDto,idtienda).toString());
        return compraService.addCompra(productoDto,tipocompra,iduser);
    }


}
