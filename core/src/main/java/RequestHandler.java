import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PostFunctions;
import entity.Post;

import static java.lang.Integer.parseInt;

public class RequestHandler {

    public static String handleRequest(Request request) {

        return switch (request.getRequestType()) {
            case GET -> getRespond(request);
            case POST -> post(request);
            case HEAD -> headRespond(request);
        };
    }

    private static String getRespond(Request request) {
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

    //TODO
    private static String headRespond(Request request) {
        return null;
    }

    //TODO
    private static String post(Request request) {
        String body = request.getBody();
        try {
            Post post = new ObjectMapper().readValue(body, Post.class);
            System.out.println(post.toString());
            PostFunctions.addPost(post);
        } catch (JsonProcessingException j) {
            j.printStackTrace();
        }
        return "";
    }
}
