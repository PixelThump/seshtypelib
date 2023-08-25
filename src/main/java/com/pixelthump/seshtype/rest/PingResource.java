package com.pixelthump.seshtype.rest;
import com.pixelthump.seshtype.rest.model.ping.Ping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingResource {

    @GetMapping()
    @ResponseBody
    public Ping ping() {

        return new Ping();
    }

}
