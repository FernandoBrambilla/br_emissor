
package com.fernando.Config;


import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
   
    
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                    .title("Br Sisetmas de Gestão - Api geral")
                    .version("v 1.0")
                    .description("ERP Gestão")
                    .termsOfService("http://brsistemas.com.br/termos")
                    .license(new License()
                        .name("Apache 2.0")
                        .url("http://brsistemas.com.br/licenca")
                    )
                );
                
                
    }
   
    
    
    
   
}
