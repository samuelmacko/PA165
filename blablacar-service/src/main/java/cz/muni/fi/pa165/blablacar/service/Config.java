package cz.muni.fi.pa165.blablacar.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@PropertySource("classpath:blablacar-service.properties")
public class Config {

    @Value("${emailHost}")
    private String emailHost;

    @Value("${emailSender}")
    private String emailSender;


    @Value("${debugRecipent}")
    private String debugRecipient;


    @Value("$enableEmails")
    private String enableEmails;

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getDebugRecipient() {
        return debugRecipient;
    }

    public void setDebugRecipient(String debugRecipient) {
        this.debugRecipient = debugRecipient;
    }

    public String getEnableEmails() {
        return enableEmails;
    }

    public void setEnableEmails(String enableEmails) {
        this.enableEmails = enableEmails;
    }

    public Config() {
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
