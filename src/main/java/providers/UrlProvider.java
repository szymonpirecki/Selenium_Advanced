package providers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum UrlProvider {

    HOME_URL("urls-home"),
    LOGIN_URL("urls-login"),
    BASKET_URL("urls-basket");
    private final String propertyName;

    UrlProvider(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getUrl() {
        String url = System.getProperty(propertyName);
        log.info("Providing: {}", url);
        return url;
    }
}
