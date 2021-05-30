package dao;

import entity.Post;

import java.util.List;

public class PostFunctions {
    static PostDao postDao = new PostDao();

    public static List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    public static Post getPost(Integer id) {
        return postDao.getPost(id);
    }

    public static void addPost(Post post) {
        postDao.addPost(post);
    }

    public static void changeText(Integer id, String text) {
        postDao.changeText(id, text);
    }

    public static void removePost(Integer id) {
        postDao.removePost(id);
    }
}
