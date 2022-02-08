package org.demo.codesharingplatform.businesslayer;

import org.demo.codesharingplatform.persistence.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public CodeEntity findCodeById(Long id) {
        return  codeRepository.findCodeById(id);
    }

    public List<CodeEntity> findLastTen() {
        return  codeRepository.findFirst10ByOrderByDateDesc();
    }

    public CodeEntity save(CodeEntity code) {
        code.setDate(LocalDateTime.now());
        return codeRepository.save(code);
    }

}