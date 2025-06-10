package juanrns.book_phone.mappers;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.contact.ContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {

    ContactDTO toContactDTO(juanrns.book_phone.domain.contact.Contact contact);
    Contact toContact(ContactDTO contactDTO);
}
