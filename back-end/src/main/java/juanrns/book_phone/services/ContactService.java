package juanrns.book_phone.services;

import juanrns.book_phone.domain.contact.ContactDTO;
import juanrns.book_phone.domain.contact.ContactResponseDTO;
import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.repository.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.Long.parseLong;

@Service
public class ContactService {

    private final ContactsRepository contactsRepository;

    public ContactService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public Contact createContact(ContactDTO contact, User user) {
        Contact newContact = new Contact(
                contact.name(),
                contact.phone(),
                contact.email(),
                contact.address(),
                contact.favorite(),
                user
        );

        return contactsRepository.save(newContact);
    }

    public List<ContactResponseDTO> getAllContacts(Long userId) {
        return contactsRepository.findContactsByUserId(userId).stream().map(
                contacts -> new ContactResponseDTO(
                        contacts.getId(),
                        contacts.getName(),
                        contacts.getPhone(),
                        contacts.getEmail(),
                        contacts.getAddress(),
                        contacts.getFavorite(),
                        contacts.getCreated(),
                        contacts.getModified()
                )
        ).toList();
    }

    public List<ContactResponseDTO> findByNameStartingWith(User user,String name) {
        List<Contact> contactsList= contactsRepository.findByUserAndNameStartingWithIgnoreCase(user, name);
        return contactsList.stream().map(
                contacts -> new ContactResponseDTO(
                        contacts.getId(),
                        contacts.getName(),
                        contacts.getPhone(),
                        contacts.getEmail(),
                        contacts.getAddress(),
                        contacts.getFavorite(),
                        contacts.getCreated(),
                        contacts.getModified()
                )
        ).toList();
    }

    public void deleteContact(String contactId, User user) {
        Contact contact = contactsRepository.findById(contactId).orElseThrow();

        if (Objects.equals(contact.getUser().getId(), user.getId())) {
            contactsRepository.deleteById(contactId);
        }else {
            throw new RuntimeException("Contact not deleted, you don't have permission");
        }

    }
}
