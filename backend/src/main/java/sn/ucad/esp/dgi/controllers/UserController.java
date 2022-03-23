package sn.ucad.esp.dgi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sn.ucad.esp.dgi.beans.User;
import sn.ucad.esp.dgi.repositories.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	Collection<User> Users() {
		return userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/user")
	ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
		log.info("Request to create a user: {}", user);
		User result = userRepository.save(user);
		return ResponseEntity.created(new URI("/api/user/" + result.getId())).body(result);
	}

	@PutMapping("/user/{id}")
	ResponseEntity<User> updateUser(@RequestBody User user) {
		log.info("Request to update a user: {}", user);
		User result = userRepository.save(user);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		log.info("Request to delete a user: {}", id);
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}