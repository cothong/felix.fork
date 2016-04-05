/*
 * Copyright (c) OSGi Alliance (2016). All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.osgi.service.converter;

import java.util.function.Function;

/**
 * An {@link Adapter} is used to modify the behaviour of the Converter service,
 * which can be useful when some of the conversions should be done different to
 * the Converter Specification.
 * 
 * @author $Id:$
 */
public interface Adapter extends Converter {
	/**
	 * Specify a conversion rule by providing a rule object.
	 * 
	 * @param rule The conversion rule.
	 * @return The current adapter, can be used to chain invocations.
	 */
    <F, T> Adapter rule(Rule<F, T> rule);

	/**
	 * Specify a rule for the conversion to and from two classes. The rule
	 * specifies the conversion in both directions. This overload makes it easy
	 * to provide the conversions as lambdas, for example:
	 * 
	 * <pre>
	 *  adapter.rule(String[].class, String.class,
	 *      v -> Stream.of(v).collect(Collectors.joining(",")),
	 *      v -> v.split(","));
	 * </pre>
	 * 
	 * @param <F> the type to convert from.
	 * @param <T> the type to convert to.
	 * @param fromCls the class to convert from.
	 * @param toCls the class to convert to.
	 * @param toFun the function to perform the conversion.
	 * @param fromFun the function to perform the reverse conversion.
	 * @return The current adapter, can be used to chain invocations.
	 */
    <F, T> Adapter rule(Class<F> fromCls, Class<T> toCls,
            Function<F, T> toFun, Function<T, F> fromFun);

	/**
	 * Specify a rule for the conversion to and from two classes. The rule
	 * specifies the conversion in both directions. This overload makes it easy
	 * to provide the conversions as method references.
	 *
	 * @param <F> the type to convert from.
	 * @param <T> the type to convert to.
	 * @param toFun the function to perform the conversion.
	 * @param fromFun the function to perform the reverse conversion.
	 * @return The current adapter, can be used to chain invocations.
	 */
    <F, T> Adapter rule(Function<F, T> toFun, Function<T, F> fromFun);

//    <F, T> Adapter rule(Function<TypeReference<F>, TypeReference<T>> toFun,
//            Function<TypeReference<T>,TypeReference<F>> fromFun);
}
