package ul.info.digitalwallet.common.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(	name = "message")
@Data
public class Message{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
	@Size(max = 20)
	private String sender;

    @NotBlank
    private String messageBody;

    @ManyToOne(targetEntity = User.class)
    private User user;
}