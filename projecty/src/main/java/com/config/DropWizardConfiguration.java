
package com.config;
import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class DropWizardConfiguration extends Configuration {
    public static ConfigurationBuilder getConfigurationObject()
    {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("4tTqUsyffNDC8xO1GURSR7ddc")
                .setOAuthConsumerSecret("AfjdxVTTv9csrw9N7zJmPhoX49lyAN1NZ7PDH7u9z2TNoX18fP")
                .setOAuthAccessToken("1450680649031962626-KOVT11mRbLxLRii3jBTbgX96wMAQbs")
                .setOAuthAccessTokenSecret("ao2D4h0PhFUzahxDLLwSxYNYWh58I6u15GtugJ7fFVxd5");
        return  configurationBuilder;
    }

}