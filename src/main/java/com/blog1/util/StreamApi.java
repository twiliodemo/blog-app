package com.blog1.util;

import Employee.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {

    public static void main(String[] args) {
     //   List<Integer> data = Arrays.asList(10,20,30,4,44,55,5,33);
       // List<Integer> newData = data.stream().filter(x->x>20).collect(Collectors.toList());
        //System.out.println(newData);

//      List<String> data =  Arrays.asList("mike","mithun","smith","udit","pankaj");
//      List<String> newDta = data.stream().filter(x->x.startsWith("m")).collect(Collectors.toList());
//      System.out.println(newDta);

//
         Employee emp1 = new Employee();
         emp1.setName("mike");
         emp1.setCity("Bengluru");
         emp1.setSalary(10000);

        Employee emp2 = new Employee();
        emp2.setName("mukesh");
        emp2.setCity("pune");
        emp2.setSalary(1000);

        Employee emp3 = new Employee();
        emp3.setName("smith");
        emp3.setCity("chennai");
        emp3.setSalary(5000);

        Employee emp4 = new Employee();
        emp4.setName("adam");
        emp4.setCity("Bengluru");
        emp4.setSalary(10000);

        List<Employee> data = Arrays.asList(emp1,emp2,emp3,emp4);
        List<Employee> newData = data.stream().filter(e->e.getSalary()==10000).collect(Collectors.toList());

        for (Employee employee:newData) {
            System.out.println(employee.getName());
            System.out.println(employee.getCity());
            System.out.println(employee.getSalary());
        }
    }


}
