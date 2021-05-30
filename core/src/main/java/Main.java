
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(80)) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println(client.getInetAddress());
                executorService.submit(() -> ConnectionHandler.handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
