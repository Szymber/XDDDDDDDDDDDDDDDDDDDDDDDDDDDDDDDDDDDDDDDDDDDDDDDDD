package Test;

import Model.Chessman.King;
import Model.MyColor;
import Model.Chessman.Chessman;
import Model.Vector2;
import org.junit.Assert;
import org.junit.Test;


public class KingTest {

	@Test 
	public void KingShouldNotBeNull() {
		Chessman king = new King(new Vector2(0, 0), MyColor.White);
		Assert.assertNotNull(king);
	}
	
	@Test
	public void KingShouldMoveOneFieldAround() {
		Chessman king = new King(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(king.isValidMove(new Vector2(1,0)));
		Assert.assertTrue(king.isValidMove(new Vector2(0,1)));
		Assert.assertTrue(king.isValidMove(new Vector2(0,-1)));
		Assert.assertTrue(king.isValidMove(new Vector2(-1,0)));
		Assert.assertTrue(king.isValidMove(new Vector2(-1,-1)));
	}
	@Test
	public void KingShouldNotMoveSeveralFields() {
		Chessman king = new King(new Vector2(0, 0), MyColor.White);
		Assert.assertFalse(king.isValidMove(new Vector2(2,0)));
		Assert.assertFalse(king.isValidMove(new Vector2(-2,0)));
		Assert.assertFalse(king.isValidMove(new Vector2(0,-2)));
		Assert.assertFalse(king.isValidMove(new Vector2(1,-2)));
	}
	
	@Test
	public void KingShouldBeEqual() {
		Chessman king = new King(new Vector2(0, 0), MyColor.White);
		Chessman king2 = new King(new Vector2(0, 0), MyColor.White);
		Assert.assertEquals(king, king2);
	}	
	
	@Test
	public void KingShouldNotBeEqual() {
		Chessman king = new King(new Vector2(0, 0), MyColor.White);
		Chessman king2 = new King(new Vector2(0, 0), MyColor.Black);
		Assert.assertNotEquals(king, king2);
	}
	
}
