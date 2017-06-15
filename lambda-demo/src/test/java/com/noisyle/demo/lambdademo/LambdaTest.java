package com.noisyle.demo.lambdademo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class LambdaTest {
	private List<Person> person_list;

	@Before
	public void before() {
		person_list = new ArrayList<Person>(1000000);
		for (int i = 0; i < 200; i++) {
			person_list.add(new Person("Person" + i, 15 + i % 10));
		}
	}

//	@Test
	public void testLambda() {
		long t0 = System.nanoTime();
		List<Adult> adult_list = person_list.stream().filter(s -> s.age > 18).map(p -> new Adult(p.name, p.age)).collect(Collectors.toList());
		long t1 = System.nanoTime();
		System.out.printf("serial: %.2fs\n", (t1 - t0) * 1e-9);
	}
	
	@Test
	public void testLambdaParallel() {
		long t0 = System.nanoTime();
		List<Adult> adult_list = person_list.stream().filter(s -> s.age > 18).map(p -> new Adult(p.name, p.age)).parallel().collect(Collectors.toList());
		long t1 = System.nanoTime();
		System.out.printf("parallel %.2fs\n", (t1 - t0) * 1e-9);
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