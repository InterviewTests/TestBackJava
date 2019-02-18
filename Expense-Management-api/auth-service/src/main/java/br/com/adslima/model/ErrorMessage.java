package br.com.adslima.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage implements Serializable{

  private static final long serialVersionUID = -1325725054820828328L;
  
  @JsonProperty(value = "error")
  private String error;
  @JsonProperty(value = "error_description")
  private String errorDescription;
  
  public ErrorMessage(String error, String errorDescription) {
    this.error = error;
    this.errorDescription = errorDescription;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
  }
  
}
