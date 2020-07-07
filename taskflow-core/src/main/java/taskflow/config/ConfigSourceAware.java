package taskflow.config;

import taskflow.enums.ConfigSource;

/**
 * 获取configSource
 */
public interface ConfigSourceAware {
	ConfigSource getConfigSource();
}
