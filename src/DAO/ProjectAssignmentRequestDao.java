package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class ProjectAssignmentRequestDao {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/construction_management_system_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Establishes a connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create (Insert) a project assignment request
    public boolean createProjectAssignmentRequest(ProjectAssignmentRequest request) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO project_assignment_requests (userID, projectID, status) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, request.getUserID());
                preparedStatement.setString(2, request.getProjectID());
                preparedStatement.setString(3, request.getStatus());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read (Retrieve) all project assignment requests
    public List<ProjectAssignmentRequest> getAllProjectAssignmentRequests() {
        List<ProjectAssignmentRequest> requests = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM project_assignment_requests";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProjectAssignmentRequest request = new ProjectAssignmentRequest();
                    request.setRequestID(resultSet.getInt("requestID"));
                    request.setUserID(resultSet.getString("userID"));
                    request.setProjectID(resultSet.getString("projectID"));
                    request.setStatus(resultSet.getString("status"));

                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    // Other CRUD methods...

    // Delete a project assignment request
    public boolean deleteProjectAssignmentRequest(int requestID) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM project_assignment_requests WHERE requestID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, requestID);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateStatus(int requestID, String status) {
    try (Connection connection = getConnection()) {
        String sql = "UPDATE project_assignment_requests SET status=? WHERE requestID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, requestID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public String updateRequest(Request requestobj)
    {
        try {
            Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pst =con.prepareStatement("update project_assignment_requests set  status='Approved' where requestID=?");
            pst.setInt(1, requestobj.getRequestID());
            int rowAffected = pst.executeUpdate();
            if(rowAffected>=1){
            return "Request Approved";
            }else
            {
            return "Request not Approved";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return "Server Error";
    }
    public String denyRequest(Request requestobj)
    {
        try {
            Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pst =con.prepareStatement("update project_assignment_requests set  status='Denied' where requestID=?");
            pst.setInt(1, requestobj.getRequestID());
            int rowAffected = pst.executeUpdate();
            if(rowAffected>=1){
            return "Request Denied";
            }else
            {
            return "Request not Denied";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return "Server Error";
    }

}
