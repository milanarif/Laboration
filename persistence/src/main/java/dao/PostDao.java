package dao;

import entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PostDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public List<Post> getAllPosts() {
        System.out.println("GETTING ALL POSTS!");
        EntityManager em = emf.createEntityManager();
        System.out.println("EM CREATED");
        List posts = em
                .createNativeQuery("Select p from Post p", Post.class)
                .getResultList();
        System.out.println("PRE-CLOSE");
        em.close();

        System.out.println("Got the posts");

        return posts;
    }


    public Post getPost(Integer id) {
        EntityManager em = emf.createEntityManager();
        Post post = em.find(Post.class, id);
        em.close();

        return post;
    }

    public void addPost(Post post) {
        System.out.println("Adding post");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();
        System.out.println("Added post!");
    }

    public void changeText(Integer id, String text) {
        EntityManager em = emf.createEntityManager();

        Post targetPost = em.find(Post.class, id);

        em.getTransaction().begin();
        targetPost.setText(text);
        em.getTransaction().commit();
        em.close();
    }

    public void removePost(Integer id) {
        EntityManager em = emf.createEntityManager();

        Post targetPost = em.find(Post.class, id);
        em.getTransaction().begin();
        em.remove(targetPost);
        em.getTransaction().commit();
        em.close();
    }
}
