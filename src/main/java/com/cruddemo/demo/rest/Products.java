package com.cruddemo.demo.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cruddemo.demo.entity.ProductEntity;
import com.cruddemo.demo.models.AddProductRequest;
import com.cruddemo.demo.repository.ProductRepository;

@RestController
public class Products {
	
	@Autowired
	ProductRepository productRepository;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/product/all")
	public List<ProductEntity> getAllproducts() {
		List<ProductEntity> productEntitiesList = new ArrayList<>();
		
		return productEntitiesList = productRepository.findAll();
		
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/product/{productName}")
	public List<ProductEntity> getProductByName(@PathVariable(name = "productName") String productName) {
		List<ProductEntity> productEntitiesList = new ArrayList<>();
		
		 return productEntitiesList = productRepository.findByName(productName);
		
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/product/add")
	public List<ProductEntity> addProduct(@RequestBody AddProductRequest requestbody) {
		
		Optional<ProductEntity> p = productRepository.findOneByName(requestbody.getProductName());
		
		if(p.isPresent())
		{
			
			ProductEntity editedProduct = 	p.get();
			editedProduct.setInStock(requestbody.getInStock());
			editedProduct.setName(requestbody.getProductName());
			editedProduct.setPrice(requestbody.getPrice());
			
			productRepository.save(editedProduct);

		}
		else
		{	
			ProductEntity p1 = new ProductEntity();
			p1.setName(requestbody.getProductName());
			p1.setPrice(requestbody.getPrice());
			p1.setInStock(requestbody.getInStock());
			productRepository.save(p1);
		}
		
		 return  productRepository.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/product/{productName}")
	public List<ProductEntity> deleteProduct(@PathVariable(name = "productName") String productName) {
		
		Optional<ProductEntity> p = productRepository.findOneByName(productName);
		if(p.isPresent())
		{
			productRepository.deleteById(p.get().getId());			
		}		
		
		 return  productRepository.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/product/add")
	public List<ProductEntity> editProduct(@RequestBody AddProductRequest requestbody) {
		
		Optional<ProductEntity> p = productRepository.findOneByName(requestbody.getProductName());
		
		if(p.isPresent())
		{
			ProductEntity newProduct = 	p.get();
			newProduct.setInStock(requestbody.getInStock());
			newProduct.setName(requestbody.getProductName());
			newProduct.setPrice(requestbody.getPrice());
			
			productRepository.save(newProduct);
			
		}		
		
		 return  productRepository.findAll();
		
	}
	
	
	

}
