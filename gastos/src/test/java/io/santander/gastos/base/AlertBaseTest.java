package io.santander.gastos.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.io.IOException;
import java.io.InputStream;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.IGNORE_UNKNOWN;
import static com.fasterxml.jackson.core.JsonParser.Feature.IGNORE_UNDEFINED;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.lang.String.format;
import static java.nio.file.Paths.get;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AlertBaseTest {

    protected static final String PATH_TEMPLATE = "./src/test/resources/json/%s.json";

    protected static final ObjectMapper MAPPER;

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected Filter[] filters;

    protected MockMvc mockMvc;

    static {
        MAPPER = new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(IGNORE_UNDEFINED, true)
                .configure(IGNORE_UNKNOWN, true);
    }

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.context).addFilters(this.filters).build();
    }


    public static <T> T createMockFromFile(final String fileName, final Class<T> resultClass)
            throws IOException {
        final Resource resource = new FileSystemResource(get(format(PATH_TEMPLATE, fileName)).toFile());
        try (final InputStream stream = resource.getInputStream()) {
            return MAPPER.readValue(stream, resultClass);
        }
    }
}