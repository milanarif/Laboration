import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ResponseSender {

    static void sendResponse(OutputStream outputToClient, String response) throws IOException {
        byte[] body = response.getBytes(StandardCharsets.UTF_8);
        String header = "HTTP/1.1 200 OK\r\nContent-length: " + response.length() + "\r\n\r\n";
        System.out.println(header + "\n" + body);
        outputToClient.write(header.getBytes(StandardCharsets.UTF_8));
        outputToClient.write(body);
        outputToClient.flush();
    }
}
