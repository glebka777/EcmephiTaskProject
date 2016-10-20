package ru.ecmephi.user.service;

import lombok.val;
import ru.ecmephi.user.service.dao.LogDAO;
import ru.ecmephi.user.service.entity.Activity;
import ru.ecmephi.user.service.entity.Record;
import ru.ecmephi.user.service.entity.User;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserLogger {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final String USER_CREATED = "User has been created: ";
    private static final String USER_LOGGED_IN = "User has logged in";

    @Inject
    private LogDAO logDAO;

    void markLogon(User user) {
        val date = new Date();
        val record = new Record();
        record.setUser(user);
        record.setDescription(USER_LOGGED_IN);
        record.setActivity(Activity.LOGON);
        record.setDate(date);
        logDAO.create(record);
    }

    void markCreation(User user) {
        val date = new Date();
        val username = user.getUsername();
        val description = USER_CREATED + username;
        val record = new Record();
        record.setUser(user);
        record.setDescription(description);
        record.setActivity(Activity.CREATION);
        record.setDate(date);
        logDAO.create(record);
    }

    void markRightsChange(User user, User admin, String newAccessLevel) {
        val date = new Date();
        val adminUsername = admin.getUsername();
        val description = "User's rights changed to " +
                newAccessLevel +
                " rights by '" +
                adminUsername +
                "'";
        val record = new Record();
        record.setUser(user);
        record.setDescription(description);
        record.setActivity(Activity.ACCESS_LEVEL_CHANGE);
        record.setDate(date);
        logDAO.create(record);
    }

    void clearLog(User user) {
        user.getLog().forEach(record -> logDAO.remove(record.getId()));
    }

    String getFullLog(User user) {
        val log = user.getLog();
        val stringBuilder = new StringBuilder();
        log.forEach(record -> stringBuilder.append(formatSingleRecord(record)));
        return stringBuilder.toString();
    }

    private String formatSingleRecord(Record record) {
        val date = formatter.format(record.getDate());
        val description = record.getDescription();
        return date +
                " :: " +
                description +
                "\n";
    }

}
