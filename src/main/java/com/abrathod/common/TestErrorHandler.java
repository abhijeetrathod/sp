package com.abrathod.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class TestErrorHandler extends DefaultResponseErrorHandler {

	

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
        //conversion logic for decoding conversion
        ByteArrayInputStream arrayInputStream = (ByteArrayInputStream) response.getBody();
        Scanner scanner = new Scanner(arrayInputStream);
        scanner.useDelimiter("\\Z");
        String data = "";
        if (scanner.hasNext())
            data = scanner.next();
        System.out.println(data);
    }
    
}
