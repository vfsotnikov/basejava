import model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume>  collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        Iterator<Resume> itResumes = collection.iterator();
        while (itResumes.hasNext()){
            Resume resume = itResumes.next();
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(),UUID_1)){
                itResumes.remove();
            }
        }
        System.out.println(collection.toString());

        Map<String,Resume> mapResumes = new HashMap<>();
        mapResumes.put(UUID_1,RESUME_1);
        mapResumes.put(UUID_2,RESUME_2);
        mapResumes.put(UUID_3,RESUME_3);

        // Bad !!!
//        for (String uuid: mapResumes.keySet()){
//            System.out.println(mapResumes.get(uuid));
//        }


        for (Map.Entry<String,Resume> itResumes2  : mapResumes.entrySet()){
            System.out.println(itResumes2.getValue());
        }
        List list = new LinkedList();

//        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
//        resumes.remove(1);
//        System.out.println(resumes);

    }
}
