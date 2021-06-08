package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RequestBuilder {

    private static RequestType type;
    private static String url;

    public static Request buildRequest(BufferedReader input) throws IOException {
        //TODO: Ugly
        StringBuilder builder = new StringBuilder();
        String line;
        boolean initialLoop = true;
        int byteSize = 0;
        boolean finished = false;

        while (true) {
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
            else if (line.toUpperCase().startsWith("CONTENT-LENGTH")) {
                line = line.replaceAll("[^\\d.]", "");
                byteSize = Integer.parseInt(line);
            }
            else if (line.trim().isEmpty() && byteSize > 0) {
                while (byteSize > 0) {
                    line = input.readLine();
                    byteSize -= line.getBytes(StandardCharsets.UTF_8).length + 2;
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
        return new Request(type, url, body);
    }

    private static RequestType getType(String type) {
        RequestType requestType;

        requestType = switch (type) {
            case "GET" -> RequestType.GET;
            case "POST" -> RequestType.POST;
            case "HEAD" -> RequestType.HEAD;
            default -> throw new IllegalStateException("Unsupported core.Request Type: " + type);
        };
        return requestType;
    }
}
