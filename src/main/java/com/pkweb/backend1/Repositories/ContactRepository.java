package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByIdUserId(Integer userId);
}
