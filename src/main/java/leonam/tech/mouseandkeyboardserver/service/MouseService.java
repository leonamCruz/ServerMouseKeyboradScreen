package leonam.tech.mouseandkeyboardserver.service;

import leonam.tech.mouseandkeyboardserver.model.Coordenadas;
import leonam.tech.mouseandkeyboardserver.model.TamanhoDaTela;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

@Service
@AllArgsConstructor
public class MouseService {
    private final Robot robot;

    public void movimentaMouse(Coordenadas coordenadas) {
        robot.mouseMove(coordenadas.getX(), coordenadas.getY());
    }

    public TamanhoDaTela pegaTamanhoDaTela() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension screenSize = toolkit.getScreenSize();

        int largura = screenSize.width;
        int altura = screenSize.height;
        var localizacao = devolveLocalizacao();
        return new TamanhoDaTela(altura,largura,localizacao.getX(),localizacao.getY());
    }

    public Coordenadas clicaBotaoEsquerdoDoMouse(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        return devolveLocalizacao();
    }

    public Coordenadas devolveLocalizacao(){
        var mouseInfo = MouseInfo.getPointerInfo();
        var localizacao = mouseInfo.getLocation();

        return new Coordenadas(localizacao.x, localizacao.y);
    }

    public Coordenadas clicaBotaoDireitoDoMouse(){

        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

        return devolveLocalizacao();
    }
    public void rolaPraBaixo(){
       robot.mouseWheel(3);
    }
    public void rolaPraCima(){
        robot.mouseWheel(-3);
    }
}
