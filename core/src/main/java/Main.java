
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        int port = 80;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Server started at port " + port + "...");
                Socket client = serverSocket.accept();
                System.out.println(client.getInetAddress());
                executorService.submit(() -> ConnectionHandler.handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
