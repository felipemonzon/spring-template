package com.moontech.library.interceptors;

import com.moontech.library.constants.ApiConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Calcula el tiempo transcurrido de la petición.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2021
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {
  /**
   * Guardar el tiempo inicial de la petición.
   *
   * @param request petición
   * @param response respuesta del servicio
   * @param handler objeto
   * @return verdadero
   */
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    request.setAttribute(ApiConstant.TIME_REQ_ATTRIBUTE, System.currentTimeMillis());
    MDC.put(ApiConstant.UUID_MDC_LABEL, request.getHeader(ApiConstant.HEADER_UUID));
    log.info(ApiConstant.START_REQUEST, request.getRequestURI());
    return true;
  }

  /**
   * Calcula el tiempo total de la petición y limpia el MDC.
   *
   * @param request petición
   * @param response respuesta del servicio
   * @param handler objeto
   * @param ex excepción
   */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    long time = (Long) request.getAttribute(ApiConstant.TIME_REQ_ATTRIBUTE);
    long timeElapsed = System.currentTimeMillis() - time;
    log.info(ApiConstant.TIME_ELAPSED_MESSAGE, request.getRequestURI(), timeElapsed);

    request.removeAttribute(ApiConstant.TIME_REQ_ATTRIBUTE);

    MDC.clear();
  }
}
