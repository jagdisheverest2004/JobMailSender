package org.example.jobmailsender.service;

import jakarta.transaction.Transactional;
import org.example.jobmailsender.model.Recipient;
import org.example.jobmailsender.repository.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientService {

    @Autowired
    private RecipientRepository recipientRepository;

    // Add methods to interact with RecipientRepository as needed
    @Transactional
    public List<Recipient> getFirstTenRecipients() {
        return recipientRepository.findRecipientByLimit(100);
    }

    @Transactional
    public void deleteFirstTenRecipients() {
        List<Recipient> recipients = recipientRepository.findRecipientByLimit(100);
        recipientRepository.deleteAll(recipients);
    }
}
