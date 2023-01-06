package com.example.ourwedding.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("ralucasorinscintei@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        emailSender.send(message);
    }

    // MIME MESSAGE= Multipurpose Internet Mail Extensions
//    public void sendMailWithAttachments(String toEmail,
//                                        String subject,
//                                        String body, String pathAttachment) throws MessagingException, jakarta.mail.MessagingException {
//        MimeMessage message=emailSender.createMimeMessage();
//        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
//        messageHelper.setFrom("gymzillasquad@gmail.com");
//        messageHelper.setTo(toEmail);
//        messageHelper.setSubject(subject);
//        messageHelper.setText(body);
//
//        FileSystemResource fileSystemResource
//                = new FileSystemResource(new File(pathAttachment));
//        messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
//
//        emailSender.send(message);
//        System.out.println("Mail sent successfully!");
//
//    }
}
