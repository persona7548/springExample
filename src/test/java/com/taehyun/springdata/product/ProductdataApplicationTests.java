package com.taehyun.springdata.product;

import com.sun.istack.NotNull;
import com.sun.org.apache.xpath.internal.operations.Equals;
import com.taehyun.springdata.product.entities.Product;
import com.taehyun.springdata.product.repos.ProductRepository;
import javassist.runtime.Desc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ProductdataApplicationTests {

    @Autowired
    ProductRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreate(){
        Product product = new Product();
        product.setId(1);
        product.setName("IPhone");
        product.setDesc("Awesome");
        product.setPrice(1000d);
        repository.save(product);
    }

    @Test
    public void testRead(){
        Product product = repository.findById(1).get();
        System.out.println(">>>>>>>>>>>>>>"+product.getDesc());
    }

    @Test
    public void testUpdate(){
        Product product = repository.findById(1).get();
        product.setPrice(1200d);
        repository.save(product);
    }

    @Test
    public void testDelete(){
        if(repository.existsById(1)){
            System.out.println("Deleting a product");
            repository.deleteById(1);
        }
    }
    @Test
    public void testCount(){
        System.out.println("Total Records =============>>>>>>>>>>>"+repository.count());

    }

    @Test
    public void testFindByNameAndDesc(){
       List<Product> products = repository.findByNameAndDesc("TV","From Samsung Inc");
       products.forEach(p -> System.out.println(p.getPrice()));
    }

    @Test
    public void testFindPriceGreaterThan(){
        List<Product> products = repository.findByPriceGreaterThan(1000d);
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByDescContains(){
        List<Product> products = repository.findByDescContains("Apple");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByBetween(){
        List<Product> products = repository.findByPriceBetween(500d,2500d);
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByDescLike(){
        List<Product> products = repository.findByDescLike("%LG%");
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByIdsIn(){
        Pageable pageable = PageRequest.of(1,2);
        List<Product> products = repository.findByIdIn(Arrays.asList(1,2,3,4));
        products.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPaging(){
        Pageable pageable = PageRequest.of(3,1);
        Iterable<Product> results = repository.findAll(pageable);
        results.forEach(p->System.out.println(p.getName()));
    }

    @Test
    public void testFindAllSorting(){
        repository.findAll(Sort.by(Sort.Direction.DESC, "name","price")).forEach(p->System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPagingAndSorting(){
        Pageable pageable = PageRequest.of(0,2, Sort.Direction.DESC,"name");
        repository.findAll(pageable).forEach(p-> System.out.println(p.getName()));
    }



}



