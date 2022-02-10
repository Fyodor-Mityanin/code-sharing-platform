package org.demo.codesharingplatform.persistence;

import org.demo.codesharingplatform.entity.Code;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Code findCodeById(UUID uuid);

    @Query("select code from Code code where code.secret = false order by code.date desc")
    List<Code> findLastNotSecretPageable(Pageable page);

}
