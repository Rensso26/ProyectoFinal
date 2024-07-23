package ec.edu.uce.interfaz.connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permite todas las solicitudes de cualquier origen
        corsConfiguration.addAllowedOrigin("*");

        // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
        corsConfiguration.addAllowedMethod("*");

        // Permite todos los encabezados
        corsConfiguration.addAllowedHeader("*");

        // Permite credenciales (cookies, autorización) - opcional y solo si es necesario
        corsConfiguration.setAllowCredentials(true);

        // Configura la fuente CORS basada en la URL
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        // Aplica esta configuración a todas las rutas
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        // Devuelve un filtro CORS configurado
        return new CorsWebFilter(corsConfigurationSource);
    }
}
