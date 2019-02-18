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
	private String _id;
	@NotNull
	private String comment;

	public String get_id() {
		return _id;
	}
	public String getComment() {
		return comment;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
