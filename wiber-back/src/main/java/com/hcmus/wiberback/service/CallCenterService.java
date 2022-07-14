package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.CallCenterRequestDto;
import com.hcmus.wiberback.entity.entity.CallCenter;
import java.util.List;

public interface CallCenterService {
  List<CallCenter> getAllStaff();

  String saveStaff(CallCenterRequestDto callCenterRequestDto);

  String updateStaff(CallCenterRequestDto callCenterRequestDto);
}
