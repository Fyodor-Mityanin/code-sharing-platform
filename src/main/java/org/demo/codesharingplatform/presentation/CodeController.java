package org.demo.codesharingplatform.presentation;

import org.demo.codesharingplatform.entity.Code;
import org.demo.codesharingplatform.businesslayer.CodeService;
import org.demo.codesharingplatform.dtos.CodeDTO;
import org.demo.codesharingplatform.dtos.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CodeController {
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";

    @Autowired
    CodeService codeService;

    @GetMapping("/code/{id}")
    public String code(@PathVariable Long id, Model model) {
        Code code = codeService.findCodeById(id);
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
        List<CodeDTO> codeDTOs = CodeMapper.entitiesToDTOs(codeList);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeDTOs);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<CodeDTO> apiCode(@PathVariable Long id) {
        Code code = codeService.findCodeById(id);
        CodeDTO codeDTO = CodeMapper.entityToDTO(code);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeDTO);
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<String> apiCodeNew(@RequestBody Code code) {
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
