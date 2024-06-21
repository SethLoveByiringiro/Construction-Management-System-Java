package util;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ValidationUtils {

    public static boolean validateFields(String nationalID, String fullNames, String phoneNumber, String password, String confirmPassword, JComboBox<String> roleComboBox, JComboBox<String> positionComboBox) {
        if (nationalID.isEmpty() || nationalID.length() != 16) {
            JOptionPane.showMessageDialog(null, "National ID must be 16 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (fullNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Full Names are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() != 10) {
            JOptionPane.showMessageDialog(null, "Phone Number must be 10 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Both Password and Confirm Password are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate Role and Position (ComboBoxes)
        String selectedRole = roleComboBox.getSelectedItem().toString();
        String selectedPosition = positionComboBox.getSelectedItem().toString();

        if ("Select Role".equals(selectedRole) || "Select Position".equals(selectedPosition)) {
            JOptionPane.showMessageDialog(null, "Please select a valid Role and Position.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    public static String determineRole(String selectedPosition, String selectedRole) {
    // Determine the role based on the selected position and role
    if (selectedPosition.isEmpty() || selectedRole.isEmpty()) {
        // Default role if no valid position or role is selected
        return "DefaultRole";
    } else if (isEligibleForAdmin(selectedPosition, selectedRole)) {
        // Grant Admin role only if the selected position is eligible
        return "Admin";
    } else {
        // All other positions get a regular role
        return "User";
    }
}

   public static boolean isEligibleForAdmin(String position, String selectedRole) {
    // Check if eligible for Admin
    return (position.trim().equalsIgnoreCase("Executives or Owners") || position.trim().equalsIgnoreCase("Human Resources (HR)")) && selectedRole.equals("Admin");
}



   

    public static boolean validateLoginFields(String userID, String password) {
        // Validate UserID
        if (userID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "UserID is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate Password
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;  // All validations passed
    }


}
