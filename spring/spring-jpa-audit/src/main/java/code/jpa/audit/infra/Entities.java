package code.jpa.audit.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("")
public class Entities {

	private final Map<String, String> entidades = new HashMap<>();

	public Map<String, String> getEntidades() {
		return entidades;
	}

}
