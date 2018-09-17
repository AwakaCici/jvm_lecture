package com.ssy.jvm.classloader;

import java.util.*;

public class SortedSalaries {
    public static void main(String[] args) {
        SortedSalaries instance = new SortedSalaries();
        Long[] salaries = new Long[]{10000L, 20000L, 40000L, 30000L, 30000L, 30000L, 40000L, 20000L, 50000L
                , 50000L, 50000L, 50000L, 60000L, 60000L, 60000L, 70000L, 80000L, 90000L, 100000L};
        ArrayList<Long> result = instance.sortSalary(19, Arrays.asList(salaries));
        System.out.println(result);
    }

    public ArrayList<Long> sortSalary(int num, List<Long> salaries) {
        if (num < 1 || num > (Math.pow(10, 5))) {
            System.out.println("invaild num");
            return null;
        }
        Map<Long, Integer> sortedValues = new HashMap<>();
        for (Long salary : salaries) {
            if (salary < 1 || salary > (Math.pow(10, 9))) {
                System.out.println("invaild salary");
                return null;
            } else {
                if (sortedValues.containsKey(salary)) {
                    sortedValues.put(salary, sortedValues.get(salary) + 1);
                } else {
                    sortedValues.put(salary, 1);
                }
            }
        }
        TreeMap<String, Long> resultMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num = Integer.valueOf(o1.substring(0, o1.indexOf("_")));
                int num2 = Integer.valueOf(o2.substring(0, o2.indexOf("_")));
                if (num2 - num > 0) {
                    return 1;
                } else if (num2 == num) {
                    Long salary1 = Long.valueOf(o1.substring(o1.indexOf("_") + 1));
                    Long salary2 = Long.valueOf(o2.substring(o2.indexOf("_") + 1));

                    return (int)(salary1 - salary2) == 0 ? (int)(salary1 - salary2) : 0;

                } else {
                    return -1;
                }
            }
        });

        for (Long salary : sortedValues.keySet()) {
            Integer numbers = sortedValues.get(salary);
            String key = numbers + "_" + salary;
            resultMap.put(key, salary);
        }

        ArrayList<Long> result = new ArrayList<>(num);

        for (Map.Entry<String, Long> entry : resultMap.entrySet()) {
            String numberstr = entry.getKey();
            Long value = entry.getValue();
            int number = Integer.valueOf(numberstr.substring(0, numberstr.indexOf("_")));
            for (int i = 0; i < number; i++) {
                result.add(value);
            }
        }


        return result;
    }


}
