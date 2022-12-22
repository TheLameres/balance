package thelameres.balance.server.impl.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serial;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Balance extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 2528173913102493967L;
    private Long amount;
}
