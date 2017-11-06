package org.example.bot.controller;

import org.example.bot.component.UserLeader;
import org.example.bot.data.Message;
import org.example.bot.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Класс, который реагирует на REST запросы
 */
@RestController
public class MainController {

    @Value("${urlSenderMessage}")
    private String urlSenderMessage;

    @Autowired
    private UserLeader userLeader;

    private RestTemplate restTemplate;

    @PostConstruct
    private void setUp() {
        restTemplate = new RestTemplate();
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public void decide(@RequestBody MessageRequest request) {
        MessageRequest forwardToLeaderRequest = userLeader.generateMessage(request);
        restTemplate.postForObject(urlSenderMessage, forwardToLeaderRequest, Message.class);
    }
}
