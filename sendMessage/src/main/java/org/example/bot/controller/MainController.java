package org.example.bot.controller;

import org.example.bot.component.SenderMessage;
import org.example.bot.data.Message;
import org.example.bot.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Класс, который реагирует на REST запросы
 */
@RestController
public class MainController {

    @Autowired
    private SenderMessage sender;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        Message response = sender.send(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
