package com.gfg.product.exception;

import java.io.Serial;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends BusinessException {

  @Serial
  private static final long serialVersionUID = -672450619400833364L;

  public NotFoundException(String message) {
    super(message);
  }
}
