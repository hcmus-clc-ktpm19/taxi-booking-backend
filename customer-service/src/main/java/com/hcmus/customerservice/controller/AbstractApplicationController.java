package com.hcmus.customerservice.controller;

import com.hcmus.customerservice.util.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractApplicationController {

  @Autowired
  protected ApplicationMapper mapper;
}
