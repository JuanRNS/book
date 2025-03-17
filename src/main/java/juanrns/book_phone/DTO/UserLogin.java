package juanrns.book_phone.DTO;

import jakarta.validation.constraints.NotNull;


public record UserLogin (@NotNull String email, @NotNull String password) {
}
