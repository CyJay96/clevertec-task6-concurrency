package ru.clevertec;

import ru.clevertec.dto.DataDtoResponse;
import ru.clevertec.service.Client;
import ru.clevertec.service.Server;

import java.util.List;

public class App {

    private static final int REQUEST_COUNT = 100;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        Client client = new Client(REQUEST_COUNT, THREAD_POOL_SIZE);

        List<DataDtoResponse> responses = client.processResponse(new Server());

        responses.forEach(System.out::println);
        System.out.println(responses.size());
    }
}