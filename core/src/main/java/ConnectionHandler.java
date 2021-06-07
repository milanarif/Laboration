
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler {

    public static void handleConnection(Socket client) {
        try {

            //Take request
            var inputFromClient = new BufferedReader(new InputStreamReader((client.getInputStream())));

            //Build request
            Request request = RequestBuilder.buildRequest(inputFromClient);
            System.out.println("Request Built!");

            //Create response
            String response = RequestHandler.handleRequest(request);
            System.out.println("Response Created!");

            //Give response
            var outputToClient = client.getOutputStream();

            if (response != null) {
                ResponseSender.sendResponse(outputToClient, response);
            }

            //Close thread
            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}