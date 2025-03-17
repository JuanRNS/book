package juanrns.book_phone.DTO;

import jakarta.validation.constraints.NotNull;

public record ContactDTO(@NotNull String name, @NotNull String phone, @NotNull String email, String address, Boolean favorite) {
}
