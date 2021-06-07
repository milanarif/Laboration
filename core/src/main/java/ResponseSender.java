import java.io.PrintWriter;

public class ResponseSender {
    static void sendResponse(PrintWriter outputToClient, String response) {
        outputToClient.print("HTTP/1.1 200 OK\r\nContent-length: " + response.length() + "\r\n\r\n" + response);
        outputToClient.flush();
    }
}
