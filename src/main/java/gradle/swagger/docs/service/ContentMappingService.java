package gradle.swagger.docs.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import gradle.swagger.docs.entity.ContentMapping;
import gradle.swagger.docs.repository.ContentMappingRepository;

/**
 * @author SSHABBIR
 *
 */
@Component("contentMappingService")
@Transactional
public class ContentMappingService {

    private final ContentMappingRepository contentMappingRepository;

    public ContentMappingService(ContentMappingRepository contentMappingRepository) {
	this.contentMappingRepository = contentMappingRepository;
    }

    public ContentMapping save(ContentMapping contentMapping) {
	return contentMappingRepository.save(contentMapping);
    }

}
