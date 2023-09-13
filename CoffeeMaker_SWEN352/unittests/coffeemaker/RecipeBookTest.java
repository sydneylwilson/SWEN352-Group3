package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeBookTest {

	@Test
	void testRecipeBook() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecipes() {
		fail("Not yet implemented");
	}

	@Test
	void testAddRecipe() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteRecipe() {
		// test the deletionRecipe method
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		rb.deleteRecipe(0);
		assertEquals(null, rb.getRecipes()[0]);
	}

	@Test
	void testEditRecipe() {
		fail("Not yet implemented");
	}

}
