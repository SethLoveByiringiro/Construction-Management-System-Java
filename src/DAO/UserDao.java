package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDao {
    private String dbUrl;
    private String username;
    private String password;

    // Constructor to receive the database connection
    public UserDao(String dbUrl, String username, String password) {
        this.dbUrl = "jdbc:mysql://localhost:3306/construction_management_system_db";
        this.username = "root";
        this.password = "";
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }

    // Insert a new user into the database
    public boolean addUser(User user) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO users (userID, nationalID, fullNames, phoneNumber, position, password, confirmPassword, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, user.getUserID());
                preparedStatement.setString(2, user.getNationalID());
                preparedStatement.setString(3, user.getFullNames());
                preparedStatement.setString(4, user.getPhoneNumber());
                preparedStatement.setString(5, user.getPosition()); 
                preparedStatement.setString(6, user.getPassword());
                preparedStatement.setString(7, user.getConfirmPassword());
                preparedStatement.setString(8, user.getRole());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a user by ID
    
    public User getUserByID(String userID) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User();
                        user.setUserID(resultSet.getString("userID"));
                        user.setNationalID(resultSet.getString("nationalID"));
                        user.setFullNames(resultSet.getString("fullNames"));
                        user.setPhoneNumber(resultSet.getString("phoneNumber"));
                        user.setPosition(resultSet.getString("position"));
                        user.setPassword(resultSet.getString("password"));
                        user.setConfirmPassword(resultSet.getString("confirmPassword"));
                        user.setRole(resultSet.getString("role"));
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    


    // Retrieve all users from the database
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userList.add(extractUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Update a user's information in the database
    public boolean updateUser(User user) {
    try (Connection connection = getConnection()) {
        String query = "UPDATE users SET nationalID = ?, fullNames = ?, phoneNumber = ?, position = ?, password = ?, confirmPassword = ?, role = ? WHERE userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNationalID());
            preparedStatement.setString(2, user.getFullNames());
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getPosition());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getConfirmPassword());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setString(8, user.getUserID());

            System.out.println("Executing query: " + preparedStatement.toString());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    // Delete a user from the database
    public boolean deleteUser(String userID) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM users WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userID);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User getUserByCredentials(String userID, String password) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users WHERE userID = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userID);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return extractUserFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
}
   

    // Helper method to extract a user from the ResultSet
    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
    User user = new User();
    user.setUserID(resultSet.getString("userID"));
    user.setNationalID(resultSet.getString("nationalID"));
    user.setFullNames(resultSet.getString("fullNames"));
    user.setPhoneNumber(resultSet.getString("phoneNumber"));
    user.setPosition(resultSet.getString("position")); // Corrected line
    user.setPassword(resultSet.getString("password"));
    user.setConfirmPassword(resultSet.getString("confirmPassword"));
    user.setRole(resultSet.getString("role"));
    return user;
}
    

}
