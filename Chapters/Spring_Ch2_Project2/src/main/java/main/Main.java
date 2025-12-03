package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args)
    {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        /// Parrot p = context.getBean(Parrot.class);
        // Exception NoUniqueBeanDefinitionException

        Parrot p = context.getBean("Koko", Parrot.class);
        System.out.println(p.getName());

        // @Primary annotation = Spring’s default choice
        p = context.getBean(Parrot.class);
        System.out.println(p.getName());

        p = context.getBean("parrot2", Parrot.class);
        System.out.println(p.getName());

        p = context.getBean("parrot3", Parrot.class);
        System.out.println(p.getName());
    }
}
