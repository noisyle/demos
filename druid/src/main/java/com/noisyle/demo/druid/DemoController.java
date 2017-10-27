package com.noisyle.demo.druid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoRepository demoRepository;

    @RequestMapping("/demo")
    public Object demo() {
        return demoRepository.findAll();
    }
}
