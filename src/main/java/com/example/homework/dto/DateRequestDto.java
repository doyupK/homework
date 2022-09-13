package com.example.homework.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;


@Getter
@Setter
public class DateRequestDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.sss x")
    private Timestamp startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
