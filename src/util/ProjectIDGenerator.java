package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectIDGenerator {

    private static int counter = 0;

    public static String generateProjectID(String projectName, String location) {
        // Convert project name and location to uppercase and remove spaces
        String formattedName = projectName.toUpperCase().replaceAll("\\s", "");
        String formattedLocation = location.toUpperCase().replaceAll("\\s", "");

        // Get the current year
        String year = new SimpleDateFormat("yy").format(new Date());

        // Generate an auto-incremented number
        String autoIncrementedNumber = String.format("%04d", getNextCounter());

        // Combine the elements to create the projectID
        return formattedName.substring(0, Math.min(formattedName.length(), 3))
                + formattedLocation.substring(0, Math.min(formattedLocation.length(), 3))
                + year + autoIncrementedNumber;
    }

    private static synchronized int getNextCounter() {
        return counter++;
    }
}
