package coop.tecso.examen.repository;

import coop.tecso.examen.model.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {
    boolean existsByRut(long rut);
    LegalPerson findByRut(long rut);
}
