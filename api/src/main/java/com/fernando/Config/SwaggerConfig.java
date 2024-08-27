
package com.fernando.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.info.License;


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
