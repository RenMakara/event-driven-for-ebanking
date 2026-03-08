package co.istad.makara.account.data.repository;

import co.istad.makara.account.data.entity.BranchEntity;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
}
