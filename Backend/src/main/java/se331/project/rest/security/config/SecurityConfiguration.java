package se331.project.rest.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.headers((headers) -> {
      headers.frameOptions((frameOptions) -> frameOptions.disable());
    });
    http.csrf((crsf) -> crsf.disable())
            .authorizeHttpRequests((authorize) -> {
              authorize.requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll()
                      .requestMatchers(HttpMethod.GET,"/students").permitAll()
                      .requestMatchers(HttpMethod.GET,"/students/**").permitAll()
                      .requestMatchers(HttpMethod.GET,"/AllStudents").permitAll()
                      .requestMatchers(HttpMethod.GET, "/teachers").permitAll()
                      .requestMatchers(HttpMethod.GET, "/teachers/**").permitAll()
                      .requestMatchers(HttpMethod.GET, "/AllTeachers").permitAll()
                      .requestMatchers(HttpMethod.GET, "/studentsByTeacher/**").permitAll()
                      .requestMatchers(HttpMethod.GET, "/teacherByStudent/**").permitAll()
                      .requestMatchers(HttpMethod.GET, "/announcements").permitAll()
                      .requestMatchers(HttpMethod.GET, "/announcement/**").permitAll()
                      .requestMatchers(HttpMethod.POST, "/announcements").permitAll()
                      .requestMatchers(HttpMethod.PUT,"/updatestudents").permitAll()
                      .requestMatchers(HttpMethod.PUT,"/updateteachers").permitAll()
                      .requestMatchers(HttpMethod.POST,"/uploadFile").permitAll()
                      .requestMatchers(HttpMethod.GET, "/folder/**").permitAll()
                      .requestMatchers(HttpMethod.GET, "/folders/**").permitAll()
                      .requestMatchers(HttpMethod.POST, "/addFolder").permitAll()
                      .requestMatchers(HttpMethod.POST, "/deleteFolder/**").permitAll()
                      .requestMatchers(HttpMethod.POST, "/addRecipe").permitAll()
                      .requestMatchers(HttpMethod.POST, "/deleteRecipe").permitAll()
                      .anyRequest().authenticated();
            })

            .sessionManagement((session) ->{
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })

            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout((logout) -> {
              logout.logoutUrl("/api/v1/auth/logout");
              logout.addLogoutHandler(logoutHandler);
              logout.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
            })
    ;

    http.cors(withDefaults());
    return http.build();

  }
}
