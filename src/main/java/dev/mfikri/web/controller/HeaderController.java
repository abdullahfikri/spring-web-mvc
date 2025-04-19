package dev.mfikri.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeaderController {

    @GetMapping("/header/token")
    @ResponseBody
    public String header (@RequestHeader(name = "X-TOKEN") String token) {
        if (token.equals("FIKRI")) {
            return "OK";
        } else {
            return "KO";
        }
    }
}
