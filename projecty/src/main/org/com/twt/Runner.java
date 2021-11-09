package com.twt;

import com.config.DropWizardConfiguration;
import com.resource.TweetController;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Runner extends Application<DropWizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new Runner().run(args);
    }
    @Override
    public void run(DropWizardConfiguration DropWizardConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new TweetController());
    }
}