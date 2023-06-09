package repository;

import models.Pagination;
import models.dto.CreateUserDto;
import models.dto.UpdateUserDto;
import models.dto.UserFilter;
import repository.interfaces.UserRepositoryInterface;
import service.ConnectionUtil;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserRepositoryInterface {

    public User insert(CreateUserDto user) throws SQLException {
        String sql = "INSERT into users(FirstName, LastName, email,username, salt, saltedHash) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getUsername());
        statement.setString(5, user.getSalt());
        statement.setString(6, user.getSaltedPassword());
        statement.executeUpdate();

        return UserRepository.getByUsername(user.getUsername());
    }
    public static User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setString(1, username);
             ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String saltedHash = resultSet.getString("saltedHash");
                String salt = resultSet.getString("salt");
                return new User(username, saltedHash, salt);
            } else {
                return null;
            }
        }
    }

    public static List<User> getByFilter(UserFilter filters, Pagination pagination) throws SQLException{
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users WHERE 1=1";
        ArrayList<Object> params = new ArrayList<Object>();

        sql += filters.getFilterQuery();
        params.addAll(filters.getFilterParams());
        sql += pagination.getSQLQuery();
        params.addAll(pagination.getSQLParams());
        /// other filter options

        Connection conn = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for(int i = 0; i < params.size(); i++){
            preparedStatement.setObject(i+1, params.get(i));
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            users.add(
                    new User(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }

        return users;
    }
    public static boolean checkEmail(UpdateUserDto user) throws SQLException {
        String checkEmail = "SELECT * FROM users WHERE email = ?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(checkEmail);
        statement.setString(1, user.getEmail());
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        return false;
    }
    public static User update(UpdateUserDto user) throws SQLException {
        if (checkEmail(user)) {
            String sql = "UPDATE users SET salt = ?, saltedHash = ? WHERE email = ?";
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, user.getSalt());
            preparedStatement.setObject(2, user.getSaltedPassword());
            preparedStatement.setObject(3, user.getEmail());
            preparedStatement.executeUpdate();
        }
        return null;
    }
}
