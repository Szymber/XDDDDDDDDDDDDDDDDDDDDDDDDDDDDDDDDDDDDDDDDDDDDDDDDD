package Test;

import Model.Vector2;
import org.junit.Assert;
import org.junit.Test;


public class Vector2Test {
	
	@Test 
	public void Vector2ShouldNotBeNull() {
		Vector2 vector = new Vector2();
		Assert.assertNotNull(vector);
	}
	
	@Test 
	public void Vector2SWithArgumentsShouldNotBeNull() {
		for (int i = -10; i < 10; i++)
			for (int j = -10; j < 10; j++) {
				Vector2 vector = new Vector2(i, j);
				Assert.assertNotNull(vector);
		}
	}
	
	@Test
	public void Vector2WithVectorArgumentShouldNotBeNull() {
		Vector2 vector = new Vector2();
		Vector2 vector2 = new Vector2(vector);
		Assert.assertNotNull(vector2);
	}
	
	@Test
	public void Vector2FieldsShouldBeEqualToArguments() {
		for (int i = -10; i < 10; i++)
			for (int j = -10; j < 10; j++) {
				Vector2 vector = new Vector2(i, j);
				Assert.assertEquals(vector.getX(), i);
				Assert.assertEquals(vector.getY(), j);
		}
	}
	
	@Test
	public void VectorsShouldBeEqual() {
		Vector2 vector = new Vector2(1,2);
		Vector2 vector2 = new Vector2(1,2);
		Assert.assertEquals(vector, vector2);
		vector2 = new Vector2(vector);
		Assert.assertEquals(vector, vector2);
		vector2 = vector;
		Assert.assertEquals(vector, vector2);
	}
	
	@Test
	public void VectorsShouldNotBeEqual() {
		Vector2 vector = new Vector2(2, 1);
		Vector2 vector2 = new Vector2(1, 2);
		Assert.assertNotEquals(vector, vector2);
	}
	
	@Test
	public void Vector2ShouldBeCorrectString() {
		Vector2 vector = new Vector2(1, 2);
		Assert.assertEquals(vector.toString(), "1 2");
	}
	
	@Test 
	public void Vector2ShouldBeMultipliedCorrect() {
		Vector2 vector = (new Vector2(2,-3)).multiply(2);
		Assert.assertEquals(vector, new Vector2(4,-6));
	}
	
	@Test 
	public void Vector2DistanceShouldBeCalculatedCorrect() {
		Vector2 v = new Vector2(0,0);
		Vector2 v2 = new Vector2(1,1); // sqrt(2);
		Double distance = v.distanceTo(v2);
		Double correct = Math.sqrt(2);
		Assert.assertEquals(distance, correct);
	}
	
	@Test 
	public void Vector2SumShouldBeCalculatedCorrect() {
		Vector2 v = new Vector2(1,1);
		Vector2 v2 = new Vector2(3,1);
		Vector2 sum = v.sum(v2);
		Assert.assertEquals(sum, new Vector2(4, 2));
	}
	
	@Test 
	public void Vector2DiffShouldBeCalculatedCorrect() {
		Vector2 v = new Vector2(1,1);
		Vector2 v2 = new Vector2(3,1);
		Vector2 diff = v.difference(v2);
		Assert.assertEquals(diff, new Vector2(2, 0));
	}
	
}
