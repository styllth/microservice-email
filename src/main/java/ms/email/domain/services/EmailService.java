package ms.email.domain.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import ms.email.api.converters.Converter;
import ms.email.api.request.EmailRequest;
import ms.email.api.response.EmailResponse;
import ms.email.domain.enums.StatusEmail;
import ms.email.domain.exceptions.ResourceNotFoundException;
import ms.email.domain.models.EmailModel;
import ms.email.domain.repositories.EmailRepository;
import ms.email.messagebroker.producers.EmailProducer;

@AllArgsConstructor
@Service
public class EmailService {

    private EmailRepository emailRepository;
    private JavaMailSender emailSender;
    private Converter converter;
    private EmailProducer userProducer;

    @SuppressWarnings("finally")
    @Transactional
    public EmailResponse sendEmail(EmailRequest emailRequest) {
        EmailModel emailModel = converter.map(emailRequest, EmailModel.class);

        emailModel.setSendDateTimeEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject("<NÃO RESPONDA>" + emailModel.getSubject());
            message.setText("Prezado(a) Usuário(a),\n\n" + emailModel.getText()
                    + "\n\nSistema Integrado da Aviação Naval (SisAvn)"
                    + "\n\nATENÇÃO! Este é um e-mail automático! Favor não responder!");
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return converter.map(emailRepository.save(emailModel), EmailResponse.class);
        }
    }

    public Page<EmailResponse> findAll(Pageable pageable) {
        return converter.mapPage(emailRepository.findAll(pageable), EmailResponse.class);
    }

    public EmailResponse findById(UUID emailId) {
        EmailModel emailModel = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado"));

        return converter.map(emailModel, EmailResponse.class);
    }

    public void sendEmailAsync(EmailModel emailModel) {
        userProducer.publishMessageEmail(emailModel);
    }

}
