import dao.PostFunctions;
import entity.Post;

import java.net.URL;
import java.util.List;

public class RequestHandler {
    public static void handleRequest(String requestLine) {
        Request request = requestFormer(requestLine);

        if (request.getRequestType() == RequestType.GET) {
            getHandler(request);
        }
        else if (request.getRequestType() == RequestType.POST) {
            postHandler(request);
        }
        else {
            headHandler(request);
        }
    }

    private static void headHandler(Request request) {
        System.out.println("HEADER REQUEST");
    }

    private static void postHandler(Request request) {
        System.out.println("POST REQUEST");
        System.out.println("REQUESTTYPE: " + request.getRequestType());
        System.out.println("URL: " + request.getUrl());
        if (request.getUrl().equals("/post")) {
            System.out.println("INSIDE");
            PostFunctions.addPost(new Post("A", "B", "C"));
        }
    }

    private static void getHandler(Request request) {
        System.out.println("GET REQUEST!");
        List<Post> posts = PostFunctions.getAllPosts();
        for (Post p : posts) {
            System.out.println(p.getSubject() + p.getAuthor() + p.getText());
        }
    }

    private static Request requestFormer(String requestLine) {
        RequestType requestType = RequestType.valueOf(requestLine.split(" ")[0]);
        String url = requestLine.split(" ")[1];

        return new Request(requestType, url);
    }
}
