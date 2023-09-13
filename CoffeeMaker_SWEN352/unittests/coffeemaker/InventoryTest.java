package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import coffeemaker.exceptions.RecipeException;

class InventoryTest {

	@Test
	void testInventory() {
		fail("Not yet implemented");
	}

	@Test
	void testGetChocolate() {
		fail("Not yet implemented");
	}

	@Test
	void testSetChocolate() {
		fail("Not yet implemented");
	}

	@Test
	void testAddChocolate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCoffee() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCoffee() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCoffee() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMilk() {
		fail("Not yet implemented");
	}

	@Test
	void testSetMilk() {
		fail("Not yet implemented");
	}

	@Test
	void testAddMilk() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSugar() {
		fail("Not yet implemented");
	}

	@Test
	void testSetSugar() {
		fail("Not yet implemented");
	}

	@Test
	void testAddSugar() {
		// test the add sugar method
		Inventory inv = new Inventory();
		try {
			inv.addSugar("5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(20, inv.getSugar());
	}

	@Test
	void testEnoughIngredients() {
		fail("Not yet implemented");
	}

	@Test
	void testUseIngredients() {
		// test the useIngredients method
		Inventory inv = new Inventory();
		Recipe r = new Recipe();
		try {
			r.setAmtChocolate("5");
			r.setAmtCoffee("5");
			r.setAmtMilk("5");
			r.setAmtSugar("5");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		inv.useIngredients(r);
		assertEquals(10, inv.getChocolate());
		assertEquals(10, inv.getCoffee());
		assertEquals(10, inv.getMilk());
		assertEquals(10, inv.getSugar());
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
