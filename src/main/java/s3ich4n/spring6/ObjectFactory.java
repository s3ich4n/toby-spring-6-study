package s3ich4n.spring6;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 메타 프로그래밍 기법으로 스프링의 BeanFactory 가 얘를 찾을 수 있다
 * 예를 들면, @Controller, @Service, @Repository, ...
 * 그 관계를 XML 로 쓰긴하는데 요샌 잘 안쓴다
 */
@Configuration
@ComponentScan
public class ObjectFactory {}
