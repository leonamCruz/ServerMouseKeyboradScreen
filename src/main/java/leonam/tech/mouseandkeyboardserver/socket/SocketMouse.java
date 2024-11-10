package leonam.tech.mouseandkeyboardserver.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import leonam.tech.mouseandkeyboardserver.model.Coordenadas;
import leonam.tech.mouseandkeyboardserver.service.MouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SocketMouse extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final MouseService mouseService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage(objetoParaJsonStringPuro(mouseService.pegaTamanhoDaTela())));
        System.out.println("Conexão WebSocket estabelecida com " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        System.out.println("Mensagem recebida: " + payload);

        switch (retornaComando(payload)) {
            case "/moveMouse":
                moveMouse(payload, session);
                break;
            case "/cliqueEsquerda":
                cliqueEsquerda(session);
                break;
            case "/cliqueDireita":
                cliqueDireita(session);
                break;
            case "/puxaCoordenadas":
                puxaCoordenadas(session);

        }

    }

    private void puxaCoordenadas(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage(objetoParaJsonStringPuro(mouseService.devolveLocalizacao())));
    }

    private void cliqueDireita(WebSocketSession session) throws IOException {
        var coordenadas = mouseService.clicaBotaoDireitoDoMouse();
        session.sendMessage(new TextMessage(objetoParaJsonStringPuro(coordenadas)));
    }


    private void cliqueEsquerda(WebSocketSession session) throws IOException {
        var coordenadas = mouseService.clicaBotaoEsquerdoDoMouse();
        session.sendMessage(new TextMessage(objetoParaJsonStringPuro(coordenadas)));
    }

    private void moveMouse(String queVeioDoCliente, WebSocketSession session) throws IOException {
        String jsonData = queVeioDoCliente.substring("/moveMouse".length()).trim();

        Coordenadas coordenadas = objectMapper.readValue(jsonData, Coordenadas.class);
        mouseService.movimentaMouse(coordenadas);

        session.sendMessage(new TextMessage(jsonData));
    }

    public String objetoParaJsonStringPuro(Object object) throws IOException {
        return new ObjectMapper().writeValueAsString(object);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Conexão WebSocket fechada com " + session.getId());
    }

    private String retornaComando(String queVeioDoCliente) {
        if (queVeioDoCliente.isEmpty()) return "";

        String[] parts = queVeioDoCliente.trim().split("\\s+", 2);
        return parts.length > 0 ? parts[0] : "";
    }
}
