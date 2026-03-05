package myspring.di.xml;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	ApplicationContext context;
	
	@BeforeEach
	void init() {
		// 컨테이너 객체생성
		context = new GenericXmlApplicationContext("classpath:hello-bean.xml");
	}
	
	// Constructor Injection 테스트하는 메서드
	@Test
	void helloBeanCons() {
		Hello hello = context.getBean("helloC",Hello.class);
		assertEquals("Hello 생성자", hello.sayHello());
		hello.print();
	}
	
	// Setter Injection 테스트하는 메서드
	@Test @Disabled
	void helloBeanSetter() {
		//getBean()이 Object 타입으로 반환되기 때문에 Hello 타입으로 캐스팅해야 함
		Hello helloById = (Hello)context.getBean("hello");
		Hello helloByType = context.getBean("hello", Hello.class);
		
		//싱글톤인지 검증하기
		System.out.println(helloById == helloByType);
		//assertSame(기대값, 실제값) -> 객체 주소를 비교
		assertSame(helloById, helloByType);
		//assertEquals(기대값, 실제값)-> 값이 같은지
		assertEquals("Hello 스프링", helloByType.sayHello());
		
		helloByType.print();
		
		Printer strPrinter = context.getBean("strPrinter", Printer.class);
		
		assertEquals("Hello 스프링", strPrinter.toString());
		
	}
}
