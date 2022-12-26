package thelameres.balance.server.impl.services;

import org.springframework.stereotype.Service;
import thelameres.balance.server.api.data.dtos.AmountDto;
import thelameres.balance.server.api.data.dtos.BalanceDto;
import thelameres.balance.server.api.data.dtos.DeleteBalanceDto;
import thelameres.balance.server.api.exceptions.BalanceNotFoundException;
import thelameres.balance.server.impl.data.entities.Balance;
import thelameres.balance.server.impl.data.repositories.BalanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceServerInternalServiceImpl implements BalanceServerInternalService {

    private final BalanceRepository balanceRepository;

    public BalanceServerInternalServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public List<BalanceDto> getBalances() {
        return balanceRepository.findAll().stream().map(this::mapToBalanceDto).toList();
    }

    @Override
    public Optional<BalanceDto> getBalance(Long id) {
        return balanceRepository.findById(id)
                .map(this::mapToBalanceDto);
    }

    @Override
    public BalanceDto saveBalance(BalanceDto balanceDto) {
        Balance balance = new Balance();
        balance.setAmount(balanceDto.amount());
        Balance savedBalance = balanceRepository.save(balance);
        return this.mapToBalanceDto(savedBalance);
    }

    @Override
    public BalanceDto saveBalance(AmountDto amountDto) {
        return this.saveBalance(new BalanceDto(null, amountDto.amount()));
    }

    @Override
    public Optional<BalanceDto> changeBalance(BalanceDto balanceDto) {
        return balanceRepository.findById(balanceDto.id())
                .map(balance -> {
                    balance.setAmount(balance.getAmount() + balanceDto.amount());
                    return balanceRepository.save(balance);
                })
                .map(this::mapToBalanceDto);
    }


    @Override
    public Optional<BalanceDto> changeBalance(Long id, AmountDto amountDto) {
        return this.changeBalance(new BalanceDto(id, amountDto.amount()));
    }

    @Override
    public DeleteBalanceDto deleteBalance(Long id) {
        balanceRepository.deleteById(id);
        if (!balanceRepository.existsById(id)) {
            return new DeleteBalanceDto(id);
        } else {
            throw new BalanceNotFoundException(id, "Balance not found");
        }
    }

    private BalanceDto mapToBalanceDto(Balance balance) {
        return new BalanceDto(balance.getId(), balance.getAmount());
    }
}
