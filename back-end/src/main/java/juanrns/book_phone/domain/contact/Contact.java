package juanrns.book_phone.domain.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import juanrns.book_phone.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
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
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Contact() {}

    public Contact(ContactDTO contactDTO, User user) {
        this.name = contactDTO.name();
        this.phone = contactDTO.phone();
        this.email = contactDTO.email();
        this.address = contactDTO.address();
        this.favorite = contactDTO.favorite();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
