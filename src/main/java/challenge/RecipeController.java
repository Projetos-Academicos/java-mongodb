package challenge;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping("/{id}/comment")
	public RecipeComment addComment(@PathVariable String id, @Valid @RequestBody RecipeComment comment) { //TODO OK
		return service.addComment(id, comment);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) { //TODO OK
		service.delete(id);
	}

	@DeleteMapping("/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId) { //TODO OK
		service.deleteComment(id, commentId);
	}

	@GetMapping("/{id}")
	public Recipe get(@PathVariable String id) { //TODO OK
		return service.get(id);
	}

	@PostMapping("/{id}/like/{userId}")
	public void like(@PathVariable String id, @PathVariable String userId) {// TODO OK
		service.like(id, userId);
	}

	@GetMapping("/ingredient")
	public List<Recipe> listByIngredient(@RequestParam String ingredient) { //TODO OK
		return service.listByIngredient(ingredient);
	}

	@PostMapping
	public Recipe save(@Valid @RequestBody Recipe recipe) { //TODO OK
		return service.save(recipe);
	}

	@GetMapping("/search")
	public List<Recipe> search(@RequestParam String search) { //TODO OK
		return service.search(search);
	}

	@DeleteMapping("/{id}/like/{userId}")
	public void unlike(@PathVariable String id, @PathVariable String userId) {//TODO OK
		service.unlike(id, userId);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable String id, @Valid @RequestBody Recipe recipe) { //TODO OK
		service.update(id, recipe);
	}

	@PutMapping("/{id}/comment/{commentId}")
	public void updateComment(@PathVariable String id, @PathVariable String commentId, @Valid @RequestBody RecipeComment comment) {//TODO OK
		service.updateComment(id, commentId, comment);
	}

}
