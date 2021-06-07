
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Started Server...");

        ExecutorService executorService = Executors.newCachedThreadPool();
        int port = 80;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> ConnectionHandler.handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
