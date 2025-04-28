package io.github.nagpalankit.creditcardprocessor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Ankit Nagpal");
        myContact.setEmail("nagpalankit@icloud.com");

        Info information = new Info()
                .title("Credit Card Processor API")
                .version("0.1.0")
                .description("This API exposes endpoints to create and fetch cards.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
