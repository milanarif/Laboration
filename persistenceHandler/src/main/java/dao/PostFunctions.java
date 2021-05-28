package dao;

import entity.Post;

import java.util.List;

public class PostFunctions {
    static PostDao postDao = new PostDao();

    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    public Post getPost(Integer id) {
        return postDao.getPost(id);
    }

    public void addPost(Post post) {
        postDao.addPost(post);
    }

    public void changeText(Integer id, String text) {
        postDao.changeText(id, text);
    }

    public void removePost(Integer id) {
        postDao.removePost(id);
    }
}
