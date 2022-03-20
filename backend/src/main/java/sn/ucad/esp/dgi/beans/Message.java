package sn.ucad.esp.dgi.beans;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Data
public class Message {
	
	public enum Status {
	    Online,
	    Offline,
	    Writing
	}
	
	@Id
	@GeneratedValue
    private Long id;
	
	
    private String initiateur;
    private String récepteur;
    private String message;
    private String date;
    private Status status;
}
