import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
	    inetStuff();
        p("\n\n");
        URLConnection0();
        p("\n\n");
        URLConnection1();
        p("\n\n");
    }

    public static void inetStuff() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("horstmann.com");
        System.out.println(address);
        p("HostName: " + address.getHostName());
        p("CannonicalHostName: " + address.getCanonicalHostName());
        p("HostAddresss: " + address.getHostAddress());
        System.out.println(address.getClass());
        p("\n\n");

    }

    public static void p(String x){
        System.out.println(x);
    }

    public static void URLConnection0(){
        try{
            URL url = new URL("http://www.google.com");
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null){
                p(line);
            }

        }catch(IOException message){
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(message); //exception handle
        }
    }

    public static void URLConnection1(){
        final int BUFFER_LENGTH = 64;
        try{
            URL url = new URL("http://www.google.com");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ReadableByteChannel channel = Channels.newChannel(inputStream);
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
            String line = null;
            while (channel.read(buffer) > 0) {
                System.out.println(new String(buffer.array()));
                buffer.clear();
            }
            channel.close();
        }catch(IOException message){
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(message); //exception handle
        }
    }
}
