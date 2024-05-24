package com.fernando.Config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Configuration
public class ModelMapperConfig {

		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
@InitBinder
public void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true", "false", false));
}
                
}
