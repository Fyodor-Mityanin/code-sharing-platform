package org.demo.codesharingplatform.businesslayer;

import org.demo.codesharingplatform.entity.Code;
import org.demo.codesharingplatform.persistence.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code findCodeById(UUID uuid) {
        return  codeRepository.findCodeById(uuid);
    }

    public List<Code> findLastTenNotSecret() {
        return codeRepository.findLastNotSecretPageable(PageRequest.of(0, 10));
    }

    public Code save(Code code) {
        code.setDate(LocalDateTime.now());
        return codeRepository.save(code);
    }

    public void delete(Code code) {
        codeRepository.delete(code);
    }

    public void decrementViews(Code code) {
        code.decrementViews();
        codeRepository.save(code);
    }

    public boolean checkAvailability(Code code) {
        System.out.println(code.getViews());
        System.out.println(code.getTime());
        System.out.println(code.isViewRestrict());
        System.out.println(code.isTimeRestrict());
        if (code.isViewRestrict()) {
            if (code.getViews() <= 0) {
                delete(code);
                return false;
            }
            decrementViews(code);
        }
        if (code.isTimeRestrict()) {
            if (code.getSecondsLeft() <= 0) {
                delete(code);
                return false;
            }
        }
        return true;
    }

    public void setIsSecret(Code code) {
        if (code.getViews() > 0) {
            code.setViewRestrict(true);
        }
        if (code.getTime() > 0) {
            code.setTimeRestrict(true);
        }
    }
}
