package org.demo.codesharingplatform.persistence;

import org.demo.codesharingplatform.entity.Code;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Code findCodeById(UUID uuid);

    List<Code> findFirst10ByOrderByDateDesc();
}
