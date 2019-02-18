package challenge;

import java.util.List;

public interface RecipeService {

	RecipeComment addComment(String id, RecipeComment comment);

	void delete(String id);

	void deleteComment(String id, String commentId);

	Recipe get(String id);

	void like(String id, String userId);

	List<Recipe> listByIngredient(String ingredient);

	Recipe save(Recipe recipe);

	List<Recipe> search(String search);

	void unlike(String id, String userId);

	void update(String id, Recipe recipe);

	void updateComment(String id, String commentId, RecipeComment comment);

}
