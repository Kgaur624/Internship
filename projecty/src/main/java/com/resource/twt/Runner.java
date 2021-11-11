package com.resource.twt;

import com.config.DropWizardConfiguration;
import com.resource.resource.TweetController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner extends Application<DropWizardConfiguration> {
    private static Logger logger= LoggerFactory.getLogger(Runner.class);
    public static void main(String[] args) throws Exception {
        logger.info("program is running");
        new Runner().run(args);
    }
    @Override
    public void run(DropWizardConfiguration DropWizardConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new TweetController());
    }
}