package ru.clevertec.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.clevertec.dto.DataDtoRequest;
import ru.clevertec.dto.DataDtoResponse;

import static org.assertj.core.api.Assertions.assertThat;

class ServerTest {

    private Server server;

    @BeforeEach
    void setUp() {
        server = new Server();
    }

    @ParameterizedTest
    @ValueSource(ints = {30, 40, 50})
    @DisplayName("Test process request method")
    void checkProcessRequestShouldReturnResponse(int value) {
        DataDtoRequest request = DataDtoRequest.builder()
                .value(value)
                .build();

        DataDtoResponse response = DataDtoResponse.builder()
                .value(100 - value)
                .build();

        DataDtoResponse actualResponse = server.processRequest(request);

        assertThat(actualResponse.getValue()).isEqualTo(response.getValue());
    }
}