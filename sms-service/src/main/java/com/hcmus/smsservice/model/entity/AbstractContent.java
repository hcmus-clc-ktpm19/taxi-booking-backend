package com.hcmus.smsservice.model.entity;

import java.io.IOException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractContent {

  protected String from;

  protected String subject;

  protected String to;

  protected String body;

  public abstract String build() throws IOException;
}
