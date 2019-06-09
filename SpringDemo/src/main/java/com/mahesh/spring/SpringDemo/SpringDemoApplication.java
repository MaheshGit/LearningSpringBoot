package com.mahesh.spring.SpringDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {
	public static void main(String[] args) {
		// sorting array...bubble sort is tightly coupled
		BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
		int [] a = bubbleSortAlgorithm.sort(new int[] {1,2,3});
		
		// Achieving loose coupling using interface
		BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new QuickSortAlgorithm());
		int index = binarySearchImpl.binarySearch(a, 3);
		System.out.println("Print Index::::"+ index);
		
		// creating beans using Spring
		ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);
		BinarySearchImpl biImpl = context.getBean(BinarySearchImpl.class);
		int index1 = binarySearchImpl.binarySearch(a, 3);
		System.out.println("Print Index1::::"+ index1);
	}

}
