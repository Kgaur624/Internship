
package com.config;
import io.dropwizard.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class DropWizardConfiguration extends Configuration {
    public  static  ConfigurationBuilder getConfigurationObject()
    {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("jUw2qaXkcqirbpR0h76dGQPEb")
                .setOAuthConsumerSecret("KlrnGLxD3QfQatAidusemOX8T7OL072Vi2HJppCMrxGL2svkcY")
                .setOAuthAccessToken("1450680649031962626-FlE4DbG86871LzJ6dVrA77aMjR47cy")
                .setOAuthAccessTokenSecret("gEIxm1eWf6ON47WszmZMzm6v9rtzWTo4LGJtIdygsJFeU");
        return  configurationBuilder;
    }

}