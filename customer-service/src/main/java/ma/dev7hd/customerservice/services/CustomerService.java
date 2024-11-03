package ma.dev7hd.customerservice.services;

import lombok.AllArgsConstructor;
import ma.dev7hd.customerservice.dtos.CustomerDto;
import ma.dev7hd.customerservice.entities.Customer;
import ma.dev7hd.customerservice.mappers.ICustomerMapper;
import org.springframework.stereotype.Service;
import ma.dev7hd.customerservice.repositories.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toDto).toList();
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDto(customer);
    }

    @Transactional
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .build();
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Transactional
    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (customerDto.getFirstName() != null && !customerDto.getFirstName().isEmpty()) customer.setFirstName(customerDto.getFirstName());
        if (customerDto.getLastName() != null && !customerDto.getLastName().isEmpty()) customer.setLastName(customerDto.getLastName());
        if (customerDto.getEmail() != null && !customerDto.getEmail().isEmpty()) customer.setEmail(customerDto.getEmail());
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Transactional
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
