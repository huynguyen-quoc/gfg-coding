package com.gfg.product.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 5295546830020209782L;

  public BusinessException(String message) {
    super(message);
  }
}
