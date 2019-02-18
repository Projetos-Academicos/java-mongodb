package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository repository;

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {

		Recipe recipe = repository.findById(id).get();
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().add(comment);
		}else {
			recipe.setComments(Arrays.asList(comment));
		}
		repository.save(recipe);
		return comment;
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Recipe recipe = repository.findById(id).get();
		List<RecipeComment> listComment = new ArrayList<RecipeComment>();
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().forEach(c -> {
				if(!c.getId().equals(commentId)) {
					listComment.add(c);
				}
			});
		}
		recipe.setComments(listComment);
		repository.save(recipe);
	}

	@Override
	public Recipe get(String id) {
		Optional<Recipe> recipe = repository.findById(id);
		if(recipe.isPresent()) {
			return recipe.get();
		}
		return null;
	}

	@Override
	public void like(String id, String userId) {
		Recipe recipe = repository.findById(id).get();
		if(recipe.getLikes() != null && !recipe.getLikes().isEmpty()) {
			recipe.getLikes().add(userId);
		}else {
			recipe.setLikes(Arrays.asList(userId));
		}

		repository.save(recipe);
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return repository.findByIngredientsInOrderByTitleAsc(ingredient);
	}

	@Override
	public Recipe save(Recipe recipe) {
		return repository.save(recipe);
	}

	@Override
	public List<Recipe> search(String search) {
		return repository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByTitleAsc(search, search);
	}

	@Override
	public void unlike(String id, String userId) {
		Recipe recipe = repository.findById(id).get();
		if(recipe.getLikes() != null && !recipe.getLikes().isEmpty() && recipe.getLikes().contains(userId)) {
			recipe.getLikes().remove(userId);
		}
		repository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Recipe recipeSaved = repository.findById(id).get();
		recipeSaved.setTitle(recipe.getTitle());
		recipeSaved.setDescription(recipe.getDescription());
		recipeSaved.setIngredients(recipe.getIngredients());

		repository.save(recipeSaved);
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Recipe recipe = repository.findById(id).get();
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().forEach(c -> {
				if(c.getId().equals(commentId)) {
					c.setComment(comment.getComment());
				}
			});
		}
		repository.save(recipe);
	}

}
