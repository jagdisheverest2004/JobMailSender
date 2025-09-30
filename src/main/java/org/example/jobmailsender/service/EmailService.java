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
        int i=0;
        for (Recipient recipient : recipients) {
            ++i;
            // Basic email validation
            if (!recipient.getEmail().matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
                System.err.println("Invalid email: " + recipient.getEmail());
                continue; // skip this recipient
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String body = createEmailBody(recipient.getName(), recipient.getCompany());

            helper.setPriority(1);
            helper.setTo(recipient.getEmail());
            helper.setFrom("charlesedarwin7@gmail.com" , "Charles E Darwin");
            helper.setSubject("Interest in Contributing to Your Team - UI/UX Designer");
            helper.setText(body, true);

            File resumeFile = new File(filePath);
            helper.addAttachment(resumeFile.getName(), resumeFile);
            mailSender.send(message);
           System.out.println((i) + " Email sent to: " + recipient.getName() + " at " + recipient.getEmail());
        }
    }

    public String createEmailBody(String name, String company) throws UnsupportedEncodingException {
        return "<html><body>" +
                "<p>Hi " + name + ",</p>" +
                "<p>I recently came across " + company + "’s impactful work in technology-driven digital solutions and innovation, and I couldn’t help but feel an instant connection. As a passionate UI/UX Designer, I’ve been following your company’s journey and have been continually inspired by your dedication to creating user-friendly, cutting-edge digital products that enhance customer experiences.</p>" +
                "<p>I wanted to reach out because I firmly believe that I would be a valuable asset to your team. With 2 years of experience in UI/UX design for enterprise applications, expertise in user research and data-driven design decisions, and proficiency in industry-standard tools like Figma and Adobe XD, I am confident in my ability to contribute to " + company + "'s continued success.</p>" +
                "<p>I would love the opportunity to discuss how my skills align with the position. If you’re available, I’m open to scheduling a call or meeting at your convenience. </p>" +
                "<p>I am available on weekdays after 5 PM and anytime on the weekend. I am confident that our conversation will shed light on the value I can bring to " + company + ".</p>" +
                "<p>Regards,</p>" +
                "<p>Charles E Darwin<br>" +
                "charlesedarwin7@gmail.com<br>" +
                "+91 86670 81049</p>" +
                "<p>LinkedIn: <a href=\"https://www.linkedin.com/in/charlesedarwin/\">https://www.linkedin.com/in/charlesedarwin/</a><br>" +
                "Portfolio: <a href=\"https://charlese.framer.website/\">https://charlese.framer.website/</a><br>" +
                "Behance: <a href=\"https://www.behance.net/charlesedarwin\">https://www.behance.net/charlesedarwin</a></p>" +
                "</body></html>";
    }
}