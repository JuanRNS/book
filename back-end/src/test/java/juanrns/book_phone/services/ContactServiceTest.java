package juanrns.book_phone.services;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.contact.ContactDTO;
import juanrns.book_phone.domain.contact.ContactResponseDTO;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.mappers.ContactMapper;
import juanrns.book_phone.repository.ContactsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactsRepository contactsRepository;

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactMapper contactMapper;

    private User user;


    @BeforeEach
    void setUp() {
        user = new User("admin","admin@teste.com", "admin", "ADMIN");
    }

    @Test
    void createContactSuccess() {
        ContactDTO contactDTO = new ContactDTO("Juan", "83999993333", "juan@teste.com", "address", true, "Testando");
        Contact contact = new Contact(contactDTO, user);
        ContactResponseDTO contactResponseDTO = new ContactResponseDTO(1L, "Juan", "83999993333", "juan@teste.com", "address", true, LocalDateTime.now(), LocalDateTime.now());

        // Aqui ele cria o mock, simula o comportamento e retorna o valor que eu quero
        when(contactMapper.toContact(contactDTO)).thenReturn(contact);
        when(contactsRepository.save(contact)).thenReturn(contact);
        when(contactMapper.toContactResponseDTO(contact)).thenReturn(contactResponseDTO);

        // Aqui ele chamta o metodo real
        ContactResponseDTO result = contactService.createContact(contactDTO, user);

        assertNotNull(result);
        assertEquals(contactResponseDTO, result);
        // Aqui ele verifica se o metodo foi chamado
        verify(contactsRepository).save(contact);
        verify(contactMapper).toContact(contactDTO);
        verify(contactMapper).toContactResponseDTO(contact);
    }

    @Test
    void getAllContacts() {
        // Arrange
        Contact contact1 = new Contact(new ContactDTO("Juan", "83999993333", "juan@teste.com", "address", true, "Testando"), user);
        Contact contact2 = new Contact(new ContactDTO("Maria", "83999994444", "maria@teste.com", "address2", true, "Testando2"), user);
        List<Contact> contacts1 = Arrays.asList(contact1, contact2);
        Page<Contact> contactsPage = new PageImpl<>(contacts1);
        contact1.setId(1L);
        contact2.setId(2L);
        contact1.setCreated(LocalDateTime.now());
        contact2.setCreated(LocalDateTime.now());
        contact1.setModified(LocalDateTime.now());
        contact2.setModified(LocalDateTime.now());
        Pageable pageable = PageRequest.of(0, 10);

        when(contactsRepository.findContactsByUserId(user.getId(), pageable)).thenReturn(contactsPage);

        Page<ContactResponseDTO> result = contactService.getAllContacts(user.getId(), pageable);

        ContactResponseDTO contactResponseDTO1 = result.getContent().get(0);
        ContactResponseDTO contactResponseDTO2 = result.getContent().get(1);
        Page<ContactResponseDTO> expectedResponse = new PageImpl<>(Arrays.asList(contactResponseDTO1, contactResponseDTO2));

        assertNotNull(result);
        assertEquals(expectedResponse.getTotalElements(), result.getTotalElements());
        assertEquals(expectedResponse.getContent(), result.getContent());
        verify(contactsRepository).findContactsByUserId(user.getId(), pageable);
    }

    @Test
    void findByNameStartingWith() {
        String name = "Ju";
        Contact contact1 = new Contact(new ContactDTO("Juan", "83999993333", "juan@teste.com", "address", true, "Testando"), user);
        List<Contact> contacts = Collections.singletonList(contact1);
        contact1.setId(1L);
        contact1.setCreated(LocalDateTime.now());
        contact1.setModified(LocalDateTime.now());
        ContactResponseDTO response = new ContactResponseDTO(
                contact1.getId(),
                contact1.getName(),
                contact1.getPhone(),
                contact1.getEmail(),
                contact1.getAddress(),
                contact1.getFavorite(),
                contact1.getCreated(),
                contact1.getModified()
        );
        when(contactsRepository.findByUserAndNameStartingWithIgnoreCase(user,name)).thenReturn(contacts);

        List<ContactResponseDTO> result = contactService.findByNameStartingWith(user,name);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(response, result.get(0));
        verify(contactsRepository).findByUserAndNameStartingWithIgnoreCase(user,name);
    }

    @Test
    void deleteContactSucess() {
        Contact contact = new Contact(new ContactDTO("Juan", "83999993333", "juan@teste.com", "address", true, "Testando"), user);
        contact.setId(1L);

        when(contactsRepository.findById(String.valueOf(contact.getId()))).thenReturn(Optional.of(contact));

        contactService.deleteContact(String.valueOf(contact.getId()), user);

        // Aqui ele verifica se o metodo foi chamado
        verify(contactsRepository).deleteById(String.valueOf(contact.getId()));
        verify(contactsRepository).findById(String.valueOf(contact.getId()));

    }

    @Test
    void updateContact() {
        // Arrange
        Long contactId = 1L;
        ContactDTO updateDTO = new ContactDTO("Juan Updated", "83999993333", "juan.updated@teste.com", "new address", true, "Updated notes");
        Contact existingContact = new Contact(new ContactDTO("Juan", "83999993333", "juan@teste.com", "address", true, "Testando"), user);
        existingContact.setId(contactId);
        
        Contact updatedContact = new Contact(updateDTO, user);
        updatedContact.setId(contactId);
        
        ContactResponseDTO expectedResponse = new ContactResponseDTO(contactId, "Juan Updated", "83999993333", "juan.updated@teste.com", "new address", true, LocalDateTime.now(), LocalDateTime.now());

        when(contactsRepository.findById(String.valueOf(contactId))).thenReturn(Optional.of(existingContact));
        when(contactsRepository.save(any(Contact.class))).thenReturn(updatedContact);
        when(contactMapper.toContactResponseDTO(updatedContact)).thenReturn(expectedResponse);

        ContactResponseDTO result = contactService.updateContact(String.valueOf(contactId), updateDTO, user);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(contactsRepository).findById(String.valueOf(contactId));
        verify(contactsRepository).save(any(Contact.class));
        verify(contactMapper).toContactResponseDTO(updatedContact);
    }
}