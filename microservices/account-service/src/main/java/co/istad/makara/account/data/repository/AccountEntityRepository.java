package co.istad.makara.account.data.repository;

import co.istad.makara.account.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, UUID> {
}
