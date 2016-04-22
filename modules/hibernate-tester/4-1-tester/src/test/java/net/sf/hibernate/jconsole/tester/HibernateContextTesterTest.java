/*
 * Copyright (c) 2011
 *
 * This file is part of HibernateJConsole.
 *
 *     HibernateJConsole is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     HibernateJConsole is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with HibernateJConsole.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.hibernate.jconsole.tester;

import org.junit.Test;

/**
 * Tests whether HibernateContextTester runs inside this Hibernate version.
 *
 * @author Juergen_Kellerer, 2010-09-18
 * @version 1.0
 */
public class HibernateContextTesterTest {
    @Test
    public void testTestContext() throws Exception {
        HibernateContextTester.testContext();
    }
}
