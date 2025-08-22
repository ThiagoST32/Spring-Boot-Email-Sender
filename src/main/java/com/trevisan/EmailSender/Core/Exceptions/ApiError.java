package com.trevisan.EmailSender.Core.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    private int statusCode;
    private String message;
    private OffsetDateTime offsetDateTime;
}
