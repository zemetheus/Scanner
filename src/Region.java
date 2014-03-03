import java.util.ArrayList;

public class Region {

	static int regionID =1;
	int ID;
	ArrayList<Coordinate> coordinates;
	
	public Region()
	{
		this.ID = regionID;
		regionID++;
		
		this.coordinates = new ArrayList<>();
	}
	
	public void add(Coordinate coord)
	{
		coordinates.add(coord);
	}
	
	public int getSize()
	{
		return coordinates.size();
	}
	
	public int getID()
	{
		return ID;
	}
	
	public ArrayList<Coordinate> getCoordinates()
	{
		return coordinates;
	}
}
