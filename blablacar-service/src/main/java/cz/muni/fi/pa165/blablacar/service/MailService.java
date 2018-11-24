package cz.muni.fi.pa165.blablacar.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class MailService {


    @Inject
    private static Config config;


    private String emailHost;


    public  void sendEmail(List<String> recipents, List<String> copyRecipents, String subject, String content){
    }

    public static void main(String[] args) {
        config.getDebugRecipient();
    }
}
