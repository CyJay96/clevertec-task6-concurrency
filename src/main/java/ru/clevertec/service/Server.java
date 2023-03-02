package ru.clevertec.service;

import ru.clevertec.dto.DataDtoRequest;
import ru.clevertec.dto.DataDtoResponse;

public class Server {

    public DataDtoResponse processRequest(DataDtoRequest request) {
        delay();
        return DataDtoResponse.builder()
                .value(100 - request.getValue())
                .build();
    }

    private void delay() {
        int time = (int) (Math.random() * 2000);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
