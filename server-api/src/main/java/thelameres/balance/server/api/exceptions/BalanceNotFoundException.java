package thelameres.balance.server.api.exceptions;

import lombok.Getter;

import java.io.Serial;

public class BalanceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8370918141313251635L;

    @Getter
    private final Long id;

    public BalanceNotFoundException(Long id, String s) {
        super(s);
        this.id = id;
    }
}
