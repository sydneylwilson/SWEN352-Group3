package coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import coffeemaker.exceptions.RecipeException;

// @author Sydney Wilson
public class RecipeTest {
	
    public Recipe recipe;
    @Before
    public void setUp() {
        recipe = new Recipe();
	}
    
    @Test
    public void testGetNameDefault() {
        String recipeNameDefault = recipe.getName();
        assertEquals("", recipeNameDefault);
    }

    @Test
    public void testGetPrice() {
        int price = recipe.getPrice();
        assertEquals(0, price);
    }
    
    @Test
    public void testGetCoffeeAmt() {
        int amtCoffee = recipe.getAmtCoffee();
        assertEquals(0, amtCoffee);
    }

    @Test
    public void testGetMilkAmt() {
        int amtMilk = recipe.getAmtMilk();
        assertEquals(0, amtMilk);
    }

    @Test
    public void testGetSugarAmt() {
        int amtSugar = recipe.getAmtSugar();
        assertEquals(0, amtSugar);
    }

    @Test
    public void testGetChocolateAmt() {
        int amtChocolate = recipe.getAmtChocolate();
        assertEquals(0, amtChocolate);
    }

    @Test
    public void testSetName() {
        recipe.setName("Mocha");
        String name = recipe.getName();
        assertEquals("Mocha", name);
    }
    
    @Test
    public void testSetPrice() {
        try {
            recipe.setPrice("5");
        } catch (RecipeException e) {
            e.printStackTrace();
        }
        int price = recipe.getPrice();
        assertEquals(5, price);
    }

    @Test
    public void testSetChocolateAmt() {
        try {
            recipe.setAmtChocolate("4");
        } catch (RecipeException e) {
            e.printStackTrace();
        }
        int amtChocolate = recipe.getAmtChocolate();
        assertEquals(4, amtChocolate);
    }

    @Test
    public void testSetSugarAmt() {
        try {
            recipe.setAmtSugar("2");
        } catch (RecipeException e) {
            e.printStackTrace();
        }
        int amtSugar = recipe.getAmtSugar();
        assertEquals(2, amtSugar);
    }

    @Test
    public void testSetCoffeeAmt() {
        try {
            recipe.setAmtCoffee("1");
        } catch (RecipeException e) {
            e.printStackTrace();
        }
        int amtCoffee = recipe.getAmtCoffee();
        assertEquals(1, amtCoffee);
    } 

    @Test
    public void testSetMilkAmt() {
        try {
            recipe.setAmtMilk("2");
        } catch (RecipeException e) {
            e.printStackTrace();
        }
        int amtMilk = recipe.getAmtMilk();
        assertEquals(2, amtMilk);
    }

    @Test
    public void testHashcodeDefaultRecipe() {
        int hashcode = recipe.hashCode();
        assertEquals(31, hashcode);
    }

    @Test
    public void testHashcodeOfTwoObjectsUnequal() {
    	int recipeHashcode = recipe.hashCode();
    	Recipe recipe2 = new Recipe();
    	recipe2.setName("Not Recipe 1");
    	int recipe2Hashcode = recipe2.hashCode();
    	assertNotEquals(recipeHashcode, recipe2Hashcode);
    }
    
	@SuppressWarnings("null")
	@Test
    public void testHashcodeNullObject() {
    	assertThrows(NullPointerException.class, () -> {
    		Recipe nullRecipe = null;
    		nullRecipe.hashCode();
    	});
    }
	
