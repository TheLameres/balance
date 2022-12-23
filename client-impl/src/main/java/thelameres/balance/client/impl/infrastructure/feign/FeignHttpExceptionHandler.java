package thelameres.balance.client.impl.infrastructure.feign;

import feign.Response;

public interface FeignHttpExceptionHandler {
    Exception handle(Response response);
}
