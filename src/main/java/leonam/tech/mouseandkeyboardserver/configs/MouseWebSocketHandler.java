package leonam.tech.mouseandkeyboardserver.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import leonam.tech.mouseandkeyboardserver.model.Coordenadas;
import leonam.tech.mouseandkeyboardserver.service.MouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MouseWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final MouseService mouseService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Conexão WebSocket estabelecida com " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Mensagem recebida: " + payload);

        if (payload.startsWith("/moveMouse")) {
            String jsonData = payload.substring("/moveMouse".length()).trim();

            Coordenadas coordenadas = objectMapper.readValue(jsonData, Coordenadas.class);
            mouseService.movimentaMouse(coordenadas);

            ObjectNode responseJson = objectMapper.createObjectNode();
            responseJson.put("x", coordenadas.getX());
            responseJson.put("y", coordenadas.getY());

            String response = responseJson.toString();

            session.sendMessage(new TextMessage(response));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Conexão WebSocket fechada com " + session.getId());
    }
}
