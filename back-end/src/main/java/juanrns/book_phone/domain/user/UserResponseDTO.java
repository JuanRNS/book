package juanrns.book_phone.domain.user;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
