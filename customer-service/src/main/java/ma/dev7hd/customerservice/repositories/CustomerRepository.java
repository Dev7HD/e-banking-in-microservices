package ma.dev7hd.customerservice.repositories;

import ma.dev7hd.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
