package com.javaschool.schedule.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TherapyCase implements Serializable {
    int id;
    String patient;
    String medical;
    String doctor;
    String date;
    String time;
    String status;
}
