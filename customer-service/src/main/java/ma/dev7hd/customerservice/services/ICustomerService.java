package ma.dev7hd.customerservice.services;

import ma.dev7hd.customerservice.dtos.CustomerDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    @Transactional
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    @Transactional
    void deleteCustomer(Long id);
}
