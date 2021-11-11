/* Matthew Irvine
 * 1001401200
 * 3/31/2020
 * Windows (through Eclipse IDE)
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class mli1200_PA2 {

	private String currDirectory;
	String pathOffset;
	
	mli1200_PA2()
	{
		Path current = Paths.get("");
		currDirectory = current.toAbsolutePath().toString();
		pathOffset = "";
	}
	
	mli1200_PA2(String dir)
	{
		currDirectory = dir;
		pathOffset = "";
	}
	
	//
	//Note:
	//	Take the specified directory and recursively calculates the size of all the files in the directory and subdirectories.
	//	tabs is used for relative positioning of each files size in a list.
	public double calculateSizeOfAllFilesInDirectory(File directory, double tabs)
	{
		double bytes = 0;	//accumilates total size.
		File[] dirs = directory.listFiles(File::isDirectory);	//first check for subdirectories
		if(dirs.length == 0)									//if no subdirectories find size of all regular files
		{
			File[] files = directory.listFiles();
			for(File f: files)
			{
				//for each file, get the length of the file, print the name and size of the file, and add to bytes the size of the file.
				double tempBytes = 0;
				tempBytes = f.length();
				String name = f.toString();
				String temp = currDirectory + "\\";
				name = name.substring(temp.length());
				
				System.out.print(name);
				for(int x =0; x<(name.length()*tabs)/name.length(); x++)
				{
					System.out.print("\t");
				}
				System.out.println(tempBytes/1000 + " bytes");
				
				bytes += tempBytes;
				//System.out.println(name + " i f " + bytes);
			}
			return bytes;
		}
		else	//if there are subdirectories.
		{
			for(File f: dirs)
			{
				//for each subdirectory recursively find the size of the subdirectory, 
				//subtract one from tabs for each subdirectory until tabs = 0, if tabs is 0 set back to 1.
				//can print name of directories, but we don't want size of directories only of files.
				double tempBytes = 0;
				tabs--;
				if(tabs == 0)
					tabs++;
				tempBytes += calculateSizeOfAllFilesInDirectory(f, tabs);	//this is where all the recursion happens.
				
				//String name = f.toString();
				//String temp = currDirectory + "\\";
				//name = name.substring(temp.length());
				//System.out.print(name);
				//for(int x =0; x<tabs; x++)
				//{
				//	System.out.print("\t");
				//}
				//System.out.println("has " + tempBytes + " bytes");
				
				tabs++;
				bytes += tempBytes;
				//System.out.println(name + " e d " + bytes);
			}
			
			//This does the same thing as when the # of directories is 0 portion
			//This accounts for the files in the current directory when there is a subdirectory present.
			dirs = directory.listFiles(File::isFile);
			for(File p: dirs)
			{
				double tempBytes = 0;
				tempBytes += p.length();
				String name = p.toString();
				String temp = currDirectory + "\\";
				name = name.substring(temp.length());
				
				System.out.print(name);
				for(int x =0; x<(name.length()*tabs)/name.length(); x++)
				{
					System.out.print("\t");
				}
				System.out.println(tempBytes/1000 + " kilobytes");
				bytes += tempBytes;
				//System.out.println(name + " e f " + bytes);
			}
		}
		
		return bytes;
	}
	
	
	public static void main(String[] args) {
		mli1200_PA2 par = new mli1200_PA2();
		File directory = new File(par.currDirectory + par.pathOffset);
		double bytes = par.calculateSizeOfAllFilesInDirectory(directory, 7);
		System.out.println("\nThe size of all the files in " + directory.toString() + " is " + bytes/1000 + " kilobytes.");
		

	}

}
