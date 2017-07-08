package com.noisyle.demo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaDemo {
	private static List<Person> person_list;

	private static void before() {
		person_list = new ArrayList<Person>(1000000);
		for (int i = 0; i < 200; i++) {
			person_list.add(new Person("Person" + i, 15 + i % 10));
		}
	}

	private static void testLambda() {
		long t0 = System.nanoTime();
		@SuppressWarnings("unused")
		List<Adult> adult_list = person_list.stream().filter(s -> s.age > 18).map(p -> new Adult(p.name, p.age)).collect(Collectors.toList());
		long t1 = System.nanoTime();
		System.out.printf("serial: %.2fs\n", (t1 - t0) * 1e-9);
	}
	
	private static void testLambdaParallel() {
		long t0 = System.nanoTime();
		@SuppressWarnings("unused")
		List<Adult> adult_list = person_list.stream().filter(s -> s.age > 18).map(p -> new Adult(p.name, p.age)).parallel().collect(Collectors.toList());
		long t1 = System.nanoTime();
		System.out.printf("parallel %.2fs\n", (t1 - t0) * 1e-9);
	}
	
	public static void main(String[] args) {
		before();
		testLambda();
		before();
		testLambdaParallel();
	}
}


class Person {
	String name;
	int age = 0;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Adult extends Person {
	Adult(String name, int age) {
		super(name, age);
	}
}