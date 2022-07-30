package com.hcmus.smsservice.model.entity;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SendGridContent extends AbstractContent {

  @Override
  public String build() throws IOException {
    Mail mail = new Mail(new Email(from), subject, new Email(to),
        new Content("text/plain", body));
    return mail.build();
  }
}
