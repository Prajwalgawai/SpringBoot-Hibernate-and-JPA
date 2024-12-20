package com.prajwalgawai.springbootJpa.repositories;

import com.prajwalgawai.springbootJpa.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByOrderByPrice();  //but if we sort like this then for every field we have to define new method
                                                // in repository which is not idea thing therefore creating below generic method

    List<ProductEntity> findBy(Sort sortBy);

    Optional<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfterOrderByTitle(LocalDateTime after);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    @Query("select e.title, e.price from ProductEntity e where e.title=:title and e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);


}