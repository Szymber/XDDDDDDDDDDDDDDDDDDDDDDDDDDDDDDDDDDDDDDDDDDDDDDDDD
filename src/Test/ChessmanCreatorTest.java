package Test;

import Model.Vector2;
import Model.MyColor;
import Model.Chessman.Chessman;
import Model.Chessman.ChessmanCreator;
import org.junit.Assert;
import org.junit.Test;


public class ChessmanCreatorTest {

	@Test 
	public void KingShouldNotBeNull() {
		Chessman king = ChessmanCreator.create("King", new Vector2(0,0), MyColor.Black);
		Assert.assertNotNull(king);
		Chessman kingWhite = ChessmanCreator.createOppositeColor(king);
		Assert.assertNotNull(kingWhite);
	}
	
	@Test 
	public void QueenShouldNotBeNull() {
		Chessman queen = ChessmanCreator.create("Queen", new Vector2(0,0), MyColor.Black);
		Assert.assertNotNull(queen);
		Chessman queenWhite = ChessmanCreator.createOppositeColor(queen);
		Assert.assertNotNull(queenWhite);
	}
	
	@Test 
	public void KnightShouldNotBeNull() {
		Chessman knight = ChessmanCreator.create("Knight", new Vector2(0,0), MyColor.Black);
		Assert.assertNotNull(knight);
		Chessman knightWhite = ChessmanCreator.createOppositeColor(knight);
		Assert.assertNotNull(knightWhite);
	}
	
	@Test 
	public void RookShouldNotBeNull() {
		Chessman rook = ChessmanCreator.create("Rook", new Vector2(0,0), MyColor.Black);
		Assert.assertNotNull(rook);
		Chessman rookWhite = ChessmanCreator.createOppositeColor(rook);
		Assert.assertNotNull(rookWhite);
	}
	
	@Test 
	public void ChessmanShouldBeNull() {
		Chessman ch = ChessmanCreator.create("yaiasdasd", new Vector2(0,0), MyColor.Black);
		Assert.assertNull(ch);
		Chessman chBlack = ChessmanCreator.createOppositeColor(ch);
		Assert.assertNull(chBlack);
	}
	
	
	
}
