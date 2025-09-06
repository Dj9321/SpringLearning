package com.dj.learning.spring.boot.repository;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class PropertyBasedNamingStrategy implements PhysicalNamingStrategy {

	private static final Properties tableProperties = new Properties();

	static {
		try {
			tableProperties.load(
					PropertyBasedNamingStrategy.class.getClassLoader().getResourceAsStream("application.properties") // table-mapping
			);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load table mappings", e);
		}
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		// Hibernate uses entity simple class name as default if no @Table(name=...)
		// provided
		String entityName = name.getText();
		String mappedName = tableProperties.getProperty(entityName, entityName);
		return Identifier.toIdentifier(mappedName);
	}

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
		return name;
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		return name;
	}
}