package leonam.tech.mouseandkeyboardserver.configs;

import leonam.tech.mouseandkeyboardserver.socket.SocketMouse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SocketMouse socketMouse;

    public WebSocketConfig(SocketMouse socketMouse) {
        this.socketMouse = socketMouse;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketMouse, "/ws").setAllowedOrigins("*");
    }
}
