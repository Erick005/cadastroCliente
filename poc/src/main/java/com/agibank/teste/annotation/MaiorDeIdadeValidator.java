package com.agibank.teste.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class MaiorDeIdadeValidator implements ConstraintValidator<MaiorDeIdade, LocalDate> {

    @Override
    public boolean isValid(LocalDate dataNascimento, ConstraintValidatorContext context) {
        if (dataNascimento == null) {
            return true; // ou false, dependendo se você não permite datas de nascimento nulas
        }

        return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
    }

}
