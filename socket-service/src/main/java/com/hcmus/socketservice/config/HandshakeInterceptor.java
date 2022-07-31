package com.hcmus.socketservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Slf4j
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    /**
     * Before websocket handshake
     * You can put some data into {@code attributes} here, and get it in WebSocketHandler's session
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("HandshakeInterceptor: beforeHandshake");
        log.info("Attributes: " + attributes.toString());
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    /**
     * After websocket handshake
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info("HandshakeInterceptor: afterHandshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
