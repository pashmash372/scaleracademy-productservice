package dev.pashmash.productservice;

import dev.pashmash.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.pashmash.productservice.inheritancedemo.joinedtable.UserRepository;
import dev.pashmash.productservice.repositories.CategoryRepository;
import dev.pashmash.productservice.repositories.PriceRepository;
import dev.pashmash.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProductserviceApplication {//implements CommandLineRunner {

    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository, @Qualifier("jt_ur") UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
////        Mentor mentor = new Mentor();
////        mentor.setName("Naman");
////        mentor.setEmail("Naman@scaler.com");
////        mentor.setAverageRating(4.65);
////        mentorRepository.save(mentor);
////
////        User user = new User();
////        user.setName("Sarath");
////        user.setEmail("sarathcool@yopmail.com");
////        userRepository.save(user);
////
////        userRepository.findAll().forEach(System.out::println);
//
//        Category category = new Category();
//        category.setName("Apple Devices");
//        categoryRepository.save(category);
//
//        Price price = new Price("INR", 100000.00);
////        priceRepository.save(price);
//
//        Product product = new Product();
//        product.setTitle("iPhone 15 Pro");
//        product.setDescription("The best iPhone Ever");
//        product.setCategory(category);
//        product.setPrice(price);
//
//        productRepository.save(product);
//
//        List<Product> inr = productRepository.findAllByPrice_Currency("INR");
//
////        Optional<Category> category1 = categoryRepository.findById(UUID.fromString("d68a882f-dd0c-4596-bbf0-8cfcc0170a27"));
////        System.out.println("Category name is: "+category1.get().getName());
////
////        System.out.println("Printing all products in the category");
////        category1.get().getProducts().forEach(System.out::println);
//
//
//    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<Product> products1 = productRepository.findAllByTitle("iPhone 15 Pro");
//        doSomething();
//    }

//    @Transactional
//    public void doSomething() throws InterruptedException {
//        System.out.println("Doing something");
//        System.out.println("Fetching category 1452d625-138c-4cf0-8079-fe099c1bc035");
//        Thread.sleep(1000);
//        Optional<Category> category1Optional = categoryRepository.findById(UUID.fromString("1452d625-138c-4cf0-8079-fe099c1bc035"));
//        Category category1 = category1Optional.orElse(null);
//    }
}