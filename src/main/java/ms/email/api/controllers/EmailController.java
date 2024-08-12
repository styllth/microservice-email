package ms.email.api.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ms.email.api.request.EmailRequest;
import ms.email.api.response.EmailResponse;
import ms.email.domain.models.EmailModel;
import ms.email.domain.services.EmailService;

@AllArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

  @Autowired
  EmailService emailService;

  @PostMapping("/send")
  @ResponseStatus(HttpStatus.CREATED)
  public EmailResponse sendingEmail(@RequestBody @Valid EmailRequest emailRequest) {
    return emailService.sendEmail(emailRequest);
  }

  @PostMapping("/send-assync")
  public ResponseEntity<String> sendingAsyncEmail(@RequestBody @Valid EmailRequest emailRequest) {
    EmailModel emailModel = new EmailModel();
    BeanUtils.copyProperties(emailRequest, emailModel);
    emailService.sendEmailAsync(emailModel);
    return new ResponseEntity<>("Enviado de Forma Assíncrona", HttpStatus.CREATED);
  }

  @GetMapping("/list")
  public Page<EmailResponse> getAllEmails(Pageable pageable) {
    return emailService.findAll(pageable);
  }

  @GetMapping("/{emailId}")
  public EmailResponse showEmail(@PathVariable(value = "emailId") UUID emailId) {
    return emailService.findById(emailId);
  }
}
