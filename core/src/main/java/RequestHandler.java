import dao.PostFunctions;
import entity.Post;
import com.google.gson.Gson;
import java.util.List;

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
            List<Post> posts = PostFunctions.getAllPosts();

            Gson gson = new Gson();
            String json = gson.toJson(posts);

            return json;
        } else {
            return null;
        }
    }

    //TODO
    private static String headRespond(Request request) {
        return null;
    }

    //TODO
    private static String post(Request request) {
        Gson gson = new Gson();

        Post post = gson.fromJson(request.getBody(), Post.class);

        PostFunctions.addPost(post);

        return "123";
    }
}
