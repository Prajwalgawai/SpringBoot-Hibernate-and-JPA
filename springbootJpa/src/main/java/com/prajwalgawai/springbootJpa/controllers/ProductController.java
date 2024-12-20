package com.prajwalgawai.springbootJpa.controllers;

import com.prajwalgawai.springbootJpa.entities.ProductEntity;
import com.prajwalgawai.springbootJpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/products")

public class ProductController {
    private final int PAGE_SIZE=5;

    @Autowired
    ProductRepository productRepository;

//    @GetMapping
//    public List<ProductEntity> getAllProducts(){ //but if we sort like this then for every field we have to define new method
//                                                // in repository which is not idea thing therefore creating below method
//        List<ProductEntity> ans=productRepository.findByOrderByPrice();
//        System.out.println("list is a "+ans);
//        return ans;
//    }

    @GetMapping
    public List<ProductEntity> findBy(@RequestParam(defaultValue = "id") String sortBy){

        return productRepository.findBy(Sort.by(Sort.Order.desc(sortBy),
                                           Sort.Order.asc("price")));  //like this we can do nested sorting also.
    }

    @GetMapping(path="/page")
    public Page<ProductEntity> getPage(@RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable= PageRequest.of(0, PAGE_SIZE, Sort.by(Sort.Direction.ASC, sortBy));
        return productRepository.findAll(pageable);   //this findAll is built in method but when we pass pageable instance
                                                     // as an argument it returns page along with metadata of that page
        //if we write productRepository.findAll(pageable).getContent(); then it will return only page excluding metadata

        //Note : we can also pass this pageable instance as a parameter to any Jpa repository method and we will receive data
        //       with page functionality.
        //If we have passed pageable parameter in repo jpa method but dont want page functionality then at the time of calling
        //simply pass null argument value for pageable parameter.

    }

}
