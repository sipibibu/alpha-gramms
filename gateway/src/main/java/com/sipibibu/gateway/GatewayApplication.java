package com.sipibibu.aplhagramms.main;

import com.sipibibu.aplhagramms.main.infastructure.config.RsaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaProperties.class)
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
