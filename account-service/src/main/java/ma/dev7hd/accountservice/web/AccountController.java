package ma.dev7hd.accountservice.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.accountservice.dtos.AccountDTO;
import ma.dev7hd.accountservice.dtos.NewAccountDTO;
import ma.dev7hd.accountservice.services.IAccountService;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private IAccountService accountService;

    @GetMapping()
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable UUID id) throws AccountNotFoundException {
        return accountService.getAccountById(id);
    }

    @PostMapping("/new")
    public AccountDTO createAccount(@RequestBody NewAccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @PatchMapping("/{id}/update")
    public AccountDTO updateAccount(@PathVariable UUID id, @RequestBody AccountDTO accountDTO) throws AccountNotFoundException {
        return accountService.updateAccount(accountDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id) throws AccountNotFoundException {
        accountService.deleteAccountById(id);
    }
}
