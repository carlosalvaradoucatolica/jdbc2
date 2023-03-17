package co.edu.ucatolica.jdbc2.util;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@SuperBuilder
public class Response {
    protected LocalDateTime timeStampo;
    protected int statusCode;
    protected HttpStatus status;
    protected String message;
    protected Map<?,?> data;
}