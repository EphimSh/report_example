package guru.qa.tests.config;


import org.aeonbits.owner.Config;
@Config.Sources("classpath:config/my.properties")
public interface CredentialsConfig extends Config {
    String login();
    String password();
}
