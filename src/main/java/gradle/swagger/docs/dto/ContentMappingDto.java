package gradle.swagger.docs.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author SSHABBIR
 *
 */
@ApiModel(value = "Content mapping dto", description = "data required to create a translation module")
public class ContentMappingDto {

    public ContentMappingDto() {
    }

    public ContentMappingDto(String projectId, String locale, int moduleId) {
	this.projectId = projectId;
	this.locale = locale;
	this.moduleId = moduleId;
    }

    @NotNull(message = "notNull.ContentMappingDTO.projectId")
    @ApiModelProperty(value = "unique translation elucidat id", required = true, example = "57b1912e1c28f")
    private String projectId;

    @NotNull(message = "notNull.ContentMappingDTO.locale")
    @ApiModelProperty(value = "translation locale format language(2 letter)-country(2 letter)")
    @Pattern(regexp = "[a-z]{2}-[a-z]{2}", message = "format.ContentMappingDTO.locale")
    private String locale;

    @NotNull(message = "notNull.ContentMappingDTO.moduleId")
    @Digits(integer = 10, fraction = 0, message = "format.ContentMappingDTO.moduleId")
    @ApiModelProperty(value = "module id encapsulating all language variants")
    private int moduleId;

    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public int getModuleId() {
        return moduleId;
    }
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
}
