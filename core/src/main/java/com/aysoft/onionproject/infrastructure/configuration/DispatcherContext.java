package com.aysoft.onionproject.infrastructure.configuration;

import static com.aysoft.onionproject.infrastructure.seedwork.context.SystemContext.*;
import static java.util.stream.Collectors.*;

import com.aysoft.onionproject.infrastructure.seedwork.i18n.SmartAcceptHeaderLocaleResolver;
import com.aysoft.onionproject.infrastructure.seedwork.server.AccessLogEntry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.valves.AccessLogValve;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("com.aysoft.onionproject.dist.rest")
public class DispatcherContext extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(Arrays.stream(STATUS).map(this::getErrorPage).toArray(ErrorPage[]::new));
        if(enabled && container instanceof TomcatEmbeddedServletContainerFactory) {
            TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory) container;
            factory.setContextValves(this.getValves());
        }
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new SmartAcceptHeaderLocaleResolver();
    }

    //<editor-fold desc="Support methods">
    private List<AccessLogValve> getValves() {
        try {
            Path path = Paths.get(String.format("%s/%s", conf(), configFile));
            if (Files.exists(path)) {
                List<AccessLogEntry> valves = new ObjectMapper().readValue(Files.lines(path).collect(joining()), new TypeReference<List<AccessLogEntry>>(){});
                return valves.stream().map(this::getValve).collect(toList());
            }
            return List.of();
        } catch (Exception ex) {
            throw new BeanCreationException("Error in access log configuration!", ex);
        }
    }

    private AccessLogValve getValve(AccessLogEntry entry) {
        AccessLogValve valve = new AccessLogValve();
        valve.setDirectory(dir);
        valve.setPrefix(entry.getPrefix());
        valve.setPattern(entry.getPattern());
        valve.setSuffix(entry.getSuffix());
        return valve;
    }

    private ErrorPage getErrorPage(HttpStatus status) {
        return new ErrorPage(status, "/error/"+status.value());
    }
    //</editor-fold>

    //<editor-fold desc="Dependency injection">
    private @Value("${application.server.logs.enabled}") boolean enabled;
    private @Value("${application.server.logs.dir}") String dir;
    private @Value("${application.server.logs.config-file}") String configFile;
    //</editor-fold>

    //<editor-fold desc="Constants">
    private static final HttpStatus[] STATUS = new HttpStatus[]{HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_FOUND, HttpStatus.FORBIDDEN, HttpStatus.UNAUTHORIZED};
    //</editor-fold>
}
