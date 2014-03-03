
public class Coordinate {

	int x,y;
	
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public String toString()
	{
		return "("+x+","+y+")";
	}
	
	public void print()
	{
		System.out.println("("+x+","+y+")");
	}
}
