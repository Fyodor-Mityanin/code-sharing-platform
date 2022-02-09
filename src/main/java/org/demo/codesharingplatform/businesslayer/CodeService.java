package org.demo.codesharingplatform.businesslayer;

import org.demo.codesharingplatform.entity.Code;
import org.demo.codesharingplatform.persistence.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Code> findLastTen() {
        return  codeRepository.findFirst10ByOrderByDateDesc();
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
        if (code.isSecret()) {
            decrementViews(code);
            if (code.getEstimateTime().isAfter(LocalDateTime.now()) && code.getViews() <= 0) {
                delete(code);
                return false;
            }
        }
        return true;
    }

    public void setIsSecret(Code code) {
        if (code.getViews() > 0 || code.getTime().getSeconds() > 0) {
            code.setSecret(true);
        }
    }
}
