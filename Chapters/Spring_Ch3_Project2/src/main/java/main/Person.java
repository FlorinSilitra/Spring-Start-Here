package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name = "Ella";
    //@Autowired first method (used in examples)
    private Parrot parrot;

    @Autowired // second method (frq. used)
    public Person(Parrot parrot) {
        this.parrot = parrot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }

    //@Autowired   no one use this
    public void setParrot(Parrot parrot) {
        this.parrot = parrot;
    }
}
