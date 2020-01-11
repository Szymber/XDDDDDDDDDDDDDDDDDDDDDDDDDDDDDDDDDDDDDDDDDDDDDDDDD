package Model;

public class Vector2 {
    protected int x;
    protected int y;

    public Vector2() {
        this(0, 0);
    }
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 v) {
        this.x = v.getX();
        this.y = v.getY();
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Vector2 sum(Vector2 v) {
        return new Vector2(v.getX() + getX(), v.getY() + getY());  
    }
    // to get v, from this we have to move..
    public Vector2 difference(Vector2 v) {
        return new Vector2(v.getX() - getX(), v.getY() - getY());  
    }
    public double distanceTo(Vector2 v) {
        return Math.sqrt(
            Math.pow(getX() - v.getX(), 2) + Math.pow(getY() - v.getY(), 2)
        );
    }
    public Vector2 multiply(int m) {
        return new Vector2(getX() * m, getY() * m);
    }
    public String toString() {
        return getX() + " " + getY();
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2 other = (Vector2) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
    
}