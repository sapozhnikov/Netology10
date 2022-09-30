import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long under18count = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних: " + under18count);

        long conscriptsCount = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(p -> p.getSex() == Sex.MAN)
                .count();
        System.out.println("Призывников: " + conscriptsCount);

        List<Person> workingPpl = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.MAN && p.getAge() >= 18 && p.getAge() <= 65) ||
                        (p.getSex() == Sex.WOMAN && p.getAge() >= 18 && p.getAge() <= 60))
                .sorted((o1, o2) -> o1.getFamily().compareTo(o2.getFamily()))
                .toList();

        System.out.println("В отсортированном списке рабочих: " + workingPpl.size());
    }
}
