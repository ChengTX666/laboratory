package nefu.laboratory.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        String weeks1="1,2,3,4";
        String weeks2="7,8";
        String request="4,5,6";
        String[] split = weeks1.split(",");
        Stream<String> stream = Arrays.stream(weeks1.split(","));

        Set<String> collect = Arrays.stream(request.split(",")).collect(Collectors.toSet());
        Set<String> collect1 = Arrays.stream(weeks1.split(",")).collect(Collectors.toSet());
        System.out.println(Collections.disjoint(collect, collect1));

    }
}
