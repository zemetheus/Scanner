import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Grid
{
	int rows,cols;
	int[][] grid, flags;
	ArrayList<Region> regions = new ArrayList<>();
	
	//default constructor
	public Grid(Scanner keyboard)
	{
		String fileName = this.getFileName(keyboard);
		this.load(fileName);
	}
	
	/**
	 * constructor that intializes the variables and loads the input file
	 * @param inputFileName
	 */
	public Grid(String inputFileName)
	{
		this.load(inputFileName);
	}
	
	// loads the initial data from a text file
	public void load(String inputFileName)
	{
		try
		{
			File file = new File(inputFileName);
			Scanner reader = new Scanner(file);
			String line;
			char[] charArray;
			
			rows = reader.nextInt();
			cols = reader.nextInt();
			reader.nextLine();

			grid = new int[rows][cols];
			flags = new int[rows][cols];
			
			for(int n=0;n<rows;n++)
			{
				line = reader.nextLine();
				
				for(int m=0;m<cols;m++)
				{
					charArray = this.removeWhitespace(line).toCharArray();
					
					if(!Character.isWhitespace(charArray[m]))
						grid[n][m] = Integer.parseInt(Character.toString(charArray[m]));
					else
						continue;
				}
			}
			
			System.out.println(Arrays.deepToString(grid));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("That file does not exist.");
		}
	}
	// generates and store the results in the object
	public void findRegions()	
	{
		boolean scanFlag = true;
		
		for(int n=0;n<grid.length;n++)
		{
			for(int m=0;m<grid[n].length;m++)
			{
				if(grid[n][m] == 1)
				{
					if(flags[n][m] != 0)
					{
						regions.get(flags[n][m]-1).add(new Coordinate(m,n));
						flagAdjacents(flags,n,m,flags[n][m]);
					}
					else
					{
						//scan
						//this branch activates if the grid is forest and the flag is 0.
						//it will scan for adjacent flagged forest
						for(int i=n-1;i<=n+1;i++)
						{
							for(int j=m-1;j<=m+1;j++)
							{
								try
								{
									if(flags[i][j] != 0 && grid[i][j] == 1)
									{
										System.out.println("i: "+i+"j: "+j);
										regions.get(flags[i][j]-1).add(new Coordinate(m,n));
										flagAdjacents(flags,n,m,flags[i][j]);
										scanFlag = false;
									}
								}
								catch(IndexOutOfBoundsException e)
								{
									continue;
								}
							}
						}
						
						if(scanFlag)
						{
							regions.add(new Region());
							regions.get(regions.size()-1).add(new Coordinate(m,n));
							flagAdjacents(flags,n,m,regions.size());
						}
					}
				}
			}
		}
		int row, col;
		
		printArray(grid);
		System.out.println("=================");
		
		
		for(Region r : regions)
		{
			for(Coordinate c : r.getCoordinates())
			{
				row = c.getY();
				col = c.getX();
				
				grid[row][col] = r.getID();
			}
		}
		
		printArray(grid);
	}
	
	private void flagAdjacents(int[][] flags, int n, int m, int flag)
	{
		
			for(int i=n-1;i<=n+1;i++)
			{
				for(int j=m-1;j<=m+1;j++)
				{
					try
					{
						flags[i][j] = flag;
					}
					catch(IndexOutOfBoundsException e)
					{
						continue;
					}
				}
			}
		
	}
	private String removeWhitespace(String line)
	{
		String temp = "";
		
		for(int n=0;n<line.length();n++)
		{
			if(!Character.isWhitespace(line.charAt(n)))
			{
				temp += line.charAt(n);
			}
		}
		
		return temp;
	}
	private String getFileName(Scanner keyboard)
	{
		String fileName;
		
		System.out.println("Enter the name of the file");
		fileName = keyboard.nextLine();
		
		File file = new File(fileName);
		
		return fileName;
		
		/*if(file.exists())
			return fileName;
		else
			return getFileName(keyboard);*/
		
	}
	
	public void save(String outputFileName)
	{
		PrintWriter pw;
		File file = new File(outputFileName);
		
		try
		{
			pw = new PrintWriter(file);
			
			pw.write("Region");
			pw.write("Size");
			pw.write("Labeled Regions");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The File Was Not Found.");
			e.printStackTrace();
		}
	}
	
	public void printArray(int[][] array)
	{
		for(int n=0;n<array.length;n++)
		{
			for(int m=0;m<array[n].length;m++)
				System.out.print(array[n][m]+" ");
			
			System.out.println();
		}
	}
}