import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ResponseSender {

    static void sendResponse(OutputStream outputToClient, String response, RequestType requestType) throws IOException {
        if (requestType == RequestType.POST) {
            String header = "HTTP/1.1 200 OK\r\n";
            outputToClient.write(header.getBytes(StandardCharsets.UTF_8));
        }

        else {
            byte[] body = response.getBytes(StandardCharsets.UTF_8);
            String header = "HTTP/1.1 200 OK\r\nContent-length: " + response.length() + "\r\n\r\n";
            outputToClient.write(header.getBytes(StandardCharsets.UTF_8));
            outputToClient.write(body);
        }

        outputToClient.flush();
    }
}
