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
package org.eclipse.kapua.app.api.core;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Category(JUnitTests.class)
public class CORSResponseFilterTest extends Assert {

    HttpServletRequest request;
    HttpServletResponse response;
    CORSResponseFilter corsResponseFilter;
    FilterChain filterChain;
    FilterConfig filterConfig;

    @Before
    public void initialize() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        corsResponseFilter = new CORSResponseFilter();
        filterChain = Mockito.mock(FilterChain.class);
        filterConfig = Mockito.mock(FilterConfig.class);
    }

    @Test
    public void initTest() {
        try {
            corsResponseFilter.init(filterConfig);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void initNullTest() {
        try {
            corsResponseFilter.init(null);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void destroyTest() {
        try {
            corsResponseFilter.destroy();
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterTest() {
        try {
            corsResponseFilter.doFilter(request, response, filterChain);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterNullRequestTest() {
        try {
            corsResponseFilter.doFilter(null, response, filterChain);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterNullResponseTest() {
        try {
            corsResponseFilter.doFilter(request, null, filterChain);
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }
    }

    @Test
    public void doFilterNullChainTest() {
        try {
            corsResponseFilter.doFilter(request, response, null);
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }
    }
}