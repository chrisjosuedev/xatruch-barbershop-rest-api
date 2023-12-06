package dev.chrisjosue.xatruchbarbershopapi.persistance;

import dev.chrisjosue.xatruchbarbershopapi.domain.entity.ShopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopService, Long> {
    List<ShopService> findAllByIsActiveIsTrue();
    Optional<ShopService> findByIdAndIsActiveIsTrue(Long id);
    Optional<ShopService> findByServiceNameIgnoreCaseAndIsActiveIsTrue(String serviceName);
}
