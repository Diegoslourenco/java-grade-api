package comgft.starterapi.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.plugin.core.SimplePluginRegistry;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	 @Bean
	 public Docket api() {
		 return new Docket(DocumentationType.SWAGGER_2)	           	
				 	.apiInfo(metaInfo())
		            .pathMapping("/")
		            .useDefaultResponseMessages(false)
		            .select()
		            .apis(RequestHandlerSelectors.basePackage("comgft.starterapi"))
		            .build()
		            .forCodeGeneration(true)
		            .genericModelSubstitutes(ResponseEntity.class)
		            .securityContexts(Lists.newArrayList(securityContext()))
		            .securitySchemes(Lists.newArrayList(apiKey()));
	}
	 
	  @Bean
	  UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder()
	        .defaultModelsExpandDepth(-1)
	        .build();
	  }  

    private ApiInfo metaInfo() {
    	
    	Contact contact = new Contact("Diego da Silva Lourenço", "https://www.linkedin.com/in/diegoslourenco/",
                						"diego.lourenco@gft.com");
    	
    	List<VendorExtension> vext = new ArrayList<>();

    	return new ApiInfo(
                "Starters API RESTful",
                "API RESTful de cadastro de starters, desafios, submissões e notas.",
                "1.0",
                "Terms of Service",       
                contact,
                "https://www.apache.org/licenses/LICENSE-2.0",
                "Apache License Version 2.0",
                vext);
    }
    
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        
        plugins.add(new CollectionJsonLinkDiscoverer());
        
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
    
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
    }
	    
}
