package ru.clevertec.service;

import ru.clevertec.dto.DataDtoRequest;
import ru.clevertec.dto.DataDtoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

    private final ExecutorService executor;

    private List<DataDtoRequest> requests;
    private List<Future<DataDtoResponse>> responses;

    public Client(int requestCount, int threadPoolSize) {
        executor = Executors.newFixedThreadPool(threadPoolSize);

        requests = new ArrayList<>(requestCount);
        responses = new ArrayList<>();

        for (int i = 0; i < requestCount; ++i) {
            DataDtoRequest request = DataDtoRequest.builder()
                    .value(i + 1)
                    .build();
            requests.add(request);
        }
    }

    public List<DataDtoResponse> processResponse(Server server) {
        requests.forEach(request -> sendRequest(server, request));

        executor.shutdown();

        return responses.stream()
                .map(responseFuture -> {
                    try {
                        return responseFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    private void sendRequest(Server server, DataDtoRequest request) {
        Future<DataDtoResponse> responseFuture = executor.submit(
                () -> server.processRequest(request)
        );
        responses.add(responseFuture);
    }
}
