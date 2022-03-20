package sn.ucad.esp.dgi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.ucad.esp.dgi.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNom(String nom);
}