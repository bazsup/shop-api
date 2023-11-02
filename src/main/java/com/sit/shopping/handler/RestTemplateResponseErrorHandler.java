package com.sit.shopping.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import com.sit.shopping.exception.EntityNotFoundException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (httpResponse
                .getStatusCode()
                .is4xxClientError() ||
                httpResponse
                        .getStatusCode()
                        .is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse
                .getStatusCode()
                .is5xxServerError()) {
            // Handle SERVER_ERROR
            throw new HttpClientErrorException(httpResponse.getStatusCode());
        } else if (httpResponse
                .getStatusCode()
                .is4xxClientError()) {
            // Handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("A product could not be found");
            }
        }
    }
}