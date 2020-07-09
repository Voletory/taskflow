package taskflow.config.register;

import taskflow.enums.ConfigSource;

/**
 * 获取configSource
 */
public interface ConfigSourceAware {
	ConfigSource getConfigSource();
}
