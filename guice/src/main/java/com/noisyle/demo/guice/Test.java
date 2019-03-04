package com.noisyle.demo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

interface IGreeting {
    void greeting();
}

class Dog implements IGreeting {
    @Override
    public void greeting() {
        System.out.println("汪汪");
    }
}

class Cat implements IGreeting {
    @Override
    public void greeting() {
        System.out.println("喵喵");
    }
}

class PetModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IGreeting.class).to(Dog.class);
    }
}

public class Test {
    @Inject
    private IGreeting pet;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PetModule());
        Test test = injector.getInstance(Test.class);
        test.pet.greeting();
    }
}
