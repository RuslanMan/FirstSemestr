package managers;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SimpleTemplateManager implements TemplateManager{
    Configuration configuration;

    public SimpleTemplateManager(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void write(Page page, HttpServletResponse response, Map<String, Object> root) {
        try {
            page.template.process(root, response.getWriter());
        } catch (TemplateException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
