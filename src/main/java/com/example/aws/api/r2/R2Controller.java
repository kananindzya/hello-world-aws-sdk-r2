package com.example.aws.api.r2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/r2")
public class R2Controller {

    @Autowired
    private R2ServiceImpl r2Service;

    @PostMapping("/bucket")
    public void createBucket(@RequestBody Map<String, String> body) {
        r2Service.createBucket(body.get("bucket"));
    }

    @PostMapping("/file")
    public void uploadFile(@RequestBody Map<String, String> body) {
        r2Service.uploadFileWithStringBody(body.get("bucket"), body.get("key"), body.get("content"));
    }

    @PostMapping("/exists")
    public boolean doesExists(@RequestBody Map<String, String> body) {
        return r2Service.isExistObject(body.get("bucket"), body.get("key"));
    }
}
