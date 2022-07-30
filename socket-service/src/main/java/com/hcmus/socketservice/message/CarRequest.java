package com.hcmus.socketservice.message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private String id;
    private String customerId;
    private String pickingAddress;
    private String arrivingAddress;
    private Double lngPickingAddress;
    private Double latPickingAddress;
    private Double lngArrivingAddress;
    private Double latArrivingAddress;
}
