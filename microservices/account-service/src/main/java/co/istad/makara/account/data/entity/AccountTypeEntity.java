package co.istad.makara.account.data.entity;

import co.istad.makara.account.domain.valueobject.AccountTypeCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account_type")
public class AccountTypeEntity {

    @Id
    private UUID accountTypeId;

    @Enumerated(EnumType.STRING)
    private AccountTypeCode accountTypeCode;

    @OneToMany(mappedBy = "accountTypeEntity", cascade = CascadeType.ALL)
    private List<AccountEntity> accountEntity;
}