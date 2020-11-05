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

import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaIllegalArgumentExceptionMapperTest extends Assert {

    KapuaIllegalArgumentExceptionMapper kapuaIllegalArgumentExceptionMapper;

    @Before
    public void initialize() {
        kapuaIllegalArgumentExceptionMapper = new KapuaIllegalArgumentExceptionMapper();
    }

    @Test
    public void toResponseTest() {
        assertEquals("Expected and actual values should be the same.", 400, kapuaIllegalArgumentExceptionMapper.toResponse(new KapuaIllegalArgumentException("name", "value")).getStatus());
        assertEquals("Expected and actual values should be the same.", "Bad Request", kapuaIllegalArgumentExceptionMapper.toResponse(new KapuaIllegalArgumentException("name", "value")).getStatusInfo().toString());
    }

    @Test(expected = NullPointerException.class)
    public void toResponseNullTest() {
        kapuaIllegalArgumentExceptionMapper.toResponse(null);
    }
}