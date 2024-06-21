
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author Love Byiringiro
 */
public class RequestDao {
    

    
        String dbUrl = "jdbc:mysql://localhost:3306/construction_management_system_db";
        String username = "root";
        String password = "";
        
       public String requestInput(Request obj) {
    try {
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement pst = con.prepareStatement("INSERT INTO project_assignment_requests (userID, projectID, status) VALUES (?, ?, ?)");

        pst.setString(1, obj.getUserID()); 
        pst.setString(2, obj.getProjectID());
        pst.setString(3, obj.getStatus()); 

        int rowAffected = pst.executeUpdate();
        if (rowAffected >= 1) {
            return "Request Registered";
        } else {
            return "Request not Registered";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "Server Error!";
    }
}



        public ResultSet projectList (Project rentobj){
   
        try {
              Connection con =DriverManager.getConnection(dbUrl, username, password);
           PreparedStatement pst =con.prepareStatement("select * from projects ");
           return pst.executeQuery();
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    
    }
        public List<ProjectAssignmentRequest> getAllProjectAssignmentRequests() {
    List<ProjectAssignmentRequest> requests = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
        String sql = "SELECT requestID, userID, projectID, status FROM project_assignment_requests";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
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
        public boolean updateRequestStatusToApproved(int requestID) {
    try {
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement pst = con.prepareStatement("UPDATE project_assignment_requests SET status = 'Approved' WHERE requestID = ?");
        pst.setInt(1, requestID);
        int rowsAffected = pst.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
}
        public boolean updateRequestStatusToDenied(int requestID) {
    String sql = "UPDATE project_assignment_requests SET status = 'Denied' WHERE requestID = ?";
    try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, requestID);
        int rowsUpdated = pstmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
        public String getUserStatus(String userID) {
    String status = null;
    String query = "SELECT status FROM project_assignment_requests WHERE userID = ?";
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password);
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, userID);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                status = resultSet.getString("status");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return status;
}





    
    
}
