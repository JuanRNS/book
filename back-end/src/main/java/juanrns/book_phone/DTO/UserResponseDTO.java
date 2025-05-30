package juanrns.book_phone.DTO;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
