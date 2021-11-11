package mli1200_PA2;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.System;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class PA2 {
	
	private String currDirectory;
	private String ToDPtW;
	String pathOffset;
	
	interface Control{
		public int pathCont(int pcmd);
	}
	
	PA2()
	{
		Path current = Paths.get("");
		currDirectory = current.toAbsolutePath().toString();
		ToDPtW = "[Chancellor Palpatine and Anakin Skywalker talk about Skywalker's dreams]\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"Did you ever hear the Tragedy of Darth Plagueis the Wise?\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"No.\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"I thought not. It's not a story the Jedi would tell you. It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith so powerful and so wise, he could use the Force to influence the midi-chlorians to create...life. He had such a knowledge of the Dark Side, he could even keep the ones he cared about...from dying.\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"He could actually...save people from death?\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"The dark side of the Force is a pathway to many abilities some consider to be unnatural.\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"What happened to him?\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"He became so powerful, the only thing he was afraid of was losing his power...which, eventually of course, he did. Unfortunately, he taught his apprentice everything he knew. Then his apprentice killed him in his sleep. Ironic. He could save others from death...but not himself.\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"Is it possible to learn this power?\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"Not from a Jedi.";
		
		pathOffset = "\\src\\mli1200_PA2\\";
	}
	
	PA2(String dir)
	{
		currDirectory = dir;
		ToDPtW = "[Chancellor Palpatine and Anakin Skywalker talk about Skywalker's dreams]\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"Did you ever hear the Tragedy of Darth Plagueis the Wise?\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"No.\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"I thought not. It's not a story the Jedi would tell you. It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith so powerful and so wise, he could use the Force to influence the midi-chlorians to create...life. He had such a knowledge of the Dark Side, he could even keep the ones he cared about...from dying.\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"He could actually...save people from death?\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"The dark side of the Force is a pathway to many abilities some consider to be unnatural.\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"What happened to him?\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"He became so powerful, the only thing he was afraid of was losing his power...which, eventually of course, he did. Unfortunately, he taught his apprentice everything he knew. Then his apprentice killed him in his sleep. Ironic. He could save others from death...but not himself.\r\n" + 
				"\r\n" + 
				"Anakin:\r\n" + 
				"Is it possible to learn this power?\r\n" + 
				"\r\n" + 
				"Palpatine:\r\n" + 
				"Not from a Jedi.";
		
		pathOffset = "\\src\\mli1200_PA2\\";
	}
	
	private String getPathOffset()
	{
		return pathOffset;
	}
	
	private void setPathOffset(String off)
	{
		pathOffset = off;
	}
	
	private void appendPathOffset(String app)
	{
		pathOffset += app;
	}
	
	private void detachPathOffset()
	{
		String[] arrPath = pathOffset.split("\\");
		for(int i = 0; i<arrPath.length; i++)
		{
			System.out.println(arrPath[i]);
		}
	}
	
	//
	//Note:
	//	Take the specified directory and recursively calculates the size of all the files in the directory and subdirectories.
	//	For some reason it will not calculate some files such as PA2.java, perhaps because they are in use?
	public double calculateSizeOfAllFilesInDirectory(File directory)
	{
		double bytes = 0;
		File[] dirs = directory.listFiles(File::isDirectory);
		if(dirs.length == 0)
		{
			File[] files = directory.listFiles();
			for(File f: files)
			{
				double tempBytes = 0;
				tempBytes = f.length();
				String name = f.toString();
				String temp = currDirectory + "\\";
				name = name.substring(temp.length());
				System.out.println(name + " has " + tempBytes + " bytes");
				bytes += tempBytes;
			}
			return bytes;
		}
		else
		{
			for(File f: dirs)
			{
				double tempBytes = 0;
				tempBytes += calculateSizeOfAllFilesInDirectory(f);
				String name = f.toString();
				String temp = currDirectory + "\\";
				name = name.substring(temp.length());
				
				//System.out.println(name + " has " + tempBytes + " bytes");
				bytes += tempBytes;
			}
		}
		return bytes;
	}
	
	//creates n number of files in the directory specified by path.
	private void testFiles(String path, int n)
	{
		String ftest = "testcase";
		String subPath = path;
		for(int i = 0; i<n; i++)
		{
			ftest = ftest + (n-i) + "" + i + ".txt";
			subPath += ftest;
			File file = new File(subPath);
			try 
			{
				if(file.createNewFile())
				{
					FileWriter writer = new FileWriter(file);
					writer.write(ToDPtW);
					writer.close();
				}
				else
				{
					System.out.println("File already exists.");
				}
			}
			catch(IOException e)
			{
				System.out.println("Create new file failed.");
			}
			
			
			subPath = path;
			ftest = "testcase";
		}
	}
	
	//creates n number of directories
	private void testDirectories(int n)
	{
		String test = "Directory";
		String dir = currDirectory + pathOffset;
		for(int i = 0; i<n; i++)
		{
			test = test + "0" + i;
			String path = dir + test;
			File file = new File(path);
			boolean bool = file.mkdir();
			if(!bool)
			{
				System.out.println("Failed to make " + dir);
				continue;
			}
			path += "\\";
			testFiles(path, i);
			path = dir;
			test = "Directory";
		}
	}
	
	//lists all of the test files in all sub directories
	private void listTests() throws IOException
	{
		String pathFinal = currDirectory + pathOffset;
		System.out.println(pathFinal);
		Stream<Path> paths = Files.walk(Paths.get(pathFinal));
		
		paths.filter(Files::isRegularFile).forEach(System.out::println);
		paths.close();
	}
	
	private void deleteTests(String dir) throws IOException
	{
		String pathFinal = dir;
		Stream<Path> paths = Files.walk(Paths.get(pathFinal));
		paths.forEach(arg0 -> {
			try {
				Files.delete(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		});
		
		paths.close();
	}
	
	private boolean deleteDirectory(File dir) throws IOException
	{
		File[] files = dir.listFiles();
		if(files != null)
		{
			for(File file: files)
			{
				deleteDirectory(file);
			}
		}
		
		return dir.delete();
	}
	
	private int Conroller(int cmd, Scanner scan, PA2 pa)
	{
		int ret = cmd;
		switch(ret)
		{
		case 1:
		{
			int num;
			System.out.println("How many directories?");
			num = scan.nextInt();
			pa.testDirectories(num);
			break;
		}
		
		case 2:
		{
			try
			{
				String pathFinal = pa.currDirectory + pa.pathOffset;
				pa.deleteTests(pathFinal);
			}
			catch(IOException e)
			{
				System.out.println("Failed to delete tests");
			}
			break;
		}
		case 3:
		{
			File directory = new File(currDirectory);
			System.out.println((int) pa.calculateSizeOfAllFilesInDirectory(directory) + " bytes");
			break;
		}
		case 4:
		{
			try 
			{
				pa.listTests();
			}
			catch(IOException e)
			{
				System.out.println("Failed to list Tests.");
			}
			
			break;
		}
		case 5:
		{
			String filename = currDirectory + pathOffset;
			File dir = new File(filename);
			try
			{
				pa.deleteDirectory(dir);
			}
			catch(IOException e)
			{
				System.out.println("deleteDirectory failed, check " + filename + "for any discrepancies.");
			}
			
			break;
		}
		case 6:
		{
			String filename = currDirectory + pathOffset;
			System.out.print("name: \t");
			scan.reset();
			String dirname = scan.nextLine();
			File file = new File(filename + dirname);
			if(!file.mkdir())
			{
				System.out.println("Failed to create " + dirname);
			}
			
			break;
		}
		case 8:
		{
			class pathController implements Control{

				@Override
				public int pathCont(int pcmd)
				{
					int pret = pcmd;
					switch(pret)
					{
					case 1:
					{
						System.out.println(pa.getPathOffset());
						break;
					}
					case 2:
					{
						String ndir;
						System.out.println("What is the new Directory?");
						ndir = scan.nextLine();
						pa.setPathOffset(ndir);
						break;
					}
					case 3:
					{
						String app;
						File file = new File(currDirectory + pathOffset);
						File[] files = file.listFiles(File::isDirectory);
						for(File f: files)
						{
							String dirname = f.toString();
							dirname = dirname.substring(dirname.lastIndexOf(currDirectory+pathOffset));
							System.out.println(dirname);
						}
						System.out.println("type directory to append to pathOffset. (or type 0 to end)");
						app = scan.nextLine();
						app += "\\";
						
						if(app.compareTo("0") != 0)
						{
							pa.appendPathOffset(app);
						}
							
						break;
					}
					case 4:
					{
						pa.detachPathOffset();
						break;
					}
					case 0:
					{
						pret = -1;
						break;
					}
					default:
					{
						System.out.println("Invalid option");
						break;
					}
					}
					return pret;
				}
				
			}
			
			int pcmd = -1;
			String menu = "1. Show pathOffset.\n"
					+ "2. Change pathOffset\n"
					+ "3. Append pathOffset\n"
					+ "4. detach pathOffset\n"
					+ "0. Exit\n";
			do
			{
				Control pathing = new pathController();
				System.out.println(menu);
				pcmd = scan.nextInt();
				scan.nextLine();
				pcmd = pathing.pathCont(pcmd);
			}while(pcmd >= 0);
			break;
		}
		case 9:
		{
			System.out.println(pa.currDirectory);
			break;
		}
		case 0:
		{
			ret = -1;
			System.out.println("Goodbye");
			break;
		}
		default:
		{
			System.out.println("Invalid option.");
			break;
		}
		}
		return ret;
	}
	
	public static void main(String args[])
	{
		PA2 pa = new PA2();
		int cmd = -1;
		Scanner scan = new Scanner(System.in);
		
		String menu = "1. Do you want to make tests?\n"
				+ "2. Do you want to delete tests?\n"
				+ "3. Do you want to check the size of all files in a directory?\n"
				+ "4. List all test files.\n"
				+ "5. Delete a directory\n"
				+ "6. Create a directory\n"
				+ "7. Change directory\n"
				+ "8. Manipulate pathOffset.\n"
				+ "9. show Current Directory\n"
				+ "0. Exit.\n";
		
		if(args.length == 0)
		{
			do
			{
				System.out.println(menu);
				cmd = scan.nextInt();
				scan.nextLine();
				cmd = pa.Conroller(cmd, scan, pa);
			}while(cmd >= 0);
		}else
		{
			for(int i = 0; i<args.length; i++)
			{
				menu += args[i];
			}
			
			do
			{
				System.out.println(menu);
				cmd = scan.nextInt();
				scan.nextLine();
				cmd = pa.Conroller(cmd, scan, pa);
			}while(cmd >= 0);

		}

	}

}
