package co.istad.makara.account.data.repository;

import co.istad.makara.account.data.entity.AccountTypeEntity;
import co.istad.makara.account.domain.valueobject.AccountTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, UUID> {
    Optional<AccountTypeEntity> findByAccountTypeCode(AccountTypeCode accountTypeCode);
}
