import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(80)) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Connection from : " + client.getInetAddress());
                executorService.submit(() -> handleConnection(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket client) {
        try {
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));
            String requestLine = readRequest(inputFromClient);

            RequestHandler.handleRequest(requestLine);

            var outputToClient = new PrintWriter(client.getOutputStream());
            sendResponse(outputToClient);

            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(PrintWriter outputToClient) {
        outputToClient.print("HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n");
        outputToClient.flush();
    }

    private static String readRequest(BufferedReader inputFromClient) throws IOException {
        var request = inputFromClient.readLine();
        while (true) {
            var line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
        }
        return request;
    }
}
