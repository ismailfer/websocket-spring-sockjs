package com.ismail.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class GreetingController
{
    // Application prefix is "/app"; so the full mapping will be "/app/hello"
    @MessageMapping("/hello")       // we will receive a message here
    @SendTo("/topic/greetings")     // then send it to the topic "/topic/greetings"
    public Greeting greet(HelloMessage message)
    {
        log.info("greet() " + message.getName());

        // timer to see how it goes
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        return Greeting.builder()
                .message("Hello, " + HtmlUtils.htmlEscape(message.getName()))
                .build();
    }
}
