package juanrns.book_phone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "users")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    private String phone;
    private String userType;
    private LocalDateTime created;
    private LocalDateTime modified;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;


    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        modified = created;
    }
    @PreUpdate
    protected void onUpdate() {
        modified = LocalDateTime.now();
    }
}
