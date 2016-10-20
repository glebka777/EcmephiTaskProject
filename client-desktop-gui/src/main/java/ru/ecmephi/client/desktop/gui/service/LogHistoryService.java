package ru.ecmephi.client.desktop.gui.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.val;
import ru.ecmephi.client.desktop.gui.ClientApp;
import ru.ecmephi.user.service.ws.endpoint.UserWebService;
import ru.ecmephi.user.service.ws.request.IdRequest;
import ru.ecmephi.user.service.ws.response.MessageResponse;

import javax.xml.ws.WebServiceException;

public class LogHistoryService extends Service<MessageResponse> {

    @Override
    protected Task<MessageResponse> createTask() {
        return new Task<MessageResponse>() {
            @Override
            protected MessageResponse call() {
                return getLog();
            }
        };
    }

    private MessageResponse getLog() throws WebServiceException {
        val id = ClientApp.getInstance().getCurrentUser().getId();
        val request = new IdRequest();
        request.setId(id);
        val userWebService = new UserWebService();
        val clientPort = userWebService.getUserWSPort();
        return clientPort.getLog(request);
    }

}
