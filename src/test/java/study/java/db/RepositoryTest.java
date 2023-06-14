package study.java.db;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import study.java.db.entity.Comments;
import study.java.db.repository.CommentsRepository;
import study.java.db.repository.CommentsRepositoryImpl;

public class RepositoryTest {
    private CommentsRepository commentsRepository;

    @BeforeEach
    public void BeforeEach() {
        commentsRepository = new CommentsRepositoryImpl();
    }

    @Test
    public void testInsert() {
        Comments comments = new Comments(1, "fadhil@sample", "hi");
        commentsRepository.insert(comments);
        
        Assertions.assertNotNull(comments.getId());
        System.out.println(comments.getId());
    }

    @Test
    public void testFindById() {
        Comments comment = commentsRepository.findById(7456);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comments notFound = commentsRepository.findById(10000000);
        Assertions.assertNull(notFound);
    }

    @Test
    void testFindAll() {
      List<Comments> comments = commentsRepository.findAll();
      System.out.println(comments.size());
    }

    @Test
    void testFindByEmail() {
        List<Comments> comments = commentsRepository.findAllByEmail("salah@test.com");
        System.out.println(comments.size());
  }
}
