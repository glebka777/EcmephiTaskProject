package me.ecmephi.task.service;

import lombok.val;
import me.ecmephi.task.service.dao.LogDAO;
import me.ecmephi.task.service.entity.Activity;
import me.ecmephi.task.service.entity.Record;
import me.ecmephi.task.service.entity.User;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserLogger {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Inject
    LogDAO logDAO;

    void markLogon(User user) {
        val date = new Date();
        val description = "User has logged in";
        val record = new Record(user, description, Activity.LOGON, date);
        logDAO.create(record);
    }

    void markCreation(User user) {
        val date = new Date();
        val description = "User '" + user.getUsername() + "' has been created";
        val record = new Record(user, description, Activity.CREATION, date);
        logDAO.create(record);
    }

    void markRightsChange(User user, User admin, String newAccessLevel) {
        val date = new Date();
        val description = "User's rights changed to " +
                newAccessLevel +
                " rights by '" +
                admin.getUsername() +
                "'";
        val record = new Record(user, description, Activity.ACCESS_LEVEL_CHANGE, date);
        logDAO.create(record);
    }

    void clearLog(User user) {
        user.getLog().forEach(record->logDAO.remove(record.getId()));
    }

    String getFullLog(User user) {
        val log = user.getLog();
        val stringBuilder = new StringBuilder();
        log.forEach(record->stringBuilder.append(formatSingleRecord(record)));
        return stringBuilder.toString();
    }

    private String formatSingleRecord(Record record) {
        val date = formatter.format(record.getDate());
        return date +
                " :: " +
                record.getDescription() +
                "\n";
    }

}
