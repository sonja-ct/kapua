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
package org.eclipse.kapua.integration.misc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.eclipse.kapua.app.api.core.filter.KapuaSessionCleanupFilter;
import org.eclipse.kapua.commons.security.KapuaSecurityUtils;
import org.eclipse.kapua.commons.security.KapuaSession;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Category(JUnitTests.class)
public class KapuaSessionCleanupFilterTest extends Assert {

    KapuaSessionCleanupFilter kapuaSessionCleanupFilter;
    URL shiroIniUrl;
    Ini shiroIni;
    InputStream input;
    SecurityManager securityManager;
    ServletRequest request;
    ServletResponse response;
    FilterChain chain;
    KapuaSession kapuaSession;

    @Before
    public void initialize() throws IOException {
        kapuaSessionCleanupFilter = new KapuaSessionCleanupFilter();
        shiroIniUrl = getClass().getResource("/shiro.ini");
        shiroIni = new Ini();
        input = shiroIniUrl.openStream();
        shiroIni.load(input);
        securityManager = new IniSecurityManagerFactory(shiroIni).getInstance();

        request = Mockito.mock(ServletRequest.class);
        response = Mockito.mock(ServletResponse.class);
        chain = Mockito.mock(FilterChain.class);
        kapuaSession = new KapuaSession();
    }

    @Test
    public void initTest() {
        try {
            kapuaSessionCleanupFilter.init(Mockito.mock(FilterConfig.class));
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void initNullTest() {
        try {
            kapuaSessionCleanupFilter.init(null);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void destroyTest() {
        try {
            kapuaSessionCleanupFilter.destroy();
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterTest() {
        SecurityUtils.setSecurityManager(securityManager);
        KapuaSecurityUtils.setSession(kapuaSession);

        try {
            kapuaSessionCleanupFilter.doFilter(request, response, chain);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterNullRequestTest() {
        SecurityUtils.setSecurityManager(securityManager);
        KapuaSecurityUtils.setSession(kapuaSession);

        try {
            kapuaSessionCleanupFilter.doFilter(null, response, chain);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterNullResponseTest() {
        SecurityUtils.setSecurityManager(securityManager);
        KapuaSecurityUtils.setSession(kapuaSession);

        try {
            kapuaSessionCleanupFilter.doFilter(request, null, chain);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void doFilterNullChainTest() {
        SecurityUtils.setSecurityManager(securityManager);
        KapuaSecurityUtils.setSession(kapuaSession);

        try {
            kapuaSessionCleanupFilter.doFilter(request, response, null);
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }
    }

    @Test
    public void doFilterNullSessionTest() {
        SecurityUtils.setSecurityManager(securityManager);
        try {
            kapuaSessionCleanupFilter.doFilter(request, response, chain);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }
}