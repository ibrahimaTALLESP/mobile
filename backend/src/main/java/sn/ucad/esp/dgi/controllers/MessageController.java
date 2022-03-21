package sn.ucad.esp.dgi.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sn.ucad.esp.dgi.beans.Message;
import sn.ucad.esp.dgi.repositories.MessageRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class MessageController {

	private final Logger log = LoggerFactory.getLogger(MessageController.class);
	private MessageRepository messageRepository;

	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@GetMapping("/messages")
	Collection<Message> Messages() {
		return messageRepository.findAll();
	}

	@GetMapping("/message/{id}")
	ResponseEntity<?> getMessage(@PathVariable Long id) {
		Optional<Message> group = messageRepository.findById(id);
		return group.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/message")
	ResponseEntity<Message> createMessage(@RequestBody Message message) throws URISyntaxException {
		log.info("Request to create a message: {}", message);
		Message result = messageRepository.save(message);
		return ResponseEntity.created(new URI("/api/message/" + result.getId())).body(result);
	}

	@PutMapping("/message/{id}")
	ResponseEntity<Message> updateMessage(@RequestBody Message message) {
		log.info("Request to update a message: {}", message);
		Message result = messageRepository.save(message);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/group/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
		log.info("Request to delete a message: {}", id);
		messageRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}