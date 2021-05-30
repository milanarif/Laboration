import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PostFunctions;

import static java.lang.Integer.parseInt;

public class RequestHandler {

    public static String handleRequest(String requestLine) {

        Request request = requestBuilder(requestLine);

        if (request.getRequestType() == RequestType.GET) {
            return getHandler(request);
        }
        else if (request.getRequestType() == RequestType.POST) {
            postHandler(request);
        }
        else {
            return headHandler(request);
        }
        return null;
    }

    private static Request requestBuilder(String requestLine) {
        RequestType requestType = RequestType.valueOf(requestLine.split(" ")[0]);
        String url = requestLine.split(" ")[1];

        return new Request(requestType, url);
    }

    private static String headHandler(Request request) {
        System.out.println("HEADER REQUEST");
        return "";
    }

    private static void postHandler(Request request) {
        //TODO: ADD POST CAPABILITY!
    }

    private static String getHandler(Request request) {
        System.out.println("GET REQUEST!");
        if (request.getUrl().equals("/getAll")) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(PostFunctions.getAllPosts());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        else if (request.getUrl().contains("id=")) {
            Integer targetId = parseInt(request.getUrl().split("id=")[1]);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(PostFunctions.getPost(targetId));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
