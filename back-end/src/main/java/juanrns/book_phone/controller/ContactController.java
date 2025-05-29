package juanrns.book_phone.controller;


import juanrns.book_phone.DTO.ContactDTO;
import juanrns.book_phone.entity.Contact;
import juanrns.book_phone.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/contact")
@RestController
public class ContactController {

    private final ContactService contactService;
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Void> addContact(@RequestBody ContactDTO contact) {
        contactService.CreateContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
    }
}
