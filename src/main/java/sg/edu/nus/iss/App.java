package sg.edu.nus.iss;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket socket = new Socket("localhost", 7000);

        try (OutputStream os = socket.getOutputStream()) {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            Console con = System.console();
            String readInput = "";

            while (!readInput.equalsIgnoreCase("close")) {
                readInput = con.readLine("Enter a command: ");
                dos.writeUTF(readInput);
                dos.flush();
            }
        } catch (EOFException ex) {
            socket.close();
        }
    }
}
