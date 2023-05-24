package com.example.springaotdemo;

import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform;
import org.postgresql.util.PGInterval;
import org.postgresql.util.PGobject;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mapping.model.CamelCaseAbbreviatingFieldNamingStrategy;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class SpringaotdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringaotdemoApplication.class, args);
	}
	static class RunTimeHintsRegistrarAOT implements RuntimeHintsRegistrar {

		@Override
		public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
			hints.reflection().registerType(PostgreSQLDialect.class, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS)
					.registerType(PGobject.class,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS)
					.registerType(PGInterval.class,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.INVOKE_DECLARED_METHODS);
			try{
				hints.reflection().registerType(NoJtaPlatform.class,MemberCategory.INTROSPECT_PUBLIC_METHODS)
						.registerConstructor(NoJtaPlatform.class.getConstructor(), ExecutableMode.INVOKE)
						.registerConstructor(CamelCaseAbbreviatingFieldNamingStrategy.class.getConstructor(),ExecutableMode.INVOKE);

			}catch (NoSuchMethodException e){
				throw new RuntimeException();
			}

		}
	}

}
