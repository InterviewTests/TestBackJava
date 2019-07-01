package br.com.zup.way.db.postgres.model.dto;

import lombok.Getter;

@Getter
public class TokenDTO {
    private String token;
    private String type;

    public TokenDTO(String token, String type) {
        this.token = token;
        this.type = type;
    }
}
