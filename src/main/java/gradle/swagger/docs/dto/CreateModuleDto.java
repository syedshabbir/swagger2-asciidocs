package gradle.swagger.docs.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author SSHABBIR
 *
 */
@ApiModel(value = "Create module dto", description = "data required to create a module")
public class CreateModuleDto {

    @NotNull(message = "notNull.ContentMappingDTO.projectId")
    @ApiModelProperty(value = "unique translation elucidat id")
    private String projectId;

    @NotNull(message = "notNull.ContentMappingDTO.moduleId")
    @Digits(integer = 10, fraction = 0, message = "format.ContentMappingDTO.moduleId")
    @ApiModelProperty(value = "module id used for all language variants")
    private int moduleId;

    public CreateModuleDto() {
    }

    public CreateModuleDto(String projectId, int moduleId) {
	this.projectId = projectId;
	this.moduleId = moduleId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
}
