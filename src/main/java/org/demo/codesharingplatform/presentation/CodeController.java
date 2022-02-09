package org.demo.codesharingplatform.presentation;

import org.demo.codesharingplatform.entity.Code;
import org.demo.codesharingplatform.businesslayer.CodeService;
import org.demo.codesharingplatform.dtos.CodeDTO;
import org.demo.codesharingplatform.dtos.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;


@Controller
public class CodeController {

    CodeService codeService;
    CodeMapper codeMapper;

    @Autowired
    public CodeController(CodeService codeService, CodeMapper codeMapper) {
        this.codeService = codeService;
        this.codeMapper = codeMapper;
    }

    @GetMapping("/code/{uuid}")
    public String code(@PathVariable UUID uuid, Model model) {
        Code code = codeService.findCodeById(uuid);
        model.addAttribute("code", code);
        model.addAttribute("title", "Code");
        return "code";
    }

    @GetMapping("/code/latest")
    public String codeLatest(Model model) {
        List<Code> codeList = codeService.findLastTen();
        model.addAttribute("codeList", codeList);
        model.addAttribute("title", "Latest");
        return "codeList";
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<CodeDTO>> apiCodeLatest() {
        List<Code> codeList = codeService.findLastTen();
        List<CodeDTO> codeDTOs = codeMapper.entitiesToDTOs(codeList);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeDTOs);
    }

    @GetMapping("/api/code/{uuid}")
    public ResponseEntity<?> apiCode(@PathVariable UUID uuid) {
        Code code = codeService.findCodeById(uuid);
        if (code == null) {
            return ResponseEntity.notFound().build();
        }
        boolean isAvailable = codeService.checkAvailability(code);
        if (!isAvailable) {
            return ResponseEntity.notFound().build();
        }
        CodeDTO codeDTO = codeMapper.entityToDTO(code);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeDTO);
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<String> apiCodeNew(@RequestBody Code code) {
        codeService.setIsSecret(code);
        Code codeToSave = codeService.save(code);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("{ \"id\": \"" + codeToSave.getId() + "\" }");
    }


    @GetMapping("/code/new")
    public String codeNew(Model model) {
        return "new_code";
    }

}
