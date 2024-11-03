package ma.dev7hd.accountservice.services;

import lombok.AllArgsConstructor;
import ma.dev7hd.accountservice.clients.CustomerRestClient;
import ma.dev7hd.accountservice.dtos.AccountDTO;
import ma.dev7hd.accountservice.dtos.NewAccountDTO;
import ma.dev7hd.accountservice.entities.Account;
import ma.dev7hd.accountservice.mappers.IMapper;
import ma.dev7hd.accountservice.model.Customer;
import ma.dev7hd.accountservice.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final IMapper mapper;
    private final CustomerRestClient customerRestClient;

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<Account> accountList = accounts.stream().map(this::addCustomerInfo).toList();
        return mapper.accountsToAccountDTOs(accountList);
    }

    @Override
    public AccountDTO getAccountById(UUID id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return mapper.accountToAccountDTO(addCustomerInfo(account));
    }

    @Transactional
    @Override
    public AccountDTO createAccount(NewAccountDTO accountDTO) {
        Account account = Account.builder()
                .accountType(accountDTO.getAccountType())
                .balance(accountDTO.getBalance())
                .createdAt(new Date())
                .currency(accountDTO.getCurrency())
                .customerId(accountDTO.getCustomerId())
                .build();

        return mapper.accountToAccountDTO(accountRepository.save(addCustomerInfo(account)));
    }

    @Transactional
    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO, UUID id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (accountDTO.getAccountType() != null) account.setAccountType(accountDTO.getAccountType());
        if (accountDTO.getBalance() != null) account.setBalance(accountDTO.getBalance());
        if (accountDTO.getCurrency() != null) account.setCurrency(accountDTO.getCurrency());
        return mapper.accountToAccountDTO(accountRepository.save(addCustomerInfo(account)));
    }

    @Override
    public void deleteAccountById(UUID id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountRepository.delete(account);
    }

    private Account addCustomerInfo(Account account) {
        Customer customer = customerRestClient.getCustomer(account.getCustomerId());
        customer.setId(account.getCustomerId());
        account.setCustomerInfo(customer);
        return account;
    }
}
