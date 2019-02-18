package challenge;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

/**
 * Classe para mapear a receita no MongoDB
 *
 */

@EntityScan
public class Recipe {

	@Id
	private String _id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	private List<String> likes;
	@NotNull
	private List<String> ingredients;
	private List<RecipeComment> comments;

	public Recipe() {

	}

	public Recipe(String _id, @NotNull String title, @NotNull String description, List<String> likes,
			@NotNull List<String> ingredients, List<RecipeComment> comments) {
		super();
		this._id = _id;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.ingredients = ingredients;
		this.comments = comments;
	}


	public String get_id() {
		return _id;
	}
	public List<RecipeComment> getComments() {
		return comments;
	}
	public String getDescription() {
		return description;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public List<String> getLikes() {
		return likes;
	}
	public String getTitle() {
		return title;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void setComments(List<RecipeComment> comments) {
		this.comments = comments;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
