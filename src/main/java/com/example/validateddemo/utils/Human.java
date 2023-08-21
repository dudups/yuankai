package com.example.validateddemo.utils;

import com.example.validateddemo.enums.HumanState;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Human {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor cstr = HumanState.class.getDeclaredConstructor(
                String.class, int.class
        );
        ReflectionFactory reflection =
                ReflectionFactory.getReflectionFactory();
        HumanState e =
                (HumanState) reflection.newConstructorAccessor(cstr).newInstance(new Object[]{"ANGRY", 3});
        System.out.printf("%s = %d\n", e.toString(), e.ordinal());
        Human human = new Human();
        human.sing(e);
    }

    public void sing(HumanState state)
    {
        switch (state)
        {
            case HAPPY:
                singHappySong();
                break;
            case SAD:
                singDirge();
                break;
            default:
              throw   new IllegalStateException("Invalid State: " + state);
        }
    }

    private void singHappySong()
    {
        System.out.println("When you're happy and you know it ...");
    }

    private void singDirge()
    {
        System.out.println("Don't cry for me Argentina, ...");
    }
}
