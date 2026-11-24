package com.bits.leave;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class LeaveServiceApp {
    public static void main(String[] args) throws Exception {
        String env = System.getenv().getOrDefault("APP_ENV", "local");
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            byte[] body = ("Employee Leave Service running in " + env + " environment\n")
                    .getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();
        System.out.println("Leave Service started in " + env + " on port 8080");
    }
}
