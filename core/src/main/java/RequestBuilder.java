import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class RequestBuilder {

    private static RequestType type;
    private static String url;

    public static Request buildRequest(BufferedReader input) throws IOException {
        //TODO: Unnecessary for all non-posts...
        StringBuilder builder = new StringBuilder();
        String line;
        boolean insideBody = false;
        boolean initialLoop = true;
        int byteSize = 0;
        boolean finished = false;

        while (true) {
            line = input.readLine();
            System.out.println(line);

            if (initialLoop) {
                initialLoop = false;
                String[] header = line.split(" ");
                type = getType(header[0]);
                url = header[1];
                if (type != RequestType.POST) {
                    break;
                }
            }
            else if (line.toUpperCase().startsWith("CONTENT-LENGTH")) {
                line = line.replaceAll("[^\\d.]", "");
                byteSize = Integer.parseInt(line);
                System.out.println(byteSize);
            }
            else if (line.trim().isEmpty() && byteSize > 0) {
                while (byteSize > 0) {
                    line = input.readLine();
                    System.out.println(line);
                    byteSize -= line.getBytes(StandardCharsets.UTF_8).length + 2;
                    System.out.println(byteSize);
                    builder.append(line);
                    if (byteSize == 0) {
                        finished = true;
                        break;
                    }
                }
            }
            if (finished) {
                break;
            }
        }
        String body = builder.toString();
        System.out.println(body);
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
}
