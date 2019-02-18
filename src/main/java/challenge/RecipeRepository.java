package challenge;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String>{

	List<Recipe> findByIngredientsInOrderByTitleAsc(String ingredient);

	@Query("SELECT r FROM Recipe r WHERE upper(r.title) LIKE %?1% or upper(r.description) LIKE %?2% ORDER BY r.title ASC")
	List<Recipe> serach(String search, String serach2);

}
