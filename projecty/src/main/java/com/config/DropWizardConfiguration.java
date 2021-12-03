/**
 * This package contains configuration class.
 */
package com.config;
import io.dropwizard.Configuration;
import org.springframework.context.annotation.Bean;
import twitter4j.conf.ConfigurationBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class DropWizardConfiguration extends Configuration {
    /**
     * String filepath stores the .yml file which contains the authentication keys.
     */
    static String accessTokenSecret = "";
    static String consumerSecret = "";
    static String consumerKey = "";
    static String accessToken = "";
    String filepath = "twitter4j-example.yaml";
    Properties properties = new Properties();
    FileInputStream fileInputStream;

    {
        try {
            fileInputStream = new FileInputStream(filepath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        accessTokenSecret = properties.getProperty("accessTokenSecret");
        consumerSecret = properties.getProperty("consumerSecret");
        consumerKey = properties.getProperty("consumerKey");
        accessToken = properties.getProperty("accessToken");
    }
    /**
     * ConfigurationBuilder class used to set all Authentication keys.
     *
     * @return returns the keys.
     */
    @Bean(name = "Configuration")
    public ConfigurationBuilder getConfigurationObject() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return configurationBuilder;
    }
}
