package juanrns.book_phone.DTO;

import java.time.LocalDateTime;

public record ContactResponseDTO(
        Long id,
        String name,
        String phone,
        String email,
        String address,
        Boolean favorite,
        LocalDateTime created,
        LocalDateTime modified
) {
}