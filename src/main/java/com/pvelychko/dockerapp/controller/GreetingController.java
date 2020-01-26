package com.pvelychko.dockerapp.controller;

import com.pvelychko.dockerapp.domain.Account;
import com.pvelychko.dockerapp.domain.Type;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;
import java.util.Map;

@RestController
@Description("A controller for handling greeting message requests")
public class GreetingController {

    @GetMapping(value = "/greeting/{account}")
    @ResponseBody
    public Map<String, String> greeting(@PathVariable Account account,
                                        @RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam(value = "type", required = false) Type type) {
        if (id != null && id < 1) {
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        }

        switch (account) {
            case personal:
                return Collections.singletonMap("message", "Hi, userId " + id);
            case business:
                if (type == Type.small) {
                    throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
                } else
                    if (type == Type.big) {
                        return Collections.singletonMap("message", "Welcome, business user!");
                    }
                break;
            default:
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        }

        return Collections.singletonMap("message", "Default message");
    }
}
