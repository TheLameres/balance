package thelameres.balance.server.api.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;

import java.util.List;

public interface BalanceController {

    @GetMapping
    ResponseEntity<List<BalanceDto>> getBalances();

    @GetMapping("/{id}")
    ResponseEntity<BalanceDto> getBalance(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<BalanceDto> newBalance(@RequestBody AmountDto amountDto);

    @PutMapping("/{id}")
    ResponseEntity<BalanceDto> changeBalance(@PathVariable("id") Long id, @RequestBody AmountDto amount);

    @DeleteMapping("/{id}")
    ResponseEntity<DeleteBalanceDto> deleteBalance(@PathVariable("id") Long id);
}
