package challenge;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

/**
 * Classe para mapear o comentário da receita no MongoDB
 *
 */

@EntityScan
public class RecipeComment {

	@Id
	private String id;
	@NotNull
	private String comment;

	public String getComment() {
		return comment;
	}
	public String getId() {
		return this.id;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(String id) {
		this.id = id;
	}

}
