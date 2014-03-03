import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		Grid grid = new Grid("input.txt");
		
		grid.findRegions();
		
		grid.save("output.txt");
		
		keyboard.close();
		
	}
}
