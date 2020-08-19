package coop.tecso.examen.controller;


import coop.tecso.examen.dto.CustomerDTO;
import coop.tecso.examen.model.PhysicalPerson;
import coop.tecso.examen.model.LegalPerson;
import coop.tecso.examen.repository.LegalPersonRepository;
import coop.tecso.examen.repository.PhysicalPersonRepository;
import coop.tecso.examen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private LegalPersonRepository legalPersonRepository;
    @Autowired
    private PhysicalPersonRepository physicalPersonRepository;
    @Autowired
    private CustomerService customerService;


    @PostMapping("/sign-up/physical")
    public ResponseEntity signUp(@RequestBody PhysicalPerson customer) {
        try {
            /* no conozco el tipo de restricción que tiene o el formato de un rut, pero asumo la validación de que sea
            un entero positivo. */
            if (customer.getRut() <= 0) return ResponseEntity.status(400).body("RUT must be a positive number");

            // logica de negocio
            if (!customerService.rutValidation(customer.getRut()))
                return ResponseEntity.status(400).body("This RUT: " + customer.getRut() + " already exits");
            if (!customerService.firstNameValidation(customer.getFirstName()))
                return ResponseEntity.status(400).body("First Name maxlength: 80");
            if (!customerService.lastNameValidation(customer.getLastName()))
                return ResponseEntity.status(400).body("Last Name maxlength: 250");

            physicalPersonRepository.save(customer);

            return ResponseEntity.status(201).body(customer);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @PostMapping("/sign-up/legal")
    public ResponseEntity signUp(@RequestBody LegalPerson customer) {
        try {
            if (customer.getRut() <= 0) return ResponseEntity.status(400).body("RUT must be a positive number");

            // logica de negocio
            if (!customerService.rutValidation(customer.getRut()))
                return ResponseEntity.status(400).body("This RUT: " + customer.getRut() + " already exits");
            if (!customerService.businessNameValidation(customer.getBusinessName()))
                return ResponseEntity.status(400).body("Business Name maxlength: 100");

            legalPersonRepository.save(customer);

            return ResponseEntity.status(201).body(customer);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        try {
            List<PhysicalPerson> fisicas = physicalPersonRepository.findAll();
            List<LegalPerson> juridicas = legalPersonRepository.findAll();

            if (fisicas.isEmpty() && juridicas.isEmpty()) return ResponseEntity.status(404).body("[]");

            // generamos el Objeto con el formato deseado
            int count = fisicas.size() + juridicas.size();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCount(count);
            customerDTO.setPersonasFisicas(fisicas);
            customerDTO.setPersonasJuridicas(juridicas);

            return ResponseEntity.ok(customerDTO);

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @GetMapping("/{rut}")
    public ResponseEntity findByRut(@PathVariable long rut) {
        try {
            /* Se que no es la mejor forma de hacerlo, pero debido a que no hay mas campos en común que el RUT,
            no cree una tabla general donde de ella a través de FK puedan estructurarse las dif. personas.
            Estuve dandole vueltas al asunto y no pude crear una solución lo mas generica posible que me convenza */
            if (rut <= 0) return ResponseEntity.status(400).body("RUT must be a positive number");

            PhysicalPerson physicalPerson = physicalPersonRepository.findByRut(rut);
            if (physicalPerson != null) return ResponseEntity.ok(physicalPerson);

            LegalPerson legalPerson = legalPersonRepository.findByRut(rut);
            if (legalPerson != null) return ResponseEntity.ok(legalPerson);

            return ResponseEntity.status(404).body("{}");

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @PutMapping("/fisica/{rut}")
    public ResponseEntity updateByRut(@RequestBody PhysicalPerson customer, @PathVariable long rut) {
        try {
            if (rut <= 0) return ResponseEntity.status(400).body("RUT must be a positive number");
            PhysicalPerson physicalPerson = physicalPersonRepository.findByRut(rut);
            // Borramos el cliente encontrado y cargamos el nuevo
            if (physicalPerson == null) return ResponseEntity.status(404).body("Customer not found with this rut: " + rut);
            physicalPersonRepository.delete(physicalPerson);
            physicalPersonRepository.save(customer);

            return ResponseEntity.status(200).body("Customer modified");

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @PutMapping("/juridica/{rut}")
    public ResponseEntity updateByRut(@RequestBody LegalPerson customer, @PathVariable long rut) {
        try {
            if (rut <= 0) return ResponseEntity.status(400).body("RUT must be a positive number");
            LegalPerson legalPerson = legalPersonRepository.findByRut(rut);
            // Borramos el cliente encontrado y cargamos el nuevo
            if (legalPerson == null) return ResponseEntity.status(404).body("Customer not found with this rut: " + rut);
            legalPersonRepository.delete(legalPerson);
            legalPersonRepository.save(customer);

            return ResponseEntity.status(200).body("Customer modified");

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{rut}")
    public ResponseEntity deleteByRut(@PathVariable long rut) {
        try {
            if (rut <= 0) return ResponseEntity.status(400).body("RUT must be a positive number");

            PhysicalPerson physicalPerson = physicalPersonRepository.findByRut(rut);
            if (physicalPerson != null) {
                physicalPersonRepository.delete(physicalPerson);
                return ResponseEntity.ok("Physical Person deleted from db");
            }

            LegalPerson legalPerson = legalPersonRepository.findByRut(rut);
            if (legalPerson != null) {
                legalPersonRepository.delete(legalPerson);
                return ResponseEntity.ok("Legal Person deleted from db");
            }

            return ResponseEntity.status(404).body("Customer not found");

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error processing request: " + ex.getMessage());
        }
    }



}
