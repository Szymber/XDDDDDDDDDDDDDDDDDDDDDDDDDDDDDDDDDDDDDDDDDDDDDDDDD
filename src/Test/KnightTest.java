package Test;

import Model.Chessman.Knight;
import Model.MyColor;
import Model.Chessman.Chessman;
import Model.Vector2;
import org.junit.Assert;
import org.junit.Test;


public class KnightTest {

	@Test 
	public void KnightShouldNotBeNull() {
		Chessman knight = new Knight(new Vector2(0, 0), MyColor.White);
		Assert.assertNotNull(knight);
	}
	
	@Test
	public void KnightShouldNotMoveForwardBackLeftRight() {
		Chessman knight = new Knight(new Vector2(0, 0), MyColor.White);
		Assert.assertFalse(knight.isValidMove(new Vector2(0,-1)));
		Assert.assertFalse(knight.isValidMove(new Vector2(0,1)));
		Assert.assertFalse(knight.isValidMove(new Vector2(1, 0)));
		Assert.assertFalse(knight.isValidMove(new Vector2(-1, 0)));
	}
	
	@Test
	public void KnightShouldMoveItsMoves() {
		Chessman knight = new Knight(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(knight.isValidMove(new Vector2(1,2)));
		Assert.assertTrue(knight.isValidMove(new Vector2(-1,2)));
		Assert.assertTrue(knight.isValidMove(new Vector2(1,-2)));
		Assert.assertTrue(knight.isValidMove(new Vector2(-1,-2)));
		Assert.assertTrue(knight.isValidMove(new Vector2(2,1)));
		Assert.assertTrue(knight.isValidMove(new Vector2(-2,1)));
		Assert.assertTrue(knight.isValidMove(new Vector2(-2,-1)));
		Assert.assertTrue(knight.isValidMove(new Vector2(2,-1)));
		
	}	
	
	@Test
	public void KnightShouldBeEqual() {
		Chessman knight = new Knight(new Vector2(0, 0), MyColor.White);
		Chessman knight2 = new Knight(new Vector2(0, 0), MyColor.White);
		Assert.assertEquals(knight, knight2);
	}	
	
	@Test
	public void KnightShouldNotBeEqual() {
		Chessman knight = new Knight(new Vector2(0, 0), MyColor.White);
		Chessman knight2 = new Knight(new Vector2(0, 0), MyColor.Black);
		Assert.assertNotEquals(knight, knight2);
	}
	
}
