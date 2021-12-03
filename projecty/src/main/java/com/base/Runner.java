/**
 * This base package includes Runner class.
 */
package com.base;
import com.config.DropWizardConfiguration;
import com.resource.TweetController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.base", "com.config", "com.Model", "com.resource", "com.service"})
public class Runner extends Application<DropWizardConfiguration> {
     /**
     * main() used to call run().
     *
     * @param args arguments given to run().
     */
    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
    /**
     * run() used to run the class and calls TwitterResources class.
     *
     * @param dropWizardConfiguration calls TwitterConfig class.
     * @param environment   sets environment to run project.
     */
    @Override
    public void run(DropWizardConfiguration dropWizardConfiguration, Environment environment) {
        environment.jersey().register(new TweetController());
    }
}