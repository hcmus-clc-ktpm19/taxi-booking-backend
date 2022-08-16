package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.repository.CarRequestRepository;
import com.hcmus.wiberback.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  private final CarRequestRepository carRequestRepository;

  @Override
  public List<CarRequest> searchAddress(String phone, String address) {
    return carRequestRepository.searchAddress(phone, address);
  }
}
