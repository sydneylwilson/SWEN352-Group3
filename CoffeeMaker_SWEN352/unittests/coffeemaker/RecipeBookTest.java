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
		// test the editRecipe method
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		Recipe r2 = new Recipe();
		r2.setName("Coffee2");
		rb.editRecipe(0, r2);
		assertEquals("Coffee2", rb.getRecipes()[0].getName());
	}

}
