package br.com.pedrosa.ia.story;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

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

@Configuration
class Example {

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {

            var threds = new ArrayList<Thread>();
            var names = new ConcurrentSkipListSet<String>();

            for (var i = 0; i < 1000; i++) {
                var first = i == 0;
                var t = Thread
                        .ofVirtual()
                        .unstarted(() -> {
                            if (first)
                                names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first)
                                names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first)
                                names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first)
                                names.add(Thread.currentThread().toString());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                threds.add(t);
            }

            for (var t : threds) t.start();
            for (var t : threds) t.join();
            System.out.println(names.toString());
        };
    }

}
