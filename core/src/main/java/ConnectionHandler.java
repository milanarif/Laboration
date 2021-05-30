
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
            Request request = requestBuilder(inputFromClient);
            //Create response
            String response = RequestHandler.handleRequest(request);

            //Give response
            var outputToClient = new PrintWriter(client.getOutputStream());
            if (response != null) {
                sendResponse(outputToClient, response);
            }

            //Close thread
            inputFromClient.close();
            outputToClient.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Request requestBuilder(BufferedReader input) throws IOException {
        String requestString = requestToString(input);

        RequestType requestType = getType(requestString);
        String url = getUrl(requestString);
        String body = getBody(requestString);

        return new Request(requestType, url, body);
    }

    private static String requestToString(BufferedReader input) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;
        while (input.ready()) {
            line = input.readLine();
            if (!builder.isEmpty()) {
                builder.append("\n");
            }
            if (line.equals("")) {
                builder.append("---body---");
            }
            else builder.append(line);
        }
        return builder.toString();
    }

    private static RequestType getType(String requestString) {
        RequestType requestType;
        String type = requestString.substring(0, requestString.indexOf(" "));

        requestType = switch (type) {
            case "GET" -> RequestType.GET;
            case "POST" -> RequestType.POST;
            case "HEAD" -> RequestType.HEAD;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return requestType;
    }

    private static String getBody(String requestString) {
        if (!requestString.contains("Content-Length:")) {
            return null;
        }
        else return requestString.substring(requestString.indexOf("---body---") + 11, requestString.length());
    }

    private static String getUrl(String requestString) {
        return requestString.substring(requestString.indexOf("/"), requestString.indexOf(" ", requestString.indexOf(" ")+1));
    }

    private static void sendResponse(PrintWriter outputToClient, String response) {
        outputToClient.print("HTTP/1.1 200 OK\r\nContent-length: " + response.length() + "\r\n\r\n" + response);
        outputToClient.flush();
    }
}