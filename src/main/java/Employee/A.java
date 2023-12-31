package Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) {
//        Function<Integer,Double> myFunction = i -> i*1.4 ;
//        Double val = myFunction.apply(100);
//        System.out.println(val);

//        List<Integer> data = Arrays.asList(10,20,30,40);
//        List<Integer>newData = data.stream().sorted().collect(Collectors.toList());
//        System.out.println(newData);


        List<Emply> data= Arrays.asList(
                new Emply("mike",5000),
                new Emply("adam",10000),
                new Emply("sohan",5000)
        );
        Map<Double, List<Emply>> groups = data.stream().collect(Collectors.groupingBy(Emply::getSalary));
        System.out.println(groups);

        for (Map.Entry<Double,List<Emply>> entry:groups.entrySet()) {
        double salary = entry.getKey();
        List<Emply> emplyList = entry.getValue();
            System.out.println("Emply with salary "+ salary + ":");
            for (Emply emply:emplyList) {
                System.out.println("\t"  + emply.getName());
            }
        }
    }

}
