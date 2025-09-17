package org.example.jobmailsender.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer sno;
    private String name;
    private String email;
    private String title;
    private String company;

    public Recipient(Integer sno, String name, String email, String title, String company) {
        this.sno = sno;
        this.name = name;
        this.email = email;
        this.title = title;
        this.company = company;
    }
}