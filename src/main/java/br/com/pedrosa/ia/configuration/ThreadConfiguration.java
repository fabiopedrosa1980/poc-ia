package br.com.pedrosa.ia.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

@Configuration
class ThreadConfiguration {

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
