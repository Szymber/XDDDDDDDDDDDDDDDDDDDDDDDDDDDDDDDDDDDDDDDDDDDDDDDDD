package Test;

import Model.Chessman.Rook;
import Model.MyColor;
import Model.Chessman.Chessman;
import Model.Vector2;
import org.junit.Assert;
import org.junit.Test;


public class RookTest {

	@Test 
	public void RookShouldNotBeNull() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertNotNull(rook);
	}
	
	@Test
	public void RookShouldMoveOneFieldLeftRight() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(rook.isValidMove(new Vector2(0,-1)));
		Assert.assertTrue(rook.isValidMove(new Vector2(0,1)));
	}
	
	@Test
	public void RookShouldMoveOneFieldLForwardBack() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(rook.isValidMove(new Vector2(1, 0)));
		Assert.assertTrue(rook.isValidMove(new Vector2(-1, 0)));
	}
	
	@Test
	public void RookShouldMoveSeveralFieldsLeftRight() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(rook.isValidMove(new Vector2(0,-5)));
		Assert.assertTrue(rook.isValidMove(new Vector2(0,5)));
	}
	
	@Test
	public void RookShouldMoveSeveralFieldsForwardBack() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(rook.isValidMove(new Vector2(3, 0)));
		Assert.assertTrue(rook.isValidMove(new Vector2(-2, 0)));
	}
	
	@Test
	public void RookShouldNotMoveDiagonally() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertFalse(rook.isValidMove(new Vector2(1, 1)));
		Assert.assertFalse(rook.isValidMove(new Vector2(-1, 1)));
		Assert.assertFalse(rook.isValidMove(new Vector2(-1, -1)));
		Assert.assertFalse(rook.isValidMove(new Vector2(1, -1)));
	}
	
	@Test
	public void RookShouldBeEqual() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Chessman rook2 = new Rook(new Vector2(0, 0), MyColor.White);
		Assert.assertEquals(rook, rook2);
	}	
	
	@Test
	public void RookShouldNotBeEqual() {
		Chessman rook = new Rook(new Vector2(0, 0), MyColor.White);
		Chessman rook2 = new Rook(new Vector2(0, 0), MyColor.Black);
		Assert.assertNotEquals(rook, rook2);
	}
	
}
