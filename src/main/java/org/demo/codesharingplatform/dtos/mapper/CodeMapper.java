package org.demo.codesharingplatform.dtos.mapper;

import org.demo.codesharingplatform.entity.Code;
import org.springframework.stereotype.Service;
import org.demo.codesharingplatform.dtos.CodeDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeMapper {

    public static CodeDTO entityToDTO (Code code) {
        return new CodeDTO(code.getDate().toString(), code.getCode());
    }

    public static List<CodeDTO> entitiesToDTOs (List<Code> codeList) {
        List<CodeDTO> codeDTOs = new ArrayList<>();
        for (Code code: codeList) {
            CodeDTO dto = entityToDTO(code);
            codeDTOs.add(dto);
        }
        return codeDTOs;
    }

}
