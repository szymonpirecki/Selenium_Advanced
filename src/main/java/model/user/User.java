package model.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private SocialTitle socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthdate;
    private boolean receivePartnerOffers;
    private boolean agreeToDataPrivacy;
    private boolean subscribeToNewsletter;
    private boolean acceptTermsAndPrivacy;

    public User(UserBuilder userBuilder) {
        this.socialTitle = userBuilder.socialTitle;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.birthdate = userBuilder.birthdate;
        this.receivePartnerOffers = userBuilder.receivePartnerOffers;
        this.agreeToDataPrivacy = userBuilder.agreeToDataPrivacy;
        this.subscribeToNewsletter = userBuilder.subscribeToNewsletter;
        this.acceptTermsAndPrivacy = userBuilder.acceptTermsAndPrivacy;
    }

    public static class UserBuilder {
        private SocialTitle socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Date birthdate;
        private boolean receivePartnerOffers;
        private boolean agreeToDataPrivacy;
        private boolean subscribeToNewsletter;
        private boolean acceptTermsAndPrivacy;

        public UserBuilder setSocialTitle(SocialTitle title) {
            this.socialTitle = title;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder setReceivePartnerOffers(boolean receivePartnerOffers) {
            this.receivePartnerOffers = receivePartnerOffers;
            return this;
        }

        public UserBuilder setAgreeToDataPrivacy(boolean agreeToDataPrivacy) {
            this.agreeToDataPrivacy = agreeToDataPrivacy;
            return this;
        }

        public UserBuilder setSubscribeToNewsletter(boolean subscribeToNewsletter) {
            this.subscribeToNewsletter = subscribeToNewsletter;
            return this;
        }

        public UserBuilder setAcceptTermsAndPrivacy(boolean acceptTermsAndPrivacy) {
            this.acceptTermsAndPrivacy = acceptTermsAndPrivacy;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}