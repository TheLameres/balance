package thelameres.balance.client.impl.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import thelameres.balance.client.impl.infrastructure.feign.FeignHttpExceptionHandler;
import thelameres.balance.server.api.data.dtos.ErrorDto;
import thelameres.balance.server.api.exceptions.BalanceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class BalanceControllerClientExceptionHandler implements FeignHttpExceptionHandler {
    private final ObjectMapper objectMapper;

    public BalanceControllerClientExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public Exception handle(Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());
        InputStream inputStream = response.body().asInputStream();
        StringBuilder out = getBody(inputStream);
        String body = out.toString();
        if (HttpStatus.NOT_FOUND.equals(httpStatus)) {
            ErrorDto errorDto = objectMapper.readValue(body, ErrorDto.class);
            return new BalanceNotFoundException(errorDto.id(), errorDto.message());
        }
        return new RuntimeException(body);
    }

    private static StringBuilder getBody(InputStream inputStream) throws IOException {
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
            out.append(buffer, 0, numRead);
        }
        return out;
    }
}
