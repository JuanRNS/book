package juanrns.book_phone.entity;


import lombok.Getter;

@Getter
public enum UserType {
    ADMIN("admin"),
    USER("user");

    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }
}
