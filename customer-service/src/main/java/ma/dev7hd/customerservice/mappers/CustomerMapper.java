package ma.dev7hd.customerservice.mappers;

import lombok.AllArgsConstructor;
import ma.dev7hd.customerservice.dtos.CustomerDto;
import ma.dev7hd.customerservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerMapper implements ICustomerMapper {
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto toDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }
}
