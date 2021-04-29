package org.example.broker.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class HttpErrorResponse {
    private final List<String> errors;
}
