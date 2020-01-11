package Test;

import Model.Chessman.Queen;
import Model.MyColor;
import Model.Chessman.Chessman;
import Model.Vector2;
import org.junit.Assert;
import org.junit.Test;


public class QueenTest {

	@Test 
	public void QueenShouldNotBeNull() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertNotNull(queen);
	}
	
	@Test
	public void QueenShouldMoveOneFieldLeftRight() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(queen.isValidMove(new Vector2(0,-1)));
		Assert.assertTrue(queen.isValidMove(new Vector2(0,1)));
	}
	
	@Test
	public void QueenShouldMoveOneFieldLForwardBack() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(queen.isValidMove(new Vector2(1, 0)));
		Assert.assertTrue(queen.isValidMove(new Vector2(-1, 0)));
	}
	
	@Test
	public void QueenShouldMoveSeveralFieldsLeftRight() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(queen.isValidMove(new Vector2(0,-5)));
		Assert.assertTrue(queen.isValidMove(new Vector2(0,5)));
	}
	
	@Test
	public void QueenShouldMoveSeveralFieldsForwardBack() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(queen.isValidMove(new Vector2(3, 0)));
		Assert.assertTrue(queen.isValidMove(new Vector2(-2, 0)));
	}
	
	@Test
	public void QueenShouldMoveOneFieldDiagonally() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(queen.isValidMove(new Vector2(1, 1)));
		Assert.assertTrue(queen.isValidMove(new Vector2(-1, 1)));
		Assert.assertTrue(queen.isValidMove(new Vector2(-1, -1)));
		Assert.assertTrue(queen.isValidMove(new Vector2(1, -1)));
	}
	
	@Test
	public void QueenShouldMoveSeveralFieldsDiagonally() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertTrue(queen.isValidMove(new Vector2(3, 3)));
		Assert.assertTrue(queen.isValidMove(new Vector2(3,-3)));
		Assert.assertTrue(queen.isValidMove(new Vector2(-3,-3)));
		Assert.assertTrue(queen.isValidMove(new Vector2(-3, 3)));
	}
	
	@Test
	public void QueenShouldNotMove() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertFalse(queen.isValidMove(new Vector2(5, 3)));
		Assert.assertFalse(queen.isValidMove(new Vector2(3,-2)));
		Assert.assertFalse(queen.isValidMove(new Vector2(-3,-1)));
		Assert.assertFalse(queen.isValidMove(new Vector2(-8, 3)));
	}
	
	@Test
	public void QueenShouldBeEqual() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Chessman queen2 = new Queen(new Vector2(0, 0), MyColor.White);
		Assert.assertEquals(queen, queen2);
	}	
	
	@Test
	public void QueenShouldNotBeEqual() {
		Chessman queen = new Queen(new Vector2(0, 0), MyColor.White);
		Chessman queen2 = new Queen(new Vector2(0, 0), MyColor.Black);
		Assert.assertNotEquals(queen, queen2);
	}
	
}
