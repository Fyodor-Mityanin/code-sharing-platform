package org.demo.codesharingplatform.presentation;

import org.demo.codesharingplatform.businesslayer.CodeService;
import org.demo.codesharingplatform.dtos.CodeDTO;
import org.demo.codesharingplatform.dtos.mapper.CodeMapper;
import org.demo.codesharingplatform.entity.Code;
import org.demo.codesharingplatform.excepton.CodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/code/new")
    public String codeNew(Model model) {
        return "new_code";
    }

    @GetMapping("/code/{uuid}")
    public String code(@PathVariable UUID uuid, Model model) {
        System.out.println("/code/{uuid}");
        Code code = codeService.findCodeById(uuid);
        System.out.println("code={" + code);
        if (code == null) {
            throw new CodeNotFoundException();
        }
        boolean isAvailable = codeService.checkAvailability(code);
        System.out.println("code available " + isAvailable);
        if (!isAvailable) {
            throw new CodeNotFoundException();
        }
        model.addAttribute("code", code);
        model.addAttribute("title", "Code");
        System.out.println(model);
        return "code";
    }

    @GetMapping("/code/latest")
    public String codeLatest(Model model) {
        List<Code> codeListNotSecret = codeService.findLastTenNotSecret();
        model.addAttribute("codeList", codeListNotSecret);
        model.addAttribute("title", "Latest");
        return "codeList";
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<CodeDTO>> apiCodeLatest() {
        List<Code> codeListNotSecret = codeService.findLastTenNotSecret();
        List<CodeDTO> codeDTOs = codeMapper.entitiesToDTOs(codeListNotSecret);
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
        var resp = ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeDTO);
        System.out.println(resp);
        return resp;
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
}
