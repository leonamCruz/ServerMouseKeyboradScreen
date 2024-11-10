package leonam.tech.mouseandkeyboardserver.configs;

import jakarta.annotation.PostConstruct;
import leonam.tech.mouseandkeyboardserver.service.GeradorQRCode;
import leonam.tech.mouseandkeyboardserver.service.Rede;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.SocketException;

@Configuration
public class ConfigQrCode {
    @Value("${server.port}")
    private int serverPort;

    @PostConstruct
    public void imprimeQrCodeComEnderecoParaCliente() throws SocketException {
        var endereco = String.format("ws://%s:%d/ws", Rede.enderecoWs(), serverPort);
        System.out.println(endereco);
        GeradorQRCode.gerarQRCode(endereco);
    }
}
