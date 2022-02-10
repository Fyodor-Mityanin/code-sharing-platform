package org.demo.codesharingplatform.dtos.mapper;

import org.demo.codesharingplatform.dtos.CodeDTO;
import org.demo.codesharingplatform.entity.Code;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeMapper {

    public CodeDTO entityToDTO (Code code) {
        if (code.isSecret()) {
            return new CodeDTO(
                    code.getDate().toString(),
                    code.getCode(),
                    (int) code.getSecondsLeft(),
                    code.getViews()
            );
        } else {
            return new CodeDTO(
                    code.getDate().toString(),
                    code.getCode(),
                    0,
                    0
            );
        }
    }

    public List<CodeDTO> entitiesToDTOs (List<Code> codeList) {
        List<CodeDTO> codeDTOs = new ArrayList<>();
        for (Code code: codeList) {
            CodeDTO dto = entityToDTO(code);
            codeDTOs.add(dto);
        }
        return codeDTOs;
    }

}
