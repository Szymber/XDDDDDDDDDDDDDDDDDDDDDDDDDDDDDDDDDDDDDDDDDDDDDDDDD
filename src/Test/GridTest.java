package Test;

import org.junit.Assert;
import org.junit.Test;

import Model.*;
import Model.Board.*;


public class GridTest {

	@Test
	public void GridShouldNotBeNull() {
		Grid grid = new Grid(new Vector2(4,4));
		Assert.assertNotNull(grid);
	}
	
	@Test
	public void GridShouldNotBeNull2() {
		Grid grid = new Grid(new Vector2(4,4), new Vector2[] {new Vector2(1,1)});
		Assert.assertNotNull(grid);
	}
	
	@Test
	public void GridShouldHaveCorrectSize() {
		Grid grid = new Grid(new Vector2(1,4));
		Assert.assertEquals(1, grid.getSize().getX());
		Assert.assertEquals(4, grid.getSize().getY());
	}
	
	@Test
	public void ReturnedFieldShouldNotBeNull() {
		Grid grid = new Grid(new Vector2(4,4), new Vector2[] {new Vector2(1,1)});
		Assert.assertNotNull(grid.getField(new Vector2(1,1)));
	}
	
	@Test
	public void ReturnedFieldShouldBeNull() {
		Grid grid = new Grid(new Vector2(4,4), new Vector2[] {new Vector2(1,1)});
		Assert.assertNull(grid.getField(new Vector2(10,1)));
	}
	
	@Test
	public void GridShouldHavaUnavailableField() {
		Grid grid = new Grid(new Vector2(4,4), new Vector2[] {new Vector2(1,1)});
		Assert.assertFalse(grid.getField(new Vector2(1,1)).isAvailable());
	}
	

}
