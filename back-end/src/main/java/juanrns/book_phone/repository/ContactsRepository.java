package juanrns.book_phone.repository;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contact,String> {
    List<Contact>findContactsByUserId(Long user_id);
    void deleteById(Long id);

    List<Contact> findByUserAndNameStartingWithIgnoreCase(User user,String prefix);
}
