import java.io.*;

/**
 * Metoder til at modtage sms.
 * Created by tomas on 11/12/15.
 */
public class Get {
	String shellCommand = "ls ";
	//Constants
	//String shellCommand = "sudo gnokii --identify";
	//String shellCommand = "sudo gnokii --smsreader";
	//String shellCommand = "touch test";
	//String shellCommand = "echo Blåbærgrød";

	public void text(){
		try{
			Runtime r = Runtime.getRuntime();
			//Process p = r.exec(new String[]{"bash", "-c", shellCommand});
			Process p = r.exec(shellCommand);

			//printStderr(p); //First print stderr, since --smsreader never finishes.
			// printStdinByte(p);
			printStdinLine(p);
		}
		catch(IOException e){
			System.out.println("IOException caught: " + e);
		}
	}
	public void printStderr(Process p) throws IOException{
		System.out.println("Printing from stderr: ");
		InputStream stderr = p.getErrorStream();
		int i;
		while( (i=stderr.read()) != -1 ){
			System.out.print((char)i);
		}
	}
	public void printStdinLine(Process p) throws IOException{
		System.out.println("Printing from stdin");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));
		String line;
		while( (line=stdin.readLine()) != null ){
			System.out.println(line);
		}
	}
	public void printStdinByte(Process p) throws IOException{
		System.out.println("Printing from stdinByte");
		InputStreamReader stdin = new InputStreamReader(p.getInputStream(), "UTF-8");
		int b;
		while( (b=stdin.read()) !=-1 ){
			System.out.print((char)b);
		}
	}
}
