package steps.base;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

@Slf4j
public class BaseSteps {
    public Random random;

    public WebDriver driver;
    public SoftAssertions softAssertions;

    public BaseSteps(WebDriver driver) {
        init(driver);
    }

    private void init(WebDriver driver) {
        this.driver = driver;
        this.softAssertions = new SoftAssertions();
        this.random = new Random();
    }

    @SneakyThrows
    public <T extends BasePage> T at(Class<T> pageType) {
        log.info("Creating instance: {}", pageType.getSimpleName());
        return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    @SneakyThrows
    public <T extends BasePage> void at(Class<T> pageType, Consumer<T> pageAction) {
        log.info("Creating instance: {}", pageType.getSimpleName());
        var page = pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        pageAction.accept(page);
    }
    public <T> T getRandom(List<T> listOfElements) {
        if (listOfElements.isEmpty()) {
            log.error("Can't get random element - list is empty.");
            return null;
        }
        int randomIndex = random.nextInt(listOfElements.size());
        return listOfElements.get(randomIndex);
    }
}
