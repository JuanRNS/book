package juanrns.book_phone.domain.user;

import jakarta.validation.constraints.NotNull;


public record UserLogin (@NotNull String email, @NotNull String password) {
}
