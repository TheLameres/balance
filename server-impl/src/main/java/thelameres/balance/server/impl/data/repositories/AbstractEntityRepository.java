package thelameres.balance.server.impl.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractEntityRepository<T> extends JpaRepository<T, Long> {
}
