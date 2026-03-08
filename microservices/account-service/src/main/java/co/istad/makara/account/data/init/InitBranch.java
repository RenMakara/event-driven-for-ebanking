package co.istad.makara.account.data.init;

import co.istad.makara.account.data.entity.BranchEntity;
import co.istad.makara.account.data.repository.BranchRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitBranch {

    private final BranchRepository branchRepository;

    @PostConstruct
    public void init(){
        if(branchRepository.count() == 0){
            BranchEntity kpt = new BranchEntity();
            kpt.setName("Kampong Thom");
            kpt.setIsOpening(true);
            kpt.setBranchId(UUID.randomUUID());

            BranchEntity phnomPenh = new BranchEntity();
            phnomPenh.setName("Phnom Penh");
            phnomPenh.setIsOpening(true);
            phnomPenh.setBranchId(UUID.randomUUID());

            branchRepository.saveAll(
                    List.of(kpt, phnomPenh)
            );
        }

    }
}