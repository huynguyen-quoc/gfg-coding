package com.gfg.product.exception;

import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class InternalServerErrorException extends BusinessException {

  @Serial
  private static final long serialVersionUID = 7485183387641201190L;

  public InternalServerErrorException(String message) {
    super(message);
  }
}
