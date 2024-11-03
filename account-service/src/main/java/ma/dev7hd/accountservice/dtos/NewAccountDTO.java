package ma.dev7hd.accountservice.dtos;

import lombok.Getter;
import lombok.Setter;
import ma.dev7hd.accountservice.enums.AccountType;
import ma.dev7hd.accountservice.model.Customer;

@Getter @Setter
public class NewAccountDTO {
    private Double balance;
    private AccountType accountType;
    private String currency;
    private Long customerId;
    private Customer customerInfo;
}
