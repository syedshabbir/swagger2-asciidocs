package gradle.swagger.docs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SSHABBIR
 *
 */
@Entity
@Table(name = "CONTENT_MAPPING")
public class ContentMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "MODULE_ID", nullable = false)
    private int moduleId;

    @Column(name = "PROJECT_ID", nullable = false)
    private String projectId;

    @Column(name = "LOCALE", nullable = false)
    private String locale;

    public ContentMapping() {
    }

    public ContentMapping(int moduleId, String projectId, String locale) {
	this.projectId = projectId;
	this.moduleId = moduleId;
	this.locale = locale;
    }

    public Integer getId() {
	return id;
    }

    public long getModuleId() {
	return moduleId;
    }

    public void setModuleId(int moduleId) {
	this.moduleId = moduleId;
    }

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

    @Override
    public String toString() {
	return getModuleId() + "," + getProjectId() + "," + getLocale();
    }
}
