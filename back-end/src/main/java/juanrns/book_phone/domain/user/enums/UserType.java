package juanrns.book_phone.domain.user.enums;


import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("admin"),
    USER("user");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }
}
