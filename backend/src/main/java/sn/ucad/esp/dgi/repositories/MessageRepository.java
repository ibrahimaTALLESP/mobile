package sn.ucad.esp.dgi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.ucad.esp.dgi.beans.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}