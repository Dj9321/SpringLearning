package com.dj.learning.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
public class MailConfigFromProperties {
	private String hostName;
	private int port;
	private String from;

}
