import model.Resume;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume("uuid1");
        Class classResume = r.getClass();
        Field field = classResume.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);

        // TODO : invoke r.toString via reflection
        try {
            Method method = classResume.getMethod("toString");
            Object stringResume = method.invoke(classResume);
            System.out.println(stringResume);
        } catch  (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}