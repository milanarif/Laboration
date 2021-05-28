import com.fasterxml.jackson.databind.ObjectMapper;

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

            String jsonResponse = RequestHandler.handleRequest(requestLine);

            var outputToClient = new PrintWriter(client.getOutputStream());
            sendResponse(outputToClient, jsonResponse);

            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: ADD RESPONSES!!
    private static void sendResponse(PrintWriter outputToClient, String response) {
        outputToClient.print(response);
        outputToClient.flush();
    }

    private static String readRequest(BufferedReader inputFromClient) throws IOException {
        String requestLine = inputFromClient.readLine();
        while (true) {
            var line = inputFromClient.readLine();
            if (line == null || line.isEmpty()) {
                break;
            }
        }
        return requestLine;
    }
}
