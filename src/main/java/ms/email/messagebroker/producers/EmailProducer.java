package ms.email.messagebroker.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ms.email.domain.models.EmailModel;

@Component
public class EmailProducer {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Value(value = "${broker.queue.email.name}")
  private String routingKey;

  public void publishMessageEmail(EmailModel email) {
    rabbitTemplate.convertAndSend("", routingKey, email);
  }

}
