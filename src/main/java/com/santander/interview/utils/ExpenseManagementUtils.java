package com.santander.interview.utils;

import com.santander.interview.domain.Response;
import com.santander.interview.domain.ResponseError;
import com.santander.interview.domain.ResponseObject;
import com.santander.interview.enums.ResponseMessageEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExpenseManagementUtils {

    public static int convertStringtoInt(int defaultValue, String value) {
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static ResponseEntity<ResponseObject> responseWithData (ResponseMessageEnum responseMessageEnum,
                                                                    HttpStatus httpStatus,
                                                                    Object data) {
        return new ResponseEntity<>(
                new ResponseObject(
                        httpStatus.value(),
                        responseMessageEnum.getUserMessage(),
                        responseMessageEnum.getInternalMessage(),
                        data
                ),
                httpStatus);
    }

    public static ResponseEntity<Response> responseWithoutData (ResponseMessageEnum responseMessageEnum,
                                                                       HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new Response(
                        httpStatus.value(),
                        responseMessageEnum.getUserMessage(),
                        responseMessageEnum.getInternalMessage()
                ),
                httpStatus);
    }

    public static ResponseEntity<ResponseError> responseWithError (ResponseMessageEnum responseMessageEnum,
                                                                     HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ResponseError(
                        httpStatus.value(),
                        responseMessageEnum.getUserMessage(),
                        responseMessageEnum.getInternalMessage(),
                        responseMessageEnum.getCode()
                ),
                httpStatus);
    }

}
