package org.example.jobmailsender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.jobmailsender.model.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachment(List<Recipient> recipients, String filePath) throws MessagingException, UnsupportedEncodingException {
        for (Recipient recipient : recipients) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String body = createEmailBody(recipient.getName(), recipient.getTitle(), recipient.getCompany());

            helper.setTo(recipient.getEmail());
            helper.setFrom("jagdishswara@gmail.com");
            helper.setSubject("Interest in Contributing to Your Team - UI/UX Designer");
            helper.setText(body, true);

            File resumeFile = new File(filePath);
            helper.addAttachment(resumeFile.getName(), resumeFile);
            mailSender.send(message);
           System.out.println("Email sent to: " + recipient.getName() + " at " + recipient.getEmail());
        }
    }

    private String createEmailBody(String name, String role, String company){

        return "<html><body>" +
                "<p>Dear " + name + ",</p>" +
                "<p>I’m contacting you because I admire your accomplishments as " + role + " at " + company + " and wanted to see whether there is an opportunity to work directly for you.</p>" +
                "<p>I was performing my job search and evaluating companies I want to work for. I didn’t notice a specific opening on your team or within your organization, but came across you as I was digging.</p>" +
                "<p>I was impressed with your leadership in human resource management and organizational development, which are essential for fostering a productive and positive work environment. I feel your guidance in these areas of my growth would be extremely helpful, which is the reason I’m contacting you specifically.</p>" +
                "<p>I offer 2 years of experience as a UI/UX Designer and excellent skills in user research, wireframing, prototyping, and UI design, which should make me a great addition to your team and company. I’ve included my resume in the email. It highlights my career profile and significant accomplishments that are also in alignment with supporting your products and services.</p>" +
                "<p>I’d welcome the opportunity to speak with you if you feel there’s an opportunity for me to join your team. If there isn’t, I’d be open to networking with you and learning from you if you’re interested in helping me. Thank you so much for your consideration.</p>" +
                "<p>Thank you,<br>Charles E Darwin</p>" +
                "<p>LinkedIn: <a href=\"https://www.linkedin.com/in/charlesedarwin/\">https://www.linkedin.com/in/charlesedarwin/</a><br>" +
                "Portfolio: <a href=\"https://charlese.framer.website/\">https://charlese.framer.website/</a><br>" +
                "Behance: <a href=\"https://www.behance.net/charlesedarwin\">https://www.behance.net/charlesedarwin</a></p>" +
                "</body></html>";
    }
}