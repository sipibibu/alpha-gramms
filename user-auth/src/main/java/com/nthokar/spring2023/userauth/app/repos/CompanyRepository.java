package com.nthokar.spring2023.userauth.app.repos;

import com.nthokar.spring2023.userauth.app.entities.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByTitle(String title);
}
