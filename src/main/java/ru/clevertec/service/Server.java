package ru.clevertec.service;

import ru.clevertec.dto.DataDtoRequest;
import ru.clevertec.dto.DataDtoResponse;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private final Lock lock = new ReentrantLock();

    public DataDtoResponse processRequest(DataDtoRequest request) {
        delay();

        int responseValue;
        try {
            lock.lock();
            responseValue = 100 - request.getValue();
        } finally {
            lock.unlock();
        }

        return DataDtoResponse.builder()
                .value(responseValue)
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
