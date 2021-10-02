package br.com.andrealoisio.divida.controller.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DevedorFormTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private DevedorForm devedorForm;

    @BeforeEach
    public void inicializa() {
        this.devedorForm = new DevedorForm();
    }

    @Test
    public void deveriaAceitarCelularValido() {
        devedorForm.setCelular("(86) 99955-0234");
        Set<ConstraintViolation<DevedorForm>> violations = validator.validate(devedorForm);
        assertFalse(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("(16) 99955-0234");
        violations = validator.validate(devedorForm);
        assertFalse(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("(86) 59955-0234");
        violations = validator.validate(devedorForm);
        assertFalse(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));
    }

    @Test
    public void naoDeveriaAceitarCelularInvalido() {
        devedorForm.setCelular("(86)99955-0234");
        Set<ConstraintViolation<DevedorForm>> violations = validator.validate(devedorForm);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("(86) 999550234");
        violations = validator.validate(devedorForm);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("86 99955-0234");
        violations = validator.validate(devedorForm);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("(86) 9955-0234");
        violations = validator.validate(devedorForm);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("(06) 99955-0234");
        violations = validator.validate(devedorForm);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));

        devedorForm.setCelular("(86) 49955-0234");
        violations = validator.validate(devedorForm);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("celular")));
    }
}
