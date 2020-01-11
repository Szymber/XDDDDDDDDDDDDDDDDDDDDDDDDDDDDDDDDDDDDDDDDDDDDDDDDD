package Test;
import org.junit.Assert;
import org.junit.Test;


import Model.*;
import Model.Chessman.*;
import Model.Board.Grid;

public class GameTest {

	@Test
	public void GameShouldNotBeNull() {
		Game g = new Game(
					new Grid(new Vector2(4,4)),
					new Chessman[] {
						new Knight(new Vector2(3,0), MyColor.White),
						new Knight(new Vector2(0,0), MyColor.Black)
					});
		Assert.assertNotNull(g);
	}

	@Test
	public void GameGridSHouldNotBeNull() {
		Game g = new Game(
				new Grid(new Vector2(4,4)),
				new Chessman[] {
					new Knight(new Vector2(3,0), MyColor.White),
					new Knight(new Vector2(0,0), MyColor.Black)
				});
		Assert.assertNotNull(g.getGrid());
	}
}
