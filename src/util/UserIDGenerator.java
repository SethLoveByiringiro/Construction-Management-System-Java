
package util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class UserIDGenerator {

    private static int counter = 0;

    public static String generateUserID(String fullNames) {
        // Extract initials from full names
        String initials = getInitialsFromFullNames(fullNames);

        // Get the current year
        String year = new SimpleDateFormat("yy").format(new Date());

        // Generate an auto-incremented number
        String autoIncrementedNumber = String.format("%04d", getNextCounter());

        // Combine the elements to create the userID
        return initials + year + autoIncrementedNumber;
    }

    private static String getInitialsFromFullNames(String fullNames) {
        StringBuilder initials = new StringBuilder();

        // Split the full names into parts
        String[] nameParts = fullNames.split("\\s+");

        // Take the first character of each part as initials
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }

        return initials.toString().toUpperCase();
    }

    private static synchronized int getNextCounter() {
        return counter++;
    }
}
