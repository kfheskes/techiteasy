package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.TelevisionWallBracket;
import nl.novi.techiteasy.models.TelevisionWallBracketKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Hiermee wordt de interface TelevisionWallBracketRepository uitgebreid van JpaRepository, wat een onderdeel is van Spring Data JPA. Dit geeft de repository toegang tot veel standaard CRUD-operaties (Create, Read, Update, Delete) en query-functionaliteiten.
//
//TelevisionWallBracket: Het type entiteit waarop deze repository van toepassing is.
//TelevisionWallBracketKey: Het type van de samengestelde sleutel van deze entiteit.
public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionWallBracket, TelevisionWallBracketKey> {
    List<TelevisionWallBracket> findAllByTelevisionId(Long televisionId);
    List<TelevisionWallBracket> findAllByWallBracketId(Long wallBracketId);
}
