package dev.mfikri.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;

@Controller
public class UploadController {

    @PostMapping(path = "/upload/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload (
            @RequestParam("name") String name,
            @RequestPart("profile") MultipartFile profile
    ) throws IOException {
        Path path = Path.of("upload/" + ZonedDateTime.now().toEpochSecond() + profile.getOriginalFilename());
        // Files.write(path, profile.getBytes()); // manual, equal to profile.transferTo(path)
        profile.transferTo(path);
        return "Success save profile " + name + " to " + path;
    }

}
