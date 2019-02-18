package challenge;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeCommentRepository commentRepository;

	@Autowired
	private RecipeRepository repository;

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		RecipeComment commentSaved = commentRepository.save(comment);
		Recipe recipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found ::" + id));
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().add(commentSaved);
		}else {
			recipe.setComments(Arrays.asList(commentSaved));
		}
		repository.save(recipe);
		return commentSaved;
	}

	@Override
	public void delete(String id) {
		Recipe recipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found :: " + id));
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().forEach(c -> commentRepository.delete(c));
		}
		repository.delete(recipe);
	}

	@Override
	public void deleteComment(String id, String commentId) {  //TODO ARRUMAR, NÃO TA FUNCIONANDO
		Recipe recipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found :: " + id));
		List<RecipeComment> listComment = recipe.getComments();
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().forEach(c -> {
				if(c.get_id().equals(commentId)) {
					listComment.remove(c);
				}
			});
		}
		recipe.setComments(listComment);
		repository.save(recipe);
		commentRepository.deleteById(commentId);
	}

	@Override
	public Recipe get(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found :: " + id));
	}

	@Override
	public void like(String id, String userId) {
		Recipe recipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found ::" + id));
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
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().forEach(c -> commentRepository.save(c));
		}
		return repository.save(recipe);
	}

	@Override
	public List<Recipe> search(String search) {//TODO IMPLEMENTAR
		return null;
	}

	@Override
	public void unlike(String id, String userId) {
		Recipe recipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found ::" + id));
		if(recipe.getLikes() != null && !recipe.getLikes().isEmpty() && recipe.getLikes().contains(userId)) {
			recipe.getLikes().remove(userId);
		}
		repository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Recipe recipeSaved = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found ::" + id));
		recipeSaved.setTitle(recipe.getTitle());
		recipeSaved.setDescription(recipe.getDescription());
		recipeSaved.setIngredients(recipe.getIngredients());

		repository.save(recipeSaved);
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		RecipeComment commentSaved = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("RecipeComment not found ::" + commentId));
		Recipe recipe = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found ::" + id));
		if(recipe.getComments() != null && !recipe.getComments().isEmpty()) {
			recipe.getComments().forEach(c -> {
				if(c.get_id().equals(commentSaved.get_id())) {
					c.setComment(comment.getComment());
				}
			});
		}
		commentSaved.setComment(comment.getComment());
		commentRepository.save(commentSaved);
		repository.save(recipe);
	}

}
