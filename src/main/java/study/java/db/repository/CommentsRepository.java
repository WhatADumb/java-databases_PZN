package study.java.db.repository;

import java.util.List;

import study.java.db.entity.Comments;

public interface CommentsRepository {
    void insert(Comments comment);
    Comments findById(int id);
    List<Comments> findAll();
    List<Comments> findAllByEmail(String email);
}
