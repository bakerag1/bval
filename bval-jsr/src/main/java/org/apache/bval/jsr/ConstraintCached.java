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
package org.apache.bval.jsr;

import javax.validation.ConstraintValidator;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: hold the relationship annotation->validatedBy[] ConstraintValidator classes that are already parsed in a
 * cache.<br/>
 */
public class ConstraintCached {
    private final Map<Class<? extends Annotation>, Class<? extends ConstraintValidator<?, ?>>[]> classes =
        new HashMap<Class<? extends Annotation>, Class<? extends ConstraintValidator<?, ?>>[]>();

    /**
     * Record the set of validator classes for a given constraint annotation.
     * 
     * @param annotationClass
     * @param definitionClasses
     */
    public <A extends Annotation> void putConstraintValidator(Class<A> annotationClass,
        Class<? extends ConstraintValidator<A, ?>>[] definitionClasses) {
        classes.put(annotationClass, definitionClasses);
    }

    /**
     * Learn whether we have cached the validator classes for the requested constraint annotation.
     * 
     * @param annotationClass
     *            to look up
     * @return boolean
     */
    public boolean containsConstraintValidator(Class<? extends Annotation> annotationClass) {
        return classes.containsKey(annotationClass);
    }

    /**
     * Get the cached validator classes for the requested constraint annotation.
     * 
     * @param annotationClass
     *            to look up
     * @return array of {@link ConstraintValidator} implementation types
     */
    @SuppressWarnings("unchecked")
    public <A extends Annotation> Class<? extends ConstraintValidator<A, ?>>[] getConstraintValidators(
        Class<A> annotationClass) {
        return (Class<? extends ConstraintValidator<A, ?>>[]) classes.get(annotationClass);
    }

}
