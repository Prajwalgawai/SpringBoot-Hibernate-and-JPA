package com.prajwalgawai.springbootJpa;

import com.prajwalgawai.springbootJpa.entities.ProductEntity;
import com.prajwalgawai.springbootJpa.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringbootJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testRepository() {
//		ProductEntity productEntity = ProductEntity.builder()
//				.sku("nestle234")
//				.title("Nestle Chocolate")
//				.price(BigDecimal.valueOf(23.45))
//				.quantity(4)
//				.build();
//
//		ProductEntity savedProductEntity = productRepository.save(productEntity);
//		System.out.println(savedProductEntity);
//	}

	@Test
	void testRepository2() {
		Optional<ProductEntity> product=productRepository.findByTitle("Coca Cola");

		System.out.println("entities with title Coca Cola is ");
        product.ifPresent(System.out::println);

	}

	@Test
	void getRepository() {
//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
//				LocalDateTime.of(2025, 1, 1, 0, 0, 0 ));
//		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanOrPriceLessThan(4, BigDecimal.valueOf(23.45));
		List<ProductEntity> entities = productRepository.findByTitleContainingIgnoreCase("Parle Biscuit");
		System.out.println("entities are :"+entities);
	}


}
