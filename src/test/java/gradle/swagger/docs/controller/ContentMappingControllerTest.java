package gradle.swagger.docs.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import gradle.swagger.docs.controller.ContentMappingController;
import gradle.swagger.docs.dto.ContentMappingDto;
import gradle.swagger.docs.dto.CreateModuleDto;
import gradle.swagger.docs.entity.ContentMapping;
import gradle.swagger.docs.repository.ContentMappingRepository;
import gradle.swagger.docs.service.ContentMappingService;
/**
 * @author SSHABBIR
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ContentMappingController.class)
@ImportAutoConfiguration(MessageSourceAutoConfiguration.class)
public class ContentMappingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContentMappingService contentMappingService;

    @MockBean
    private ContentMappingRepository contentMappingRepository;

    private JacksonTester<ContentMappingDto> contentMappingDTOJsonTester;
    private JacksonTester<CreateModuleDto> createModuleDTOJsonTester;
    private ContentMappingDto contentMappingDto;
    private CreateModuleDto createModuleDto;


    @Before
    public void setup() {
	JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void createLanguageMapping() throws Exception {

	contentMappingDto = new ContentMappingDto("1234", "en-gb", 1234);
	final String contentMappingDTOJson = contentMappingDTOJsonTester.write(contentMappingDto).getJson();
	when(contentMappingService.save(any(ContentMapping.class))).thenReturn(new ContentMapping());
	mockMvc.perform(post("/mapping/translation")
		.content(contentMappingDTOJson)
		.contentType(APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status()
			.isCreated());
    }

    @Test
    public void createLanguageMappingInvalidLlocale() throws Exception {

	contentMappingDto = new ContentMappingDto("1234", "en-gG", 1234);
	final String contentMappingDTOJson = contentMappingDTOJsonTester.write(contentMappingDto).getJson();
	when(contentMappingService.save(any(ContentMapping.class))).thenReturn(new ContentMapping());
	mockMvc.perform(post("/mapping/translation")
		.content(contentMappingDTOJson)
		.contentType(APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(content().string(containsString(
			"####Locale format is incorrect, should be lowercase language code(2)-country code(2) e.g. en-gb####")));
    }

    @Test
    public void createNewModule() throws Exception {

	createModuleDto = new CreateModuleDto("1234", 5678);
	final String contentMappingDTOJson = createModuleDTOJsonTester.write(createModuleDto).getJson();
	when(contentMappingService.save(any(ContentMapping.class))).thenReturn(new ContentMapping());
	mockMvc.perform(post("/mapping/module")
		.content(contentMappingDTOJson)
		.contentType(APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status().isCreated());
    }

    @Test
    public void createNewModuleInvalidProjectId() throws Exception {

	createModuleDto = new CreateModuleDto();
	createModuleDto.setModuleId(1234);
	final String contentMappingDTOJson = createModuleDTOJsonTester.write(createModuleDto).getJson();
	when(contentMappingService.save(any(ContentMapping.class))).thenReturn(new ContentMapping());
	mockMvc.perform(post("/mapping/module")
		.content(contentMappingDTOJson)
		.contentType(APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(content().string(containsString("####project Id cannot be empty####")));
    }

    @Test
    public void failCreateNewModuledWhenModuleId() throws Exception {

	createModuleDto = new CreateModuleDto("1234", 1234);
	final String contentMappingDTOJson = createModuleDTOJsonTester.write(createModuleDto).getJson();
	when(contentMappingService.save(any(ContentMapping.class)))
		.thenThrow(new DataIntegrityViolationException("could not execute statement; SQL [n/a]..."));
	mockMvc.perform(post("/mapping/module")
		.content(contentMappingDTOJson)
		.contentType(APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(content().string(containsString(
			"####mapping values already exist in a combination. Please note combination values treated as unique, UNIQUE (MODULE_ID, ELUCIDAT_ID, LOCALE), UNIQUE (MODULE_ID, LOCALE), UNIQUE (ELUCIDAT_ID, LOCALE), UNIQUE (MODULE_ID, ELUCIDAT_ID)####")));
    }

}
