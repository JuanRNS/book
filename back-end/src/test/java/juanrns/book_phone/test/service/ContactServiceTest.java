package juanrns.book_phone.test.service;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.contact.ContactDTO;
import juanrns.book_phone.domain.contact.ContactResponseDTO;
import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.mappers.ContactMapper;
import juanrns.book_phone.repository.ContactsRepository;
import juanrns.book_phone.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactsRepository contactsRepository;

    @Mock
    private ContactMapper contactMapper;

    private ContactDTO contactDTO;
    private Contact contact;
    private ContactResponseDTO responseDTO;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Juan teste", "juanteste@gmail.com", "1234", "8399999999");
        user.setId(1L);
        contactDTO = new ContactDTO("John Doe", "1234567890", "john@example.com", "123 Test St", false, "Test description");
        contact = new Contact("John Doe", "1234567890", "john@example.com", "123 Test St", false, user);
        responseDTO = new ContactResponseDTO(1L, "John Doe", "1234567890", "john@example.com", "123 Test St", false, null, null);
    }


    @Test
    void testCreateContact() {
        when(contactMapper.toContact(contactDTO)).thenReturn(contact);

        when(contactsRepository.save(contact)).thenReturn(contact);

        when(contactMapper.toContactResponseDTO(contact)).thenReturn(responseDTO);

        ContactResponseDTO result = contactService.createContact(contactDTO, user);

        assertNotNull(result);
        assertEquals(contactDTO.name(), result.name());
        assertEquals(contactDTO.phone(), result.phone());

        verify(contactMapper).toContact(contactDTO);
        verify(contactsRepository).save(contact);
        verify(contactMapper).toContactResponseDTO(contact);
    }

    @Test
    void testDeleteContact() {
       when(contactsRepository.findById("1")).thenReturn(Optional.of(contact));
       contactService.deleteContact("1", user);
       verify(contactsRepository).findById("1");
    }


}
