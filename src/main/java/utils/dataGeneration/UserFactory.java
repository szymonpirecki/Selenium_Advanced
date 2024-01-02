package utils.dataGeneration;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.user.SocialTitle;
import model.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class UserFactory {
    private static final Faker faker = new Faker();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public User getAlreadyRegisteredUser() {
        return new User.UserBuilder()
                .setFirstName(System.getProperty("firstName"))
                .setLastName(System.getProperty("lastName"))
                .setSocialTitle(SocialTitle.fromString(System.getProperty("socialTitle")))
                .setBirthdate(convertStringToDate(System.getProperty("birthdate")))
                .setEmail(System.getProperty("email"))
                .setPassword(System.getProperty("password"))
                .setReceivePartnerOffers(Boolean.parseBoolean(System.getProperty("receivePartnerOffers")))
                .setAgreeToDataPrivacy(Boolean.parseBoolean(System.getProperty("agreeToDataPrivacy")))
                .setSubscribeToNewsletter(Boolean.parseBoolean(System.getProperty("subscribeToNewsletter")))
                .setAcceptTermsAndPrivacy(Boolean.parseBoolean(System.getProperty("acceptTermsAndPrivacy")))
                .build();

    }

    public User getRandomUser() {
        return new User.UserBuilder()
                .setFirstName(getRandomFirstName())
                .setLastName(getRandomLastName())
                .setSocialTitle(SocialTitle.getRandomTitle())
                .setBirthdate(getRandomBirthdate())
                .setEmail(getRandomEmail())
                .setPassword(getRandomPassword())
                .setReceivePartnerOffers(getRandomBoolean())
                .setAgreeToDataPrivacy(true)
                .setSubscribeToNewsletter(getRandomBoolean())
                .setAcceptTermsAndPrivacy(true)
                .build();
    }

    private Date getRandomBirthdate() {
        return faker.date().birthday();
    }


    @SneakyThrows
    public Date convertStringToDate(String dateString) {
        return formatter.parse(dateString);
    }

    private String getRandomFirstName() {
        return faker.name().firstName();
    }

    private String getRandomLastName() {
        return faker.name().lastName();
    }

    private String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    private String getRandomPassword() {
        return faker.internet().password();
    }

    private boolean getRandomBoolean() {
        return faker.bool().bool();
    }


}
