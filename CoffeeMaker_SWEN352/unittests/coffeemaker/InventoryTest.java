package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import coffeemaker.exceptions.InventoryException;
import coffeemaker.exceptions.RecipeException;

class InventoryTest {
	
//	public ExpectedException exception = ExpectedException.none();

	@Test
	void testInventory() {
		Inventory inv = new Inventory();
		assertNotNull(inv);
	}

	@Test
	void testGetChocolate() {
		Inventory i = new Inventory();
		assertEquals(15, i.getChocolate());
	}

	@Test
	void testSetChocolate() {
		Inventory i = new Inventory();
		i.setChocolate(20);
		assertEquals(20, i.getChocolate());
	}

	@Test
	void testAddChocolate() throws InventoryException {
		Inventory i = new Inventory();
		i.addChocolate("5");
		assertEquals(20, i.getChocolate());
	}
	
	@Test
	void testAddChocolateException(){
		String error = "";
		Inventory i = new Inventory();
		try {
			i.addChocolate("-1");
		} catch (InventoryException e) {
			error = e.getMessage();
		}
		assertEquals("Units of chocolate must be a positive integer", error);
	}

	@Test
	void testGetCoffee() {
		Inventory i = new Inventory();
		assertEquals(15, i.getCoffee());
	}

	@Test
	void testSetCoffee() {
		Inventory i = new Inventory();
		i.setCoffee(20);
		assertEquals(20, i.getCoffee());
	}

	@Test
	void testAddCoffee() throws InventoryException {
		Inventory i = new Inventory();
		i.addCoffee("5");
		assertEquals(20, i.getCoffee());
	}

	@Test
	void testGetMilk() {
		Inventory i = new Inventory();
		assertEquals(15, i.getMilk());
	}

	@Test
	void testSetMilk() {
		Inventory i = new Inventory();
		i.setMilk(20);
		assertEquals(20, i.getMilk());
	}

	@Test
	void testAddMilk() throws InventoryException {
		Inventory i = new Inventory();
		i.addMilk("5");
		assertEquals(20, i.getMilk());
	}

	@Test
	void testGetSugar() {
		Inventory i = new Inventory();
		assertEquals(15, i.getSugar());
	}

	@Test
	void testSetSugar() {
		Inventory i = new Inventory();
		i.setSugar(20);
		assertEquals(20, i.getSugar());
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
		Inventory inv = new Inventory();
		Recipe r = new Recipe();
		try {
			r.setAmtChocolate("10");
			r.setAmtCoffee("10");
			r.setAmtMilk("10");
			r.setAmtSugar("10");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertTrue(inv.enoughIngredients(r));
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
	void testUseIngredientsFails() {
		// test the useIngredients method
		Inventory inv = new Inventory();
		Recipe r = new Recipe();
		try {
			r.setAmtChocolate("25");
			r.setAmtCoffee("25");
			r.setAmtMilk("25");
			r.setAmtSugar("25");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		assertFalse(inv.useIngredients(r));
	}
	
	@Test
	void testNotEnoughIngredients() {
		Inventory inv = new Inventory();
		Recipe r = new Recipe();
		try {
			r.setAmtChocolate("20");
			r.setAmtCoffee("20");
			r.setAmtMilk("20");
			r.setAmtSugar("20");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
//		boolean x = inv.enoughIngredients(r);
		assertFalse(inv.enoughIngredients(r));
	}

	@Test
	void testToString() {
		Inventory i = new Inventory();
		
		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
    	buf.append("15");
    	buf.append("\n");
    	buf.append("Milk: ");
    	buf.append("15");
    	buf.append("\n");
    	buf.append("Sugar: ");
    	buf.append("15");
    	buf.append("\n");
    	buf.append("Chocolate: ");
    	buf.append("15");
    	buf.append("\n");
    	
		assertEquals(i.toString(), buf);
	}

}
