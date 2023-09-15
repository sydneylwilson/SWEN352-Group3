package coffeemaker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffeemaker.exceptions.RecipeException;

public class RecipeBookTest {
	RecipeBook rb;
	Recipe r;

	@Before
	public void setUp() throws Exception {
		rb = new RecipeBook();
		r = new Recipe();
	}

	@After
	public void tearDown() throws Exception {
		rb = null;
		r = null;
	}

	@Test
	public void testRecipeBook() {
		// test the constructor
		RecipeBook rb = new RecipeBook();
		assertEquals(4, rb.getRecipes().length);
	}

	@Test
	public void testGetRecipes() {
		// test the get Recipes method
		RecipeBook rb = new RecipeBook();
		Recipe[] recipes = rb.getRecipes();
		assertEquals(4, recipes.length);
	}

	@Test
	public void testAddRecipe() {
		// test the add recipe method
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		try {
			r.setAmtChocolate("0");
			r.setAmtCoffee("3");
			r.setAmtMilk("1");
			r.setAmtSugar("1");
			r.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r));
	}

	@Test 
	public void testAddRecipeFail() {
		// test the add recipe method but the same recipe is already there
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		try {
			r.setAmtChocolate("0");
			r.setAmtCoffee("3");
			r.setAmtMilk("1");
			r.setAmtSugar("1");
			r.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		rb.addRecipe(r);
		Recipe r2 = r;
		assertFalse(rb.addRecipe(r2));
		
	}

	@Test
	public void testDeleteRecipe() {
		// test the delete recipe method
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		rb.deleteRecipe(0);
		assertEquals(null, rb.getRecipes()[0]);
	}

	@Test
	public void testDeleteRecipeFail() {
		// test the delete recipe method but the recipe is null
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		rb.deleteRecipe(0);
		assertEquals(null, rb.getRecipes()[0]);
		rb.deleteRecipe(0);
		assertEquals(null, rb.getRecipes()[0]);
	}

	@Test
	public void testEditRecipe() {
		// test the edit Recipe method
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		Recipe r2 = new Recipe();
		r2.setName("Coffee2");
		rb.editRecipe(0, r2);
		assertEquals("Coffee2", rb.getRecipes()[0].getName());
	}

	@Test
	public void testEditRecipeFail() {
		// test the edit Recipe method but the recipe is null
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		assertEquals(null, rb.deleteRecipe(0));
	}

	@Test
	public void testEditRecipeFail2() {
		// test the edit Recipe method but the recipe is null
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		Recipe r2 = new Recipe();
		r2.setName("Coffee2");
		rb.editRecipe(0, r2);
		assertEquals("Coffee2", rb.getRecipes()[0].getName());
		rb.editRecipe(0, r2);
		assertEquals("Coffee2", rb.getRecipes()[0].getName());
	}

	@Test
	public void testEditRecipe2() {
		// test the edit Recipe method again
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		rb.addRecipe(r);
		Recipe r2 = new Recipe();
		r2.setName("Coffee2");
		rb.editRecipe(0, r2);
		assertEquals("Coffee2", rb.getRecipes()[0].getName());
		Recipe r3 = new Recipe();
		r3.setName("Coffee3");
		rb.editRecipe(0, r3);
		assertEquals("Coffee3", rb.getRecipes()[0].getName());
	}

	@Test
	public void testAddRecipe2() {
		// test the add recipe method again
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		try {
			r.setAmtChocolate("0");
			r.setAmtCoffee("3");
			r.setAmtMilk("1");
			r.setAmtSugar("1");
			r.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r));
		Recipe r2 = new Recipe();
		r2.setName("Coffee2");
		try {
			r2.setAmtChocolate("0");
			r2.setAmtCoffee("3");
			r2.setAmtMilk("1");
			r2.setAmtSugar("1");
			r2.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r2));
	}

	@Test
	public void testAddRecipe3() {
		// test the add recipe method again
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		try {
			r.setAmtChocolate("0");
			r.setAmtCoffee("3");
			r.setAmtMilk("1");
			r.setAmtSugar("1");
			r.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r));
		Recipe r2 = new Recipe();
		r2.setName("Coffee2");
		try {
			r2.setAmtChocolate("0");
			r2.setAmtCoffee("3");
			r2.setAmtMilk("1");
			r2.setAmtSugar("1");
			r2.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r2));
		Recipe r3 = new Recipe();
		r3.setName("Coffee3");
		try {
			r3.setAmtChocolate("0");
			r3.setAmtCoffee("3");
			r3.setAmtMilk("1");
			r3.setAmtSugar("1");
			r3.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r3));
	}

	@Test
	public void testAddRecipeFail2() {
		// test the add recipe method but the same recipe is already there and it fails again
		RecipeBook rb = new RecipeBook();
		Recipe r = new Recipe();
		r.setName("Coffee");
		try {
			r.setAmtChocolate("0");
			r.setAmtCoffee("3");
			r.setAmtMilk("1");
			r.setAmtSugar("1");
			r.setPrice("50");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(rb.addRecipe(r));
		Recipe r2 = r;
		assertFalse(rb.addRecipe(r2));
	}
}
