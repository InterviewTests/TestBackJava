package io.santander.gastos.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.Instant;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@Builder
@JsonInclude(NON_EMPTY)
public class ErrorResponse {

    private Instant timestamp;

    private int status;

    private String error;

    @Singular
    @JsonProperty("error_description")
    private List<String> messages;
}