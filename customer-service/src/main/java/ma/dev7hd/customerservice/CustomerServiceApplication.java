package ma.dev7hd.customerservice;

import ma.dev7hd.customerservice.entities.Customer;
import ma.dev7hd.customerservice.repositories.CustomerRepository;
import ma.dev7hd.customerservice.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository CustomerRepository) {
        return args -> {
            List<Customer> customers = new ArrayList<>();
            List.of("Hamza Damiri", "Ali Alami", "Hind Baroudi", "Mustapha Damiri").forEach(name -> {
                String[] nameArray = name.split(" ");
                Customer customer = Customer.builder()
                        .firstName(nameArray[0])
                        .lastName(nameArray[1])
                        .email(nameArray[0].toLowerCase() + nameArray[1].toLowerCase() + "@mail.com")
                        .build();
                customers.add(customer);
            });
            CustomerRepository.saveAll(customers);
        };
    }

}
