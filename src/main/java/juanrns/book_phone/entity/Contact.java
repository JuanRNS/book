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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String name;
    @NotNull
    private String phone;
    private String email;
    private String address;
    private Boolean favorite;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Contact(String name,String phone, String email,String address,Boolean favorite) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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
