package thelameres.balance.server.impl.web.rest.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;
import thelameres.balance.server.api.data.dtos.ErrorDto;
import thelameres.balance.server.api.web.rest.BalanceController;
import thelameres.balance.server.api.exceptions.BalanceNotFoundException;
import thelameres.balance.server.impl.services.BalanceServerInternalService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceControllerVersion1 implements BalanceController {
    private final BalanceServerInternalService balanceServerInternalService;

    public BalanceControllerVersion1(BalanceServerInternalService balanceServerInternalService) {
        this.balanceServerInternalService = balanceServerInternalService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BalanceDto>> getBalances() {
        return ResponseEntity.ok(balanceServerInternalService.getBalances());
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable("id") Long id) {
        return balanceServerInternalService.getBalance(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BalanceNotFoundException(id, "Balance not found"));
    }

    @Override
    @PostMapping
    public ResponseEntity<BalanceDto> newBalance(@RequestBody AmountDto amountDto) {
        return ResponseEntity.ok(balanceServerInternalService.saveBalance(amountDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BalanceDto> changeBalance(@PathVariable("id") Long id, @RequestBody AmountDto amountDto) {
        return balanceServerInternalService
                .changeBalance(id, amountDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BalanceNotFoundException(id, "Balance not found"));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteBalanceDto> deleteBalance(@PathVariable("id") Long id) {
        return ResponseEntity.ok(balanceServerInternalService.deleteBalance(id));
    }

    @ExceptionHandler(BalanceNotFoundException.class)
    public ResponseEntity<ErrorDto> balanceNotFoundExceptionHandler(BalanceNotFoundException balanceNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(balanceNotFoundException.getId(),
                        balanceNotFoundException.getMessage()));
    }
}
