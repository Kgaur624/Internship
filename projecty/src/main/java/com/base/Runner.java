package com.base;


import com.config.DropWizardConfiguration;
import com.resource.TweetController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.base","com.config","com.Model","com.resource","com.service" })
public class Runner extends Application<DropWizardConfiguration> {
    private static Logger logger= LoggerFactory.getLogger(Runner.class);
    public static void main(String[] args) throws Exception {
        //logger.info("program is running");
       // new Runner().run(args);
        SpringApplication.run(Runner.class,args);
    }
    @Override
    public void run(DropWizardConfiguration DropWizardConfiguration, Environment environment) {
        environment.jersey().register(new TweetController());
    }
}