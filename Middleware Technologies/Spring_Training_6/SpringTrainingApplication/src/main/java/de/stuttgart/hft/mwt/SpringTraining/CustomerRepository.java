package de.stuttgart.hft.mwt.SpringTraining;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    List<Customer> findById(long id);

}