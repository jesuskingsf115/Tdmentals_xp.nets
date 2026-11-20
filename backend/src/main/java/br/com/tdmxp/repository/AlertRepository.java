package br.com.tdmxp.repository;

import br.com.tdmxp.enums.AlertCategory;
import br.com.tdmxp.enums.RiskLevel;
import br.com.tdmxp.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Optional<Alert> findByExternalId(String externalId);
    List<Alert> findByCityIgnoreCaseAndLocationUfIgnoreCase(String city, String uf);
    List<Alert> findByCategory(AlertCategory category);
    List<Alert> findByRiskLevel(RiskLevel riskLevel);
}
