package juanrns.book_phone.repositories;

import juanrns.book_phone.entity.Contact;
import juanrns.book_phone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<Contact,String> {
    List <Contact> findContactsByUserId(Long user_id);

    Long user(User user);
}
