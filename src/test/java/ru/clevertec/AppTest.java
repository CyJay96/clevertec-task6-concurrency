package ru.clevertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.DataDtoResponse;
import ru.clevertec.service.Client;
import ru.clevertec.service.Server;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    private static final int REQUEST_COUNT = 100;
    private static final int THREAD_POOL_SIZE = 10;

    private Client client;
    private Server server;

    private List<DataDtoResponse> responses;

    @BeforeEach
    void setUp() {
        client = new Client(REQUEST_COUNT, THREAD_POOL_SIZE);
        server = new Server();

        responses = new ArrayList<>();
        for (int i = 99; i >= 0; --i) {
            responses.add(DataDtoResponse.builder()
                    .value(i)
                    .build());
        }
    }

    @Test
    void checkAppFunctionalityShouldReturnResponseList() {
        List<DataDtoResponse> actualResponses = client.processResponse(server);
        assertThat(actualResponses).hasSize(REQUEST_COUNT);
    }
}