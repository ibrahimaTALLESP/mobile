package sn.ucad.esp.dgi.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;

	public Long getId() {
		return id;
	}
}