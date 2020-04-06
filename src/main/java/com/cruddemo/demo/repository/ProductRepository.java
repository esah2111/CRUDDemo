package com.cruddemo.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cruddemo.demo.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	@Query(value = "select * from product where product_name = ?1",nativeQuery = true)
	public List<ProductEntity>  findByName(String name);
	
	@Query(value = "select * from product where product_name = ?1 limit 1",nativeQuery = true)
	public Optional<ProductEntity>  findOneByName(String name);
	

}
