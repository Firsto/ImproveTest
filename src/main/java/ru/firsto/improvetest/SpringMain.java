package ru.firsto.improvetest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.firsto.improvetest.web.ProductRestController;

import java.util.Arrays;

/**
 * User: razor
 * Date: 26.12.15
 */
public class SpringMain {
    public static void main(String[] args) {
        // Для проверки тут можно подёргать контроллер
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));

            ProductRestController mainController = appCtx.getBean(ProductRestController.class);
        }
    }
}
