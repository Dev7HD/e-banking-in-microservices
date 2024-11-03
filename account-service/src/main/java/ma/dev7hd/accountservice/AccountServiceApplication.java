package ma.dev7hd.accountservice;

import ma.dev7hd.accountservice.entities.Account;
import ma.dev7hd.accountservice.enums.AccountType;
import ma.dev7hd.accountservice.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository) {
        return args -> {
            List<Account> accounts = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                double r = Math.random();
                Account account = Account.builder()
                        .balance(Math.random() * 10000 + 5000)
                        .currency(Math.random() > 0.5 ? "EUR" : "USD")
                        .createdAt(new Date())
                        .accountType(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .customerId((long) (r > 0.75 ? 1 : r > 0.5 ? 2 : r > 0.25 ? 3 : 4))
                        .build();
                accounts.add(account);
            }
            accountRepository.saveAll(accounts);
        };
    }
}
