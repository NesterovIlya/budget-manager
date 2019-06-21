package com.github.nesterovilya.budgetmanager.rest;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Ilya Nesterov
 */

@Slf4j
@RestController
@RequestMapping("/echo")
public class EchoController {

    @RequestMapping(value = "/test", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto echo() {
        log.info("GET /echo/test");

        return new ResponseDto("Hello World!");
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode(of = {"message"})
    @ToString(of = {"message"})
    public static class ResponseDto {
        private String message;
    }
}
