package fr.filrougeback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestAPIResponse {
    private int statusCode;
    private String message;

    public RestAPIResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
