package juanrns.book_phone.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    public User() {}

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String phoneNumber;
    private UserType userType = UserType.ADMIN;
    private LocalDateTime created;
    private LocalDateTime modified;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    public User(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }



    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        modified = created;
    }

    @PreUpdate
    protected void onUpdate() {
        modified = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Sem roles por enquanto
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
