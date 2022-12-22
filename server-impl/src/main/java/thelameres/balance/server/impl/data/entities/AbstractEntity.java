package thelameres.balance.server.impl.data.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AbstractEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6696628108529388850L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}