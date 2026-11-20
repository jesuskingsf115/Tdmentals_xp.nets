package br.com.tdmxp.repository;

import br.com.tdmxp.model.EmailNotification;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepository extends JpaRepository<EmailNotification, Long> {
    boolean existsByUserIdAndAlertId(Long userId, Long alertId);
}
