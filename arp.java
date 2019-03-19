import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class arp {

    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        ProcessBuilder processBuilderq = new ProcessBuilder();
        ProcessBuilder processBuilderr = new ProcessBuilder();
        
        processBuilder.command("bash", "-c", "route -n | cut -d ' ' -f 1 | egrep '192.[0-9]{3}.[0-9].[0-9]'");
        processBuilderq.command("bash", "-c", "ip addr | egrep 'inet' | awk 'NR==3{print $2}'");
        processBuilderr.command("bash", "-c", "./latest.sh");

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Gateway : "+line);
            }

            int exitCode = process.waitFor();
            //System.out.println("\nExited with error code : " + exitCode);

            Process processq = processBuilderq.start();

            BufferedReader readerq =
                    new BufferedReader(new InputStreamReader(processq.getInputStream()));

            String lineq;
            while ((lineq = readerq.readLine()) != null) {
                System.out.println("\nOurIP : "+lineq);
            }        

            int exitCodeq = processq.waitFor();
            System.out.println("\n");
            //System.out.println("\nExited with error code : " + exitCodeq);


            Process processr = processBuilderr.start();

            BufferedReader readerr =
                    new BufferedReader(new InputStreamReader(processr.getInputStream()));

            String liner;
            while ((liner = readerr.readLine()) != null) {
                System.out.println("OurIP : "+liner);
            }        

            int exitCoder = processr.waitFor();
            System.out.println("Exit Code: "+exitCoder);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

