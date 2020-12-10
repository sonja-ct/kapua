/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.core.exception;

import org.eclipse.kapua.commons.service.internal.KapuaServiceDisabledException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaServiceDisabledExceptionMapperTest extends Assert {

    KapuaServiceDisabledExceptionMapper kapuaServiceDisabledExceptionMapper;

    @Before
    public void initialize() {
        kapuaServiceDisabledExceptionMapper = new KapuaServiceDisabledExceptionMapper();
    }

    @Test
    public void toResponseTest() {
        assertEquals("Expected and actual values should be the same.", 404, kapuaServiceDisabledExceptionMapper.toResponse(new KapuaServiceDisabledException("name")).getStatus());
        assertEquals("Expected and actual values should be the same.", "Not Found", kapuaServiceDisabledExceptionMapper.toResponse(new KapuaServiceDisabledException("name")).getStatusInfo().toString());
    }

    @Test(expected = NullPointerException.class)
    public void toResponseNullTest() {
        kapuaServiceDisabledExceptionMapper.toResponse(null);
    }
}