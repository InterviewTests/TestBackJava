package io.santander.gastos.enumerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
public enum ErrorMessages {

    MISSING_PARAMETER {
        @Override
        public String getErrorMessage(String error) {
            return String.format("The %s parameter was not found.", error);
        }
    },

    MISSING_USER_CARD {
        @Override
        public String getErrorMessage(String error) {
            return String.format("There is no card for the customer %s", error);
        }
    },

    NONEXISTENT_CARD {
        @Override
        public String getErrorMessage(@Nullable String error) {
            return String.format("Nonexistent card", error);
        }
    },

    INTERNAL_SERVER_ERROR {
        @Override
        public String getErrorMessage(String error) {
            return String.format("%s", error);
        }
    };

    public abstract String getErrorMessage(String error);
}
