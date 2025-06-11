package juanrns.book_phone.domain.user.enums;

public enum UserType {
    ADMIN("admin"),
    USER("user");

    private final String userType;

    public String getUserType() {
        return userType;
    }

    UserType(String userType) {
        this.userType = userType;
    }
}
