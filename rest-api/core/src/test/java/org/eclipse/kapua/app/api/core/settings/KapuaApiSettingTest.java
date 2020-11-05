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
package org.eclipse.kapua.app.api.core.settings;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

@Category(JUnitTests.class)
public class KapuaApiSettingTest extends Assert {

    @Test
    public void kapuaApiSettingTest() throws Exception {
        Constructor<KapuaApiSetting> kapuaApiSetting = KapuaApiSetting.class.getDeclaredConstructor();
        assertTrue("True expected.", Modifier.isPrivate(kapuaApiSetting.getModifiers()));
        kapuaApiSetting.setAccessible(true);
        kapuaApiSetting.newInstance();
    }

    @Test
    public void getInstanceTest() {
        assertTrue("True expected.", KapuaApiSetting.getInstance() instanceof KapuaApiSetting);
    }
}