package model.user;

import lombok.Getter;

@Getter
public enum SocialTitle {
    MR("Mr."), MRS("Mrs.");
    private final String title;

    SocialTitle(String title) {
        this.title = title;
    }

    public static SocialTitle getRandomTitle(){
        return values()[(int) (Math.random() * values().length)];
    }
    public static SocialTitle fromString(String text) {
        for (SocialTitle socialTitle : SocialTitle.values()) {
            if (socialTitle.title.equalsIgnoreCase(text)) {
                return socialTitle;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }


}
