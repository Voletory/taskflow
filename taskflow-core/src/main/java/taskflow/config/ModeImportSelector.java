package taskflow.config;

import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import taskflow.annotation.EnableTaskFlow;
import taskflow.enums.ConfigSource;

import java.lang.annotation.Annotation;
import java.util.function.Predicate;


/**
 * @author steven.zhu 2020/7/10 21:05.
 * @类描述：
 */
public class ModeImportSelector implements ImportSelector {

    private static final String DEFAULT_MODE_ATTRIBUTE_NAME = "source";


    protected String getModeAttributeName() {
        return DEFAULT_MODE_ATTRIBUTE_NAME;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Class<?> annType = EnableTaskFlow.class;
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(annType.getName(), false));
        if (attributes == null) {
            throw new IllegalArgumentException(String.format(
                    "@%s is not present on importing class '%s' as expected",
                    annType.getSimpleName(), importingClassMetadata.getClassName()));
        }
        ConfigSource configSource = attributes.getEnum(getModeAttributeName());
        String[] imports = selectImports(configSource);
        if (imports == null) {
            throw new IllegalArgumentException("Unknown Mode: " + configSource);
        }
        return imports;
    }

    protected String[] selectImports(ConfigSource configSource) {
        switch (configSource) {
            case XML:
                return new String[]{TaskFlowXmlConfiguration.class.getName()};
            case CUS:
                return new String[]{TaskFlowConfiguration.class.getName()};
            default:
                return null;
        }
    }

}
