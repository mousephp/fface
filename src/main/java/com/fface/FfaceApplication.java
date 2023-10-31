package com.fface;

import com.fface.base.audit.SpringSecurityAuditorAware;
import com.fface.base.utils.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableJpaAuditing(auditorAwareRef = "auditorRef")
public class FfaceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public SpringSecurityAuditorAware auditorRef() {
		return new SpringSecurityAuditorAware();
	}

	public static void main(String[] args) {
		SpringApplication.run(FfaceApplication.class, args);
	}

}
