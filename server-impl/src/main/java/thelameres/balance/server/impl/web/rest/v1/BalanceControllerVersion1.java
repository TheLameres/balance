package thelameres.balance.server.impl.web.rest.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;
import thelameres.balance.server.api.web.rest.BalanceController;
import thelameres.balance.server.impl.data.entities.Balance;
import thelameres.balance.server.impl.data.repositories.BalanceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceControllerVersion1 implements BalanceController {
    private final BalanceRepository balanceRepository;

    public BalanceControllerVersion1(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BalanceDto>> getBalances() {
        return ResponseEntity.ok(balanceRepository.findAll().stream().map(this::mapToBalanceDto).toList());
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable("id") Long id) {
        Optional<Balance> balanceById = balanceRepository.findById(id);
        return balanceById.map(balance -> ResponseEntity.ok(this.mapToBalanceDto(balance))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<BalanceDto> newBalance(@RequestBody AmountDto amountDto) {
        Balance balance = new Balance();
        balance.setAmount(amountDto.amount());
        Balance savedBalance = balanceRepository.save(balance);
        return ResponseEntity.ok(this.mapToBalanceDto(savedBalance));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BalanceDto> changeBalance(@PathVariable("id") Long id, @RequestBody AmountDto amountDto) {
        Optional<Balance> balanceById = balanceRepository.findById(id);
        if (balanceById.isPresent()) {
            Balance balance = balanceById.get();
            balance.setAmount(balance.getAmount() + amountDto.amount());
            Balance savedBalance = balanceRepository.save(balance);
            return ResponseEntity.ok(this.mapToBalanceDto(savedBalance));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteBalanceDto> deleteBalance(@PathVariable("id") Long id) {
        balanceRepository.deleteById(id);
        if (!balanceRepository.existsById(id)) {
            return ResponseEntity.ok(new DeleteBalanceDto(id));
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    private BalanceDto mapToBalanceDto(Balance balance) {
        return new BalanceDto(balance.getId(), balance.getAmount());
    }
}