	@Test
	public void testHashcodeRecipe() {
		recipe.setName("Recipe 1");
		try {
			recipe.setAmtCoffee("1");
			recipe.setAmtMilk("1");
			recipe.setAmtSugar("1");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		int recipe1Hashcode = recipe.hashCode();
		assertEquals(-740191650, recipe1Hashcode);
	}
    
	@Test
	public void testHashcodeWithPartRecipeInvalid() {
		recipe.setName("Secret Recipe");
		try {
			recipe.setAmtCoffee("-5");
			recipe.setAmtMilk("1");
			recipe.setAmtSugar("1");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		int recipe1Hashcode = recipe.hashCode();
		assertEquals(2050073885, recipe1Hashcode);
	}
	
	@Test
	public void testHashcodeWithRecipeInvalidDefaultName() {
		recipe.setName("");
		try {
			recipe.setAmtCoffee("-5");
			recipe.setAmtMilk("NO");
			recipe.setAmtSugar("-100");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		int recipe1Hashcode = recipe.hashCode();
		assertEquals(31, recipe1Hashcode);
	}
	
	@Test
	public void testHashcodeNumericName() {
		recipe.setName("123456");
		try {
			recipe.setAmtCoffee("1");
			recipe.setAmtMilk("1");
			recipe.setAmtSugar("1");
		} catch (RecipeException e) {
			e.printStackTrace();
		}
		int recipe1Hashcode = recipe.hashCode();
		assertEquals(1450575490, recipe1Hashcode);
	}
	
    @Test
    public void testEqualsWhenEqual() {
        Recipe recipe2 = new Recipe();
        assertTrue(recipe.equals(recipe2));
    }
    
    @Test
    public void testEqualsWhenNotEqual() {
    	Recipe recipe2 = new Recipe();
    	recipe2.setName("newName");
    	assertFalse(recipe.equals(recipe2));
    }
    
    @Test
    public void testEqualsToNullObject() {
    	Recipe nullRecipe = null;
    	assertFalse(recipe.equals(nullRecipe));
    }

    @SuppressWarnings("unlikely-arg-type")
	@Test
    public void testEqualsToWrongObject() {
    	RecipeBook recipeBook = new RecipeBook();
    	assertFalse(recipe.equals(recipeBook));
    }
    
    @Test
    public void testEqualsReflexivity() {
    	assertTrue(recipe.equals(recipe));
    }
    
    @Test
    public void testEqualsSymmetry() {
    	Recipe recipe2 = new Recipe();
    	assertTrue(recipe.equals(recipe2) && recipe2.equals(recipe));
    }
    
    @Test
    public void testEqualsTransitivity() {
    	Recipe recipe2 = new Recipe();
    	Recipe recipe3 = new Recipe();
    	if (recipe.equals(recipe2) && recipe2.equals(recipe3)) {
    		assertTrue(recipe3.equals(recipe));
    	}
    }
    
    @Test
    public void testSetChocolateAmtNegative() {
        boolean thrown = false;
        try {
            recipe.setAmtChocolate("-1");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testSetChocolateInvalidInput() {
        boolean thrown = false;
        try {
            recipe.setAmtChocolate("NO");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testSetCoffeeAmtNegative() {
        boolean thrown = false;
        try {
            recipe.setAmtCoffee("-1");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }

    @Test
    public void testSetCoffeeInvalidAmt() {
        boolean thrown = false;
        try {
            recipe.setAmtCoffee("NO");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testSetMilkAmtNegative() {
        boolean thrown = false;
        try {
            recipe.setAmtMilk("-1");
        }
        catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    } 
    
    @Test
    public void testSetMilkInvalidInput() {
        boolean thrown = false;
        try {
            recipe.setAmtMilk("NO");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }

    @Test
    public void testSetPriceNegative() {
        boolean thrown = false;
        try {
            recipe.setPrice("-1");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testSetPriceInvalidInput() {
        boolean thrown = false;
        try {
            recipe.setPrice("aksdjbckas");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testSetSugarNegative() {
        boolean thrown = false;
        try {
            recipe.setAmtSugar("-1");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testSetSugarInvalidInput() { 
        boolean thrown = false;
        try {
            recipe.setAmtSugar("NO");
        } catch (RecipeException e) {
            thrown = true;
            e.printStackTrace();
        }
        assertTrue(thrown);
    }
    
    @Test
    public void testRecipeToStringCustomName() {
    	recipe.setName("Coffee1234!");
    	String recipeString = recipe.toString();
    	assertEquals("Coffee1234!", recipeString);
    }

    @Test
    public void testRecipeToStringDefaultName() {
    	String recipeString = recipe.toString();
    	assertEquals("", recipeString);
    }
}
