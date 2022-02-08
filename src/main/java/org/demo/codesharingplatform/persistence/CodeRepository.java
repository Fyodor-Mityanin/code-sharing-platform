package org.demo.codesharingplatform.persistence;

import org.demo.codesharingplatform.entity.Code;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Code findCodeById(Long id);

    List<Code> findFirst10ByOrderByDateDesc();
}
