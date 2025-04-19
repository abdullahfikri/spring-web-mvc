package dev.mfikri.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    @GetMapping("/date")
    @ResponseBody
    public String getDate(@RequestParam(name = "date") Date date, HttpServletResponse response) throws IOException {
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }
}
