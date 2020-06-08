package com.paulo.altran.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRetornoDTO {

	private String title;
	private int status;
	private String detail;
	private Long timestamp;
	private String developerMessage;
}
