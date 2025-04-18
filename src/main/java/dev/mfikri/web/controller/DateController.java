package dev.mfikri.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    @GetMapping("/date")
    public void getDate(@RequestParam(name = "date") Date date, HttpServletResponse response) throws IOException {
        response.getWriter().println(new SimpleDateFormat("yyyyMMdd").format(date));
    }
}
