package challenge;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String>{

	List<Recipe> findByIngredientsInOrderByTitleAsc(String ingredient);

	List<Recipe> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByTitleAsc(String title, String description);

}
