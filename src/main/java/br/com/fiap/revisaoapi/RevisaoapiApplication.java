package br.com.fiap.revisaoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "checkpoint 3 java advanced", version = "0.0.1", description = "Fork de API desenvolvida para checkpoint 3 de java advanced"))
public class RevisaoapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevisaoapiApplication.class, args);
    }

}
