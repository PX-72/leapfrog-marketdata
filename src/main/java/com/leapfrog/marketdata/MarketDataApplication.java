package com.leapfrog.marketdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MarketDataApplication {

    public static void main(String[] args) {

        var appCtx = SpringApplication.run(MarketDataApplication.class, args);

        /*for(String s : appCtx.getBeanDefinitionNames()){
            System.out.println(s);
        }*/
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> template){
        return args -> {
            for(int i = 0; i < 100; i++) {
                template.send("leapfrog", Integer.toString(i));
            }
        };
    }

}
