package leonam.tech.mouseandkeyboardserver.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MouseWebSocketHandler mouseWebSocketHandler;

    public WebSocketConfig(MouseWebSocketHandler mouseWebSocketHandler) {
        this.mouseWebSocketHandler = mouseWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(mouseWebSocketHandler, "/ws").setAllowedOrigins("*");
    }
}
