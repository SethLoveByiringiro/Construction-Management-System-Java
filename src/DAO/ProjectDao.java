package DAO;

import model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    private String dbUrl;
    private String username;
    private String password;

    // Constructor to receive the database connection details
    // Constructor to receive the database connection
    public ProjectDao(String dbUrl, String username, String password) {
        this.dbUrl = "jdbc:mysql://localhost:3306/construction_management_system_db";
        this.username = "root";
        this.password = "";
    }

    public boolean addProject(Project project) {
        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            String sql = "INSERT INTO projects (projectID, name, description, location, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set parameters
                preparedStatement.setString(1, project.getProjectID());
                preparedStatement.setString(2, project.getName());
                preparedStatement.setString(3, project.getDescription());
                preparedStatement.setString(4, project.getLocation());

                // Convert java.util.Date to java.sql.Date
                java.sql.Date startDateSql = new java.sql.Date(project.getStartDate().getTime());
                java.sql.Date endDateSql = new java.sql.Date(project.getEndDate().getTime());

                preparedStatement.setDate(5, startDateSql);
                preparedStatement.setDate(6, endDateSql);

                preparedStatement.executeUpdate();
                return true; // Operation was successful

            } catch (SQLException e) {
                e.printStackTrace();
                return false; // Operation failed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Operation failed
        }
    }


    public void updateProject(Project project) {
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
        String sql = "UPDATE projects SET name=?, description=?, location=?, startDate=?, endDate=? WHERE projectID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setString(3, project.getLocation());

            java.sql.Date startDateSql = new java.sql.Date(project.getStartDate().getTime());
            java.sql.Date endDateSql = new java.sql.Date(project.getEndDate().getTime());

            preparedStatement.setDate(4, startDateSql);
            preparedStatement.setDate(5, endDateSql);

            preparedStatement.setString(6, project.getProjectID());

            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public boolean deleteProject(String projectID) {
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
        String sql = "DELETE FROM projects WHERE projectID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, projectID);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}




    public List<Project> getAllProjects() {
    List<Project> projects = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
        String sql = "SELECT * FROM projects";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectID(resultSet.getString("projectID"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setLocation(resultSet.getString("location"));
                project.setStartDate(resultSet.getDate("startDate"));
                project.setEndDate(resultSet.getDate("endDate"));

                projects.add(project);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return projects;
}
// Add this method to your ProjectDao class
public Project getProjectByID(String projectID) {
    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
        String sql = "SELECT * FROM projects WHERE projectID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, projectID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Project project = new Project();
                    project.setProjectID(resultSet.getString("projectID"));
                    project.setName(resultSet.getString("name"));
                    project.setDescription(resultSet.getString("description"));
                    project.setLocation(resultSet.getString("location"));
                    project.setStartDate(resultSet.getDate("startDate"));
                    project.setEndDate(resultSet.getDate("endDate"));
                    return project;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
