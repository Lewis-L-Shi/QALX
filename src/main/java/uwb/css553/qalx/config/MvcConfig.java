package uwb.css553.qalx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is responsible to configure views.
 * @author Trang Quang
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Map view controllers to views
     * @param registry ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // this will map URLs to views (html) directly without controller
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/doctor").setViewName("doctor");
        registry.addViewController("/researcher").setViewName("researcher");
    }
}
