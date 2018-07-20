import model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume("uuid1");
        try {
            Class classResume = r.getClass();
            Method method = classResume.getMethod("toString");
            Object stringResume = method.invoke(r);
            System.out.println(stringResume);
        } catch  (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}