package juanrns.book_phone.mappers;

import juanrns.book_phone.domain.contact.Contact;
import juanrns.book_phone.domain.contact.ContactDTO;
import juanrns.book_phone.domain.contact.ContactResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "user", ignore = true)
    Contact toContact(ContactDTO contactDTO);
    ContactResponseDTO toContactResponseDTO(Contact contact);
}
