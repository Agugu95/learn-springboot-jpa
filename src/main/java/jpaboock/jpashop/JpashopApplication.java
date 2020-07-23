package jpaboock.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String hi = hello.getData();
		System.out.println(hi);
		SpringApplication.run(JpashopApplication.class, args);
	}

}
