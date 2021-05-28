import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(80)) {
            Socket client = socket.accept();
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));

            while (true) {
                var line = inputFromClient.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                System.out.println(line);
            }
            var outPutToClient = new PrintWriter(client.getOutputStream());
            outPutToClient.println("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");
            outPutToClient.flush();
            inputFromClient.close();
            outPutToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
