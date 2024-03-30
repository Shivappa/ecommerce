package com.techloom.EcomProductService.service;

import com.techloom.EcomProductService.model.Category;
import com.techloom.EcomProductService.model.Order;
import com.techloom.EcomProductService.model.Price;
import com.techloom.EcomProductService.model.Product;
import com.techloom.EcomProductService.repository.CategoryRepository;
import com.techloom.EcomProductService.repository.OrderRepository;
import com.techloom.EcomProductService.repository.PriceRepository;
import com.techloom.EcomProductService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitServiceImpl implements InitService {

    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public InitServiceImpl(CategoryRepository categoryRepository,
                           PriceRepository priceRepository,
                           ProductRepository productRepository,
                            OrderRepository orderRepository) {
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void initialize() {
        Category category = new Category();
        category.setCategoryName("Electronics");

        category = categoryRepository.save(category);

        Price iphone = new Price();
        iphone.setAmount(80000);
        iphone.setCurrency("INR");
        iphone.setDiscount(0);

        Price macbook = new Price();
        macbook.setAmount(180000);
        macbook.setCurrency("INR");
        macbook.setDiscount(0);

        Price ipad = new Price();
        ipad.setAmount(60000);
        ipad.setCurrency("INR");
        ipad.setDiscount(0);

        iphone = priceRepository.save(iphone);
        macbook = priceRepository.save(macbook);
        ipad = priceRepository.save(ipad);

        Product productIphone = new Product();
        productIphone.setCategory(category);
        productIphone.setTitle("iPhone 14");
        productIphone.setDescription("Best iPhone");
        productIphone.setPrice(iphone);
        productIphone.setImage("http://randomimage");

        Product productMacbook = new Product();
        productMacbook.setCategory(category);
        productMacbook.setTitle("MacBook Pro 16");
        productMacbook.setDescription("Best Mac");
        productMacbook.setPrice(macbook);
        productMacbook.setImage("http://randomimagemac");

        Product productIpad = new Product();
        productIpad.setCategory(category);
        productIpad.setTitle("iPad Air");
        productIpad.setDescription("Best iPad");
        productIpad.setPrice(ipad);
        productIpad.setImage("http://randomimageipad");

        productIphone = productRepository.save(productIphone);
        productMacbook = productRepository.save(productMacbook);
        productIpad = productRepository.save(productIpad);

        Order order = new Order();
        order.setProducts(List.of(productIphone, productMacbook, productIpad));

        order = orderRepository.save(order);
    }
}
