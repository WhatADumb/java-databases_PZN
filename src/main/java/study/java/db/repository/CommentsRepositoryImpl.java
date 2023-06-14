package study.java.db.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import study.java.db.ConnectionUtil;
import study.java.db.entity.Comments;

public class CommentsRepositoryImpl implements CommentsRepository{

    @Override
    public List<Comments> findAll() {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM comments";
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)){
                    List<Comments> comments = new ArrayList<>();
                    while (resultSet.next()) {
                        comments.add(new Comments(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("comment")));
                    }
                    return comments;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comments> findAllByEmail(String email) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM comments WHERE email = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, email);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    List<Comments> comments = new ArrayList<>();
                    while(resultSet.next()){
                        comments.add(new Comments(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("comment")));
                    }
                    return comments;
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comments findById(int id) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM comments WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        return new Comments(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("comment"));
                    }else{
                        return null;
                    }
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Comments comment) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString(1, comment.getEmail());
                preparedStatement.setString(2, comment.getComment());
                preparedStatement.executeUpdate();
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                        comment.setId(resultSet.getInt(1));
                    }
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}