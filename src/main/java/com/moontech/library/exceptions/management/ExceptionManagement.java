package com.moontech.library.exceptions.management;

import com.moontech.library.constants.ApiConstant;
import com.moontech.library.constants.ErrorConstant;
import com.moontech.library.exceptions.custom.BusinessException;
import com.moontech.library.exceptions.custom.ErrorResponse;
import com.moontech.library.exceptions.custom.ForbiddenException;
import com.moontech.library.exceptions.custom.NotDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Administrador de excepciones.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2021
 */
@Slf4j
@RestControllerAdvice
public class ExceptionManagement {
  /** Enumerator for errors. */
  public enum ErrorType {
    ERROR,
    WARN,
    INVALID,
    FATAL
  }

  /**
   * Método para manejar una excepción de tipo {@link Exception}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link Exception}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link Exception}.
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse generalException(Exception ex, WebRequest request) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.FATAL.name())
            .code(ErrorConstant.GENERIC_ERROR_CODE)
            .message(ErrorConstant.GENERIC_ERROR_MESSAGE)
            .moreInfo(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link NotDataFoundException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link NotDataFoundException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     NotDataFoundException}.
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotDataFoundException.class)
  public ErrorResponse notDataFoundException(NotDataFoundException ex, WebRequest request) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.WARN.name())
            .code(ErrorConstant.RECORD_NOT_FOUND_CODE)
            .message(ErrorConstant.RECORD_NOT_FOUND_MESSAGE)
            .moreInfo(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link HttpRequestMethodNotSupportedException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link HttpRequestMethodNotSupportedException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     HttpRequestMethodNotSupportedException}.
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  public ErrorResponse resolveHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex, WebRequest request) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.ERROR.name())
            .code(ErrorConstant.BAD_REQUEST_CODE)
            .message(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link HttpMediaTypeNotSupportedException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link HttpMediaTypeNotSupportedException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     HttpMediaTypeNotSupportedException}.
   */
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public ErrorResponse resolveHttpMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex, WebRequest request) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.ERROR.name())
            .code(ErrorConstant.BAD_REQUEST_CODE)
            .message(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link HttpMediaTypeNotAcceptableException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link HttpMediaTypeNotAcceptableException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     HttpMediaTypeNotAcceptableException}.
   */
  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponse resolveHttpMediaTypeNotAcceptableException(
      WebRequest request, HttpMediaTypeNotAcceptableException ex) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.INVALID.name())
            .code(ErrorConstant.BAD_REQUEST_CODE)
            .message(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link MethodArgumentNotValidException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link MethodArgumentNotValidException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     MethodArgumentNotValidException}.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResponse resolveMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, WebRequest request) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

    List<String> fields = new ArrayList<>();
    Map<String, List<String>> groupedErrors = new HashMap<>();

    for (FieldError fieldError : fieldErrors) {
      String field = fieldError.getField();
      groupedErrors.computeIfAbsent(
          fieldError.getDefaultMessage(), key -> Collections.singletonList(field));
      fields.add(field);
    }
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.INVALID.name())
            .code(ErrorConstant.BAD_REQUEST_CODE)
            .message(groupedErrors.toString())
            .moreInfo(fields.toString())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link BusinessException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link BusinessException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     BusinessException}.
   */
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ErrorResponse resolveBusinessException(WebRequest request, BusinessException ex) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.WARN.name())
            .code(ex.getCode())
            .message(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Método para manejar una excepción de tipo {@link ForbiddenException}.
   *
   * @param request Objeto Http Servlet de petición.
   * @param ex Excepción recibida {@link ForbiddenException}
   * @return errorResponse {@link ErrorResponse} respuesta específica para {@link
   *     ForbiddenException}.
   */
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorResponse resolveForbiddenException(WebRequest request, ForbiddenException ex) {
    ErrorResponse apiError =
        ErrorResponse.builder()
            .type(ErrorType.WARN.name())
            .code(ex.getCode())
            .message(ex.getMessage())
            .uuid(request.getHeader(ApiConstant.HEADER_UUID))
            .build();
    log.debug(apiError.toString());
    return apiError;
  }

  /**
   * Maneja una excepción de tipo {@code DataIntegrityViolationException} generada por excepciones
   * SQL.
   *
   * @param req Petición
   * @param ex excepción generada por JPA
   * @return objeto de respuesta específico para {@see DataIntegrityViolationException}
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public ErrorResponse resolveDataIntegrityViolationException(
      HttpServletRequest req, DataIntegrityViolationException ex) {
    String error = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();

    String[] message = Arrays.stream(error.split("[:]")).map(String::trim).toArray(String[]::new);
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .type(ErrorType.ERROR.name())
            .code(ErrorConstant.GENERIC_ERROR_CODE)
            .message(message[2])
            .location(req.getRequestURI())
            .moreInfo(
                message[1].replace(ErrorConstant.PREFIX_DETAIL_MESSAGE, StringUtils.EMPTY).trim())
            .uuid(req.getHeader(ApiConstant.HEADER_UUID))
            .timestamp(ZonedDateTime.now())
            .build();
    log.error(errorResponse.toString());
    return errorResponse;
  }
}
