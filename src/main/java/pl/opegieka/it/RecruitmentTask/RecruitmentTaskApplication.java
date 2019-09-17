package pl.opegieka.it.RecruitmentTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
@SpringBootApplication
public class RecruitmentTaskApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RecruitmentTaskApplication.class, args);

		Arrays.asList(ctx.getBeanDefinitionNames()).stream()
				.sorted()
				.forEach(System.out::println);


	}

}
