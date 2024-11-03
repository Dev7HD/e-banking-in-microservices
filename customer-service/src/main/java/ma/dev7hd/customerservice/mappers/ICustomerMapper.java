package ma.dev7hd.customerservice.mappers;

import ma.dev7hd.customerservice.dtos.CustomerDto;
import ma.dev7hd.customerservice.entities.Customer;

public interface ICustomerMapper {
    CustomerDto toDto(Customer customer);
}
