package me.ecmephi.task.ui.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import lombok.val;
import me.ecmephi.task.common.endpoint.UserWebService;
import me.ecmephi.task.common.request.IdRequest;
import me.ecmephi.task.common.response.MessageResponse;
import me.ecmephi.task.ui.ClientFXApp;

import javax.xml.ws.WebServiceException;

public class LogHistoryService extends Service<MessageResponse> {

    @Override
    protected Task<MessageResponse> createTask() {
        return new Task<MessageResponse>() {
            @Override
            protected MessageResponse call() throws Exception {
                return getLog();
            }
        };
    }

    private MessageResponse getLog() throws WebServiceException {
        val id = ClientFXApp.getInstance().getCurrentUser().getId();
        val request = new IdRequest();
        request.setId(id);
        val clientPort = new UserWebService().getUserWSPort();
        return clientPort.getLog(request);
    }

}
