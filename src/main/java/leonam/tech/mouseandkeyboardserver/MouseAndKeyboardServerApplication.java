package leonam.tech.mouseandkeyboardserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;

@SpringBootApplication
public class MouseAndKeyboardServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MouseAndKeyboardServerApplication.class, args);
    }


    @Bean
    public Robot iniciaRobot() throws AWTException {
        if (GraphicsEnvironment.isHeadless()) {
            throw new AWTException("Headless mode");
        }
        return new Robot();
    }

}
