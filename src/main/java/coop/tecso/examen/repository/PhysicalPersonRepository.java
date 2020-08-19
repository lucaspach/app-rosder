package coop.tecso.examen.repository;

import coop.tecso.examen.model.PhysicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalPersonRepository extends JpaRepository<PhysicalPerson, Long> {
    boolean existsByRut(long rut);
    PhysicalPerson findByRut(long rut);
}
