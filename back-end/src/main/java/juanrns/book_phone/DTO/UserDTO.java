package juanrns.book_phone.DTO;

import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull String name, @NotNull String email, @NotNull String password, String phoneNumber) {
}
