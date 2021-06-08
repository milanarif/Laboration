package dao;

import entity.Post;

//LOTS TO DO

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PostDao {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public List<Post> getAllPosts() {
        EntityManager em = emf.createEntityManager();
        List<Post> posts = em
                .createQuery("Select p from Post p", Post.class)
                .getResultList();
        em.close();

        return posts;
    }


    public Post getPost(Integer id) {
        EntityManager em = emf.createEntityManager();
        Post post = em.find(Post.class, id);
        em.close();

        return post;
    }

    public void addPost(Post post) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();
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
