package ru.clevertec.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.DataDtoRequest;
import ru.clevertec.dto.DataDtoResponse;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientTest {

    private static final int REQUEST_COUNT = 100;
    private static final int THREAD_POOL_SIZE = 10;

    private List<DataDtoResponse> responses;

    @Mock
    private Server server;

    private Client client;

    @BeforeEach
    void setUp() {
        server = Mockito.mock(Server.class);
        client = new Client(REQUEST_COUNT, THREAD_POOL_SIZE);

        responses = new ArrayList<>();
        for (int i = 99; i >= 0; --i) {
            responses.add(DataDtoResponse.builder()
                    .value(i)
                    .build());
        }
    }

    @Test
    @DisplayName("Test process response method")
    void checkProcessResponseShouldReturnResponseList() {
        DataDtoRequest request = DataDtoRequest.builder().value(1).build();
        DataDtoResponse response = DataDtoResponse.builder().value(99).build();

        doReturn(response).when(server).processRequest(request);

        List<DataDtoResponse> actualResponses = client.processResponse(server);

        verify(server, times(REQUEST_COUNT)).processRequest(any());

        assertThat(actualResponses).hasSize(REQUEST_COUNT);
    }
}