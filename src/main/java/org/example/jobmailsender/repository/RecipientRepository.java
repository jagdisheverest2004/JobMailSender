package org.example.jobmailsender.repository;

import org.example.jobmailsender.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}