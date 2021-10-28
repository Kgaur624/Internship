package com.twt;




import com.config.CConfiguration;
import com.resource.Main;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Runner extends Application<CConfiguration> {
    public static void main(String[] args) throws Exception {
        new Runner().run(args);
    }
    @Override
    public void run(CConfiguration CConfiguration, Environment environment) throws Exception {
        System.out.println("running");
        environment.jersey().register(new Main());
    }
}