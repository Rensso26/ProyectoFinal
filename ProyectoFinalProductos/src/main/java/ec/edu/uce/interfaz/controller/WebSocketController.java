package ec.edu.uce.interfaz.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/fabricate")
    @SendTo("/topic/fabrication")
    public String sendFabricationMessage(String message) {
        return message;
    }
}
