package com.agibank.teste.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class MaiorDeIdadeValidator implements ConstraintValidator<MaiorDeIdade, LocalDate> {

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {
        if (Objects.nonNull(dataNascimento)) {
            return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
        }

        return true;
    }

}
