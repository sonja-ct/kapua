/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.core.auth;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Category(JUnitTests.class)
public class KapuaTokenAuthenticationFilterTest extends Assert {

    HttpServletRequest request;
    HttpServletResponse response;
    KapuaTokenAuthenticationFilter kapuaTokenAuthenticationFilter;
    Object[] mappedValues;

    @Before
    public void initialize() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        kapuaTokenAuthenticationFilter = new KapuaTokenAuthenticationFilter();
        mappedValues = new Object[]{new Object(), 0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false};
    }

    @Test
    public void isAccessAllowedTrueTest() {
        Mockito.when(request.getMethod()).thenReturn("OPTIONS");
        for (Object mappedValue : mappedValues) {
            assertTrue("True expected.", kapuaTokenAuthenticationFilter.isAccessAllowed(request, response, mappedValue));
        }
    }

    @Test
    public void onAccessDeniedTest() throws Exception {
        assertFalse("False expected.", kapuaTokenAuthenticationFilter.onAccessDenied(request, response));
    }

    @Test
    public void onAccessDeniedNullRequestTest() throws Exception {
        assertFalse("False expected.", kapuaTokenAuthenticationFilter.onAccessDenied(null, response));
    }

    @Test(expected = NullPointerException.class)
    public void onAccessDeniedNullResponseTest() throws Exception {
        assertFalse("False expected.", kapuaTokenAuthenticationFilter.onAccessDenied(request, null));
    }
}