package comgft.starterapi.config;

import java.lang.reflect.Field;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;


/**
 * HideHateoasLinks --- contains the methods to hide the HATEOAS links in the swagger documentation in order to simplify the display for the user.
 * @author    Diego da Silva Lourenco
 */

@Component
public class HideHateoasLinks implements ModelPropertyBuilderPlugin {
	
	@Override
	public boolean supports(final DocumentationType delimiter) {
		return true;
	}

	@Override
	public void apply(final ModelPropertyContext context) {
		if (context.getBeanPropertyDefinition().isPresent()) {
			BeanPropertyDefinition bean = context.getBeanPropertyDefinition().get();
			AnnotatedField annotatedField = bean.getField();
		  
			if (null != annotatedField) {
				Field field = (Field) annotatedField.getMember();
    		
				if (field != null)
					hideLinksField(context, field);
			}
		} 
		else if (context.getAnnotatedElement().isPresent() && context.getAnnotatedElement().get() instanceof Field) {
			Field field = (Field) context.getAnnotatedElement().get();
			hideLinksField(context, field);
		}
  	}

  	private void hideLinksField(final ModelPropertyContext context, final Field field) {
  		if (RepresentationModel.class.equals(field.getDeclaringClass()) && field.getName().contains("links")) {
  			context.getBuilder().isHidden(true);
  		}
  	}

}
