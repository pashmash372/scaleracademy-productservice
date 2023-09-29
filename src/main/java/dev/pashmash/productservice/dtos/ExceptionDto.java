package dev.pashmash.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;
}
