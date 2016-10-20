package ru.ecmephi.user.service.test;

import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogSimulator {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String USER_CREATED = "User has been created: ";
    private static final String USER_LOGGED_IN = "User has logged in";
    private StringBuilder log;

    public LogSimulator() {
        log = new StringBuilder();
    }

    void simulateLogon() {
        val date = formatter.format(new Date());
        log.append(date).append(" :: ").append(USER_LOGGED_IN).append("\n");
    }

    void simulateCreation(String username) {
        val description = USER_CREATED + username;
        val date = formatter.format(new Date());
        log.append(date).append(" :: ").append(description).append("\n");
    }

    void simulateRightsChange(String adminUsername, String newAccessLevel) {
        val description = "User's rights changed to " +
                newAccessLevel +
                " rights by '" +
                adminUsername +
                "'";
        val date = formatter.format(new Date());
        log.append(date).append(" :: ").append(description).append("\n");
    }

    String getLog() {
        return log.toString();
    }

}
