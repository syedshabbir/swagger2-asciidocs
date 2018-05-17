package gradle.swagger.docs.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import gradle.swagger.docs.dto.ContentMappingDto;

/**
 * @author SSHABBIR
 *
 */
@JsonTest
@RunWith(SpringRunner.class)
public class ContentMappingDTOTest {

    @Autowired
    private JacksonTester<ContentMappingDto> json;
    private ContentMappingDto contentMappingDto;

    private static final int MODULE_ID = 5678;
    private static final String ELUCIDAT_ID = "1234";
    private static final String LOCALE = "en-gb";

    private static final String JSON_TO_DESERIALIZE = "{\"projectId\":\""
	    + ELUCIDAT_ID
	    + "\",\"locale\":\""
	    + LOCALE
	    + "\",\"moduleId\":"
	    + MODULE_ID
	    + "}";
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	contentMappingDto = new ContentMappingDto(ELUCIDAT_ID, LOCALE, MODULE_ID);
    }

    @Test
    public void projectIdSerializes() throws IOException {
	assertThat(this.json.write(contentMappingDto))
		.extractingJsonPathStringValue("@.projectId")
		.isEqualTo(ELUCIDAT_ID);
    }

    @Test
    public void moduleIdSerializes() throws IOException {
	assertThat(this.json.write(contentMappingDto))
		.extractingJsonPathNumberValue("@.moduleId")
		.isEqualTo(MODULE_ID);
    }

    @Test
    public void localeSerializes() throws IOException {
	assertThat(this.json.write(contentMappingDto))
		.extractingJsonPathStringValue("@.locale")
		.isEqualTo(LOCALE);
    }

    @Test
    public void projectIdDeserializes() throws IOException {
	assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getProjectId().equals(ELUCIDAT_ID));
    }

    @Test
    public void localeDeserializes() throws IOException {
	assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getLocale().equals(LOCALE));
    }

    @Test
    public void moduleIdDeserializes() throws IOException {
	assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getModuleId() == MODULE_ID);
    }
}
