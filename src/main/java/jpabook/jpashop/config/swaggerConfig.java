package jpabook.jpashop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerConfig {
    // swagger API 문서 생성
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.swaggerInfo()) // 스웨거 정보 등록
                .select()
                .apis(RequestHandlerSelectors.basePackage("jpabook.jpashop.api")) // 모든 controller 패키지 탐색
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(true); // 디폴트 200, 401, 403, 404 메시지 표
    }

    // swagger API 문서 정보
    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot API Document")
                .description("스프링부트 API 문서 테스트")
                .version("1.0.0")
                .build();
    }
}
