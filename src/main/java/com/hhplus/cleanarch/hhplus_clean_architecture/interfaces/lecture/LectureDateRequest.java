package com.hhplus.cleanarch.hhplus_clean_architecture.interfaces.lecture;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LectureDateRequest {
    private LocalDate startAt;
    private LocalDate endAt;
}
