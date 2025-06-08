package juanrns.book_phone.domain.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import juanrns.book_phone.domain.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Data
public class Contact {

    public Contact() {}

    public Contact(String name,String phone, String email,String address,Boolean favorite, User user_id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.favorite = favorite;
        this.user = user_id;
    }

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
    private String description;

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
