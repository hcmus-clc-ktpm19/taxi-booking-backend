package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CallCenterRequestDto;
import com.hcmus.wiberback.model.entity.CallCenter;
import java.util.List;

public interface CallCenterService {
  List<CallCenter> getAllCallCenter();

  String saveCallCenter(CallCenterRequestDto callCenterRequestDto);

  String updateCallCenter(CallCenterRequestDto callCenterRequestDto);
}
