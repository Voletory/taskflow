package taskflow.handler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import taskflow.config.register.WorkRegister;
import taskflow.enums.ConfigSource;
import taskflow.work.WorkFactory;

/**
 * 处理<workFactory>标签，开启该标签以后才能使用{@link WorkFactory}
 * Created by lizhou on 2017/3/14/014.
 */
public class WorkFactoryDefinitionParser implements BeanDefinitionParser, WorkRegister {
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return registerWorkFactory(parserContext.getRegistry());
    }

	@Override
	public ConfigSource getConfigSource() {
		return ConfigSource.XML;
	}
}