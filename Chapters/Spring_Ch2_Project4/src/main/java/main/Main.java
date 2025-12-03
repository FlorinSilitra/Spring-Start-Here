package main;

import config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args)
    {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot x = new Parrot();
        x.setName("Kiki");

        Supplier<Parrot> parrotSupplier = () -> x;

        context.registerBean("parrot1",
                Parrot.class, parrotSupplier);

        Parrot x2 = new Parrot();
        x2.setName("Lolo");

        Supplier<Parrot> parrotSupplier2 = () -> x2;

        context.registerBean("parrot2",
                Parrot.class,
                parrotSupplier2,
                bc -> bc.setPrimary(true));

        Parrot p = context.getBean(Parrot.class);
        System.out.println(p.getName());

        p = context.getBean("parrot1", Parrot.class);
        System.out.println(p.getName());
    }
}
