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
package org.eclipse.kapua.app.api.resources.v1.resources.model;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.math.BigInteger;

@Category(JUnitTests.class)
public class EntityIdTest extends Assert {

    @Test
    public void entityIdTest() {
        String[] compactEntityId = {"111", "entityId", "1000000", "-111", "12"};
        String[] expectedValues = {"-10403", "134670355735069", "-174798351539", "-303755", "-41"};

        for (int i = 0; i < compactEntityId.length; i++) {
            EntityId entityId = new EntityId(compactEntityId[i]);

            assertEquals("Expected and actual values should be the same.", expectedValues[i], entityId.getId().toString());
            assertEquals("Expected and actual values should be the same.", expectedValues[i], entityId.toString());
        }
    }

    @Test(expected = NullPointerException.class)
    public void entityIdNullTest() {
        new EntityId(null);
    }

    @Test(expected = NumberFormatException.class)
    public void entityIdEmptyTest() {
        new EntityId("");
    }

    @Test
    public void setAndGetIdTest() {
        EntityId entityId = new EntityId("111");

        assertEquals("Expected and actual values should be the same.", "-10403", entityId.getId().toString());
        assertEquals("Expected and actual values should be the same.", "-10403", entityId.toString());

        entityId.setId(BigInteger.ONE);

        assertEquals("Expected and actual values should be the same.", "1", entityId.getId().toString());
        assertEquals("Expected and actual values should be the same.", "1", entityId.toString());
    }
}