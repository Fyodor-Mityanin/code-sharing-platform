package org.demo.codesharingplatform.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Code")
public class CodeNotFoundException extends RuntimeException {

}