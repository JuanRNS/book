package juanrns.book_phone.services;

import juanrns.book_phone.DTO.ContactDTO;
import juanrns.book_phone.DTO.ContactResponseDTO;
import juanrns.book_phone.entity.Contact;
import juanrns.book_phone.entity.User;
import juanrns.book_phone.repositories.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactsRepository contactsRepository;

    public ContactService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public ContactResponseDTO CreateContact(ContactDTO contact, User user) {
        Contact contact1 = new Contact(contact.name(),contact.phone(),contact.email(),
                contact.address(),contact.favorite(), user);
        Contact newContact = contactsRepository.save(contact1);

        return new ContactResponseDTO(
                newContact.getId(),
                newContact.getName(),
                newContact.getPhone(),
                newContact.getEmail(),
                newContact.getAddress(),
                newContact.getFavorite(),
                newContact.getCreated(),
                newContact.getModified()
        );
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
}
