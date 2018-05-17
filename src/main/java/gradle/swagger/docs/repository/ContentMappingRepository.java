package gradle.swagger.docs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gradle.swagger.docs.entity.ContentMapping;
/**
 * @author SSHABBIR
 *
 */
@Repository
public interface ContentMappingRepository extends CrudRepository<ContentMapping, Long> {
}
