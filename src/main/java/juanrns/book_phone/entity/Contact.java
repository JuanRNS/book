package juanrns.book_phone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "contacts")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    private String address;
    private Boolean favorite;
    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
