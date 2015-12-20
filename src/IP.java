import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by tomas on 12/20/15.
 */
public class IP {
   String shellCommand = "/sbin/ifconfig eth0";
   Scanner sc;

   public String getIpAddress(){
      String ipaddress = "FAIL!";
      try{
         Runtime r = Runtime.getRuntime();
         Process p = r.exec(shellCommand);
         InputStream stdin = p.getInputStream();
         sc = new Scanner(stdin);
         Pattern pattern = Pattern.compile("\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b");

         while( (ipaddress = sc.findInLine(pattern)) == null) {
            sc.nextLine();
         }
      }catch(IOException e){System.out.println("IOException in " + this.getClass());}
      //System.out.println(ipaddress); // DEBUG
      return ipaddress;
   }
}