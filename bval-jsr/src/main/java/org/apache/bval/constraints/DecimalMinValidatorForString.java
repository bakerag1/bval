/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.bval.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * Description:
 * Check that the String being validated represents a number, and has a value
 * >= minvalue
 */
public class DecimalMinValidatorForString implements ConstraintValidator<DecimalMin, String> {

    private BigDecimal minValue;

    @Override
    public void initialize(DecimalMin annotation) {
        try {
            this.minValue = new BigDecimal(annotation.value());
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(annotation.value() + " does not represent a valid BigDecimal format");
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //null values are valid
        if (value == null) {
            return true;
        }
        try {
            return new BigDecimal(value).compareTo(minValue) != -1;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
