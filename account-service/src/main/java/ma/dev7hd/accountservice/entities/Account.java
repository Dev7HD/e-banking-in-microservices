package ma.dev7hd.accountservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.dev7hd.accountservice.enums.AccountType;
import ma.dev7hd.accountservice.model.Customer;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Date createdAt;

    private String currency;

    private Long customerId;

    @Transient
    private Customer customerInfo;
}
