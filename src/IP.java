import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Hent ipadresse ved hjælp af ifconfig og lidt regex-trylleri.
 * Created by tomas on 12/20/15.
 */
public class IP {
	String eth = "eth0";
   String shellCommand = "/sbin/ifconfig " + eth;
   Scanner sc;

   public String getIpAddress(){
      String ipaddress = "Failed to get ipaddress of " + eth;
      try{
         Runtime r = Runtime.getRuntime();
         Process p = r.exec(shellCommand);
         InputStream stdin = p.getInputStream();
         sc = new Scanner(stdin);
         Pattern pattern = Pattern.compile("\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b");

			while( sc.hasNextLine() && ((ipaddress = sc.findInLine(pattern))== null) ){
				sc.nextLine();
			}
      }catch(IOException e){System.out.println("IOException: "+e+"     in " + this.getClass());}
      return ipaddress;
   }
}