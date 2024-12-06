package com.lamulaapp.exception

import io.konform.validation.ValidationError

class ValidationErrorsException(val errors: List<ValidationError>) : Exception()