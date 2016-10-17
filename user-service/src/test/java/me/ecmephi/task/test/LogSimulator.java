package me.ecmephi.task.test;

import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogSimulator {

    private SimpleDateFormat formatter;
    private StringBuilder log;

    public LogSimulator() {
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        log = new StringBuilder();
    }

    void simulateLogon() {
        val description = "User has logged in";
        val date = formatter.format(new Date());
        log.append(date).append(" :: ").append(description).append("\n");
    }

    void simulateCreation(String username) {
        val description = "User '" + username + "' has been created";
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
