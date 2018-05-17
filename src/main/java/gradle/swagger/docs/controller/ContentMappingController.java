package gradle.swagger.docs.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gradle.swagger.docs.dto.ContentMappingDto;
import gradle.swagger.docs.dto.CreateModuleDto;
import gradle.swagger.docs.dto.ErrorDto;
import gradle.swagger.docs.entity.ContentMapping;
import gradle.swagger.docs.service.ContentMappingService;
import gradle.swagger.docs.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author SSHABBIR
 *
 */
@RestController
//@formatter:off
@Api(value = "/mapping",
     tags = "Create mapping entries",
     produces = "application/json",
     consumes = "application/json",
     protocols = "http, https")
//@formatter:on
@RequestMapping(value = "/mapping", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContentMappingController {

    private ContentMappingService contentMappingService;

    public ContentMappingController(ContentMappingService contentMappingService) {
	this.contentMappingService = contentMappingService;
    }

    // @formatter:off
    @ApiOperation(value = "create elucidat translation content mapping",
	          notes = "creates a language mapping entry for an existing learning module",
	          response = ContentMapping.class)
    @ApiResponses({
	    @ApiResponse(code = 201, message = "Created", response = ContentMapping.class),
	    @ApiResponse(code = 400, message = "Bad request", response = ErrorDto.class)
    })
    //@formatter:on

    @PostMapping("/translation")
    @ResponseBody
    public ResponseEntity<ContentMapping> createLanguageVariantMapping(
	    @ApiParam(required = true, name = "contentMappingDTO", value = "New module language variant mapping")
	    @Valid @RequestBody ContentMappingDto contentMappingDto) {

	ContentMapping contentMapping = new ContentMapping();
	BeanUtils.copyProperties(contentMappingDto, contentMapping);
	contentMapping = contentMappingService.save(contentMapping);
	return new ResponseEntity<ContentMapping>(contentMapping, HttpStatus.CREATED);
    }

    @ApiOperation(value = "create elucidat mapping for a new module ",
	          notes = "creates en-gb mapping entry for new learning module",
	          response = ContentMapping.class)
    @ApiResponses({
	    @ApiResponse(code = 201, message = "Created", response = ContentMapping.class),
	    @ApiResponse(code = 400, message = "Bad request", response = ErrorDto.class)
    })
    @PostMapping("/module")
    @ResponseBody
    public ResponseEntity<ContentMapping> createModuleMapping(
	    @ApiParam(required = true, name = "CreateModuleDto", value = "create new module mapping - default language for mapping is always en-gb")
	    @Valid @RequestBody CreateModuleDto createModuleDto) {

	ContentMapping contentMapping = new ContentMapping();
	BeanUtils.copyProperties(createModuleDto, contentMapping);
	contentMapping.setLocale(Constants.enGB);
	contentMapping = contentMappingService.save(contentMapping);
	return new ResponseEntity<ContentMapping>(contentMapping, HttpStatus.CREATED);
    }

}
