package ms.email.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.email.domain.models.EmailModel;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
