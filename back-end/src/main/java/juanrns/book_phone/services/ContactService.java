package juanrns.book_phone.services;

import juanrns.book_phone.DTO.ContactDTO;
import juanrns.book_phone.entity.Contact;
import juanrns.book_phone.repositories.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactsRepository contactsRepository;

    public ContactService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public void CreateContact(ContactDTO contact) {
        Contact contact1 = new Contact(contact.name(),contact.phone(),contact.email(),contact.address(),contact.favorite());
        contactsRepository.save(contact1);
    }

    public List<Contact> getAllContacts() {
        return contactsRepository.findAll();
    }
}
