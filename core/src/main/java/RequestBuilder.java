import java.io.BufferedReader;
import java.io.IOException;

public class RequestBuilder {

    private static RequestType type;
    private static String url;
    private static String body;

    public static Request buildRequest(BufferedReader input) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;
        boolean insideBody = false;
        boolean initialLoop = true;
        while (input.ready()) {
            line = input.readLine();

            if (initialLoop) {
                initialLoop = false;
                String[] header = line.split(" ");
                type = getType(header[0]);
                url = header[1];
                if (type != RequestType.POST) {
                    break;
                }
            }

            if (insideBody) {
                builder.append(line).append("\n");
            }
            if (line.trim().isEmpty()) {
                insideBody = true;
            }
        }
        body = builder.toString();
        return new Request(type, url, body);
    }

    private static RequestType getType(String type) {
        RequestType requestType;

        requestType = switch (type) {
            case "GET" -> RequestType.GET;
            case "POST" -> RequestType.POST;
            case "HEAD" -> RequestType.HEAD;
            default -> throw new IllegalStateException("Unsupported Request Type: " + type);
        };
        return requestType;
    }

    private static String getBody(String requestString) {
        if (!requestString.contains("Content-Length:")) {
            return null;
        }
        else return requestString.substring(requestString.indexOf("---body---") + 11, requestString.length());
    }
}
