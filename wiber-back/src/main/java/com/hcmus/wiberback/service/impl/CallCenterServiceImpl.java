package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.CallCenterRequestDto;
import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.CallCenter;
import com.hcmus.wiberback.model.exception.AccountNotFoundException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.repository.CallCenterRepository;
import com.hcmus.wiberback.service.CallCenterService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CallCenterServiceImpl implements CallCenterService {
  private final CallCenterRepository callCenterRepository;
  private final AccountRepository accountRepository;

  @Override
  public List<CallCenter> getAllCallCenter() {
    return callCenterRepository.findAll();
  }

  @Override
  public String saveCallCenter(CallCenterRequestDto callCenterRequestDto) {
    Account account =
        accountRepository
            .findAccountByPhone(callCenterRequestDto.getPhone())
            .orElseThrow(() -> new AccountNotFoundException("Account not found",
                callCenterRequestDto.getPhone()));

    CallCenter staff = new CallCenter();
    staff.setName(callCenterRequestDto.getName());
    staff.setAccount(account);

    return callCenterRepository.save(staff).getId();
  }

  @Override
  public String updateCallCenter(CallCenterRequestDto callCenterRequestDto) {
    return null;
  }
}
