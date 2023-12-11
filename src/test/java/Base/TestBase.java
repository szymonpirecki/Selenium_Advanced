package Base;

import configuration.handler.BrowserHandler;
import configuration.handler.EnvironmentHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import providers.UrlProvider;

import java.util.Random;

@Slf4j
public class TestBase {

    public WebDriver driver;
    public Random random;

    @BeforeAll
    static void beforeAll() {
        initializeTestEnvironment();
    }

    private static void initializeTestEnvironment() {
        EnvironmentHandler.setEnvironmentProperties();
        BrowserHandler.setBrowserProperties();
    }

    @BeforeEach
    void setUp() {
        BrowserHandler browserHandler = new BrowserHandler();
        if (this.driver == null)
            driver = browserHandler.initDriver();
        driver.get(UrlProvider.HOME_URL.getUrl());
        random = new Random();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info("Driver closed properly");
    }

//    @SneakyThrows
//    public <T extends BasePage> T at(Class<T> pageType) {
//        log.info("Creating instance: {}", pageType.getSimpleName());
//        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
//    }

}