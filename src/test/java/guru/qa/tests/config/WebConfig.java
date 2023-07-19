package guru.qa.tests.config;


import org.aeonbits.owner.Config;
@Config.Sources("classpath:config/${env}.properties")
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("114.0")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();
    @Key("baseUrl")
    @DefaultValue("https://primekraft.ru")
    String getBaseUrl();
    @Key("remoteDriverUrl")
    String getRemoteUrl();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();
}
