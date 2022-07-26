package com.hcmus.smsservice.model.entity;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendGridContent implements AbstractContent {
  private String from;
  private String subject;
  private String to;
  private String content;

  @Override
  public String build() throws IOException {
    Mail mail = new Mail(new Email(from), subject, new Email(to),
        new Content("text/plain", content));
    return mail.build();
  }
}
