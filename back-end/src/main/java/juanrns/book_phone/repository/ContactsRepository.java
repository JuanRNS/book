package juanrns.book_phone.repository;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<Contact,String> {
    Page<Contact> findContactsByUserId(Long user_id, Pageable pageable);

    List<Contact> findByUserAndNameStartingWithIgnoreCase(User user,String prefix);

    Optional<Contact> findById(String id);
}
