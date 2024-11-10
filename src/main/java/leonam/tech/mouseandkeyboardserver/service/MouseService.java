package leonam.tech.mouseandkeyboardserver.service;

import leonam.tech.mouseandkeyboardserver.model.Coordenadas;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.Robot;
import java.awt.event.InputEvent;

@Service
@AllArgsConstructor
public class MouseService {
    private final Robot robot;

    public void movimentaMouse(Coordenadas coordenadas) {
        robot.mouseMove(coordenadas.getX(), coordenadas.getY());
    }

    public void clicaBotaoEsquerdoDoMouse(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void clicaBotaoDireitoDoMouse(){
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    }
    public void rolaPraBaixo(){
       robot.mouseWheel(3);
    }
    public void rolaPraCima(){
        robot.mouseWheel(-3);
    }
}
