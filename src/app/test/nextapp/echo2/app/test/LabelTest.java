/* 
 * This file is part of the Echo Web Application Framework (hereinafter "Echo").
 * Copyright (C) 2002-2009 NextApp, Inc.
 *
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */

package nextapp.echo2.app.test;

import junit.framework.TestCase;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.IllegalChildException;
import nextapp.echo2.app.Label;

/**
 * Unit test(s) for the <code>nextapp.echo2.app.Label</code>. 
 */
public class LabelTest extends TestCase {
    
    /**
     * Test default property values.
     */
    public void testDefaults() {
        Label label = new Label();
        assertTrue(label.isLineWrap());
    }

    /**
     * Attempt to illegally add children, test for failure.
     */
    public void testIllegalChildren() {
        Label label = new Label();
        boolean exceptionThrown = false;
        try {
            label.add(new Label("you can't add children to this component, right?"));
        } catch (IllegalChildException ex) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    /**
     * Test property accessors and mutators.
     */
    public void testProperties() {
        Label label = new Label();
        label.setText("Label");
        label.setIcon(TestConstants.ICON);
        label.setTextAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
        label.setTextPosition(new Alignment(Alignment.DEFAULT, Alignment.BOTTOM));
        label.setLineWrap(false);
        assertEquals("Label", label.getText());
        assertEquals(TestConstants.ICON, label.getIcon());
        assertEquals(new Alignment(Alignment.LEFT, Alignment.TOP), label.getTextAlignment());
        assertEquals(new Alignment(Alignment.DEFAULT, Alignment.BOTTOM), label.getTextPosition());
        assertEquals(false, label.isLineWrap());
    }
}
