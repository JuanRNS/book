package juanrns.book_phone.services;

import juanrns.book_phone.domain.contact.ContactDTO;
import juanrns.book_phone.domain.contact.ContactResponseDTO;
import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.mappers.ContactMapper;
import juanrns.book_phone.repository.ContactsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ContactService {

    private final ContactsRepository contactsRepository;
    private final ContactMapper contactMapper;

    public ContactService(ContactsRepository contactsRepository, ContactMapper contactMapper) {
        this.contactsRepository = contactsRepository;
        this.contactMapper = contactMapper;
    }

    public ContactResponseDTO createContact(ContactDTO contactDTO, User user) {
        Contact newContact = contactMapper.toContact(contactDTO);
        newContact.setUser(user);
        Contact contactSave = contactsRepository.save(newContact);
        return contactMapper.toContactResponseDTO(contactSave);

    }

    public Page<ContactResponseDTO> getAllContacts(Long userId, Pageable pageable) {
        return contactsRepository.findContactsByUserId(userId, pageable)
                .map(contact -> new ContactResponseDTO(
                        contact.getId(),
                        contact.getName(),
                        contact.getPhone(),
                        contact.getEmail(),
                        contact.getAddress(),
                        contact.getFavorite(),
                        contact.getCreated(),
                        contact.getModified()
                ));
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
        Contact contact = contactsRepository.findById(contactId).orElseThrow(
                () -> new RuntimeException("Contact not found")
        );

        if (Objects.equals(contact.getUser().getId(), user.getId())) {
            contactsRepository.deleteById(contactId);
        }else {
            throw new RuntimeException("Contact not deleted, you don't have permission");
        }

    }

    public ContactResponseDTO updateContact(String contactId, ContactDTO contactDTO, User user) {
        Contact contact = contactsRepository.findById(contactId).orElseThrow(
                () -> new RuntimeException("Contact not found")
        );
        if (Objects.equals(contact.getUser().getId(), user.getId())) {
            contact.setName(contactDTO.name());
            contact.setPhone(contactDTO.phone());
            contact.setEmail(contactDTO.email());
            contact.setAddress(contactDTO.address());
            contact.setFavorite(contactDTO.favorite());
            Contact contactSave = contactsRepository.save(contact);
            return contactMapper.toContactResponseDTO(contactSave);
        }else {
            throw new RuntimeException("Contact not updated, you don't have permission");
        }
    }
}
