package br.com.pedrosa.ia.story;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StoryController {

    private final ChatClient chatClient;

    public StoryController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/story")
    Map<String, String> story() {
        var prompt = """
                                
                Prezado Chat
                                
                Por favor me conta a hisotoria do banco Itaú ao longo dos tempos.
                                
                """;
        var response = this.chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        return Map.of("story", response);
    }


}

