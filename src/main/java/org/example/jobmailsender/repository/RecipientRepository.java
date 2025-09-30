package org.example.jobmailsender.repository;

import org.example.jobmailsender.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    @Query("SELECT r FROM Recipient r ORDER BY r.sno ASC limit ?1")
    List<Recipient> findRecipientByLimit(int i);
}