package org.demo.codesharingplatform.persistence;

import org.demo.codesharingplatform.businesslayer.CodeEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CodeRepository extends CrudRepository<CodeEntity, Long> {
    CodeEntity findCodeById(Long id);

    List<CodeEntity> findFirst10ByOrderByDateDesc();
}
