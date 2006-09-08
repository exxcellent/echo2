/* 
 * This file is part of the Echo Web Application Framework (hereinafter "Echo").
 * Copyright (C) 2002-2005 NextApp, Inc.
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

package nextapp.echo2.testapp.interactive.testscreen;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Container;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.SplitPaneLayoutData;
import nextapp.echo2.testapp.interactive.ButtonColumn;
import nextapp.echo2.testapp.interactive.CoolDelayMessage;
import nextapp.echo2.testapp.interactive.StyleUtil;
import nextapp.echo2.webcontainer.ContainerContext;
import nextapp.echo2.webcontainer.DefaultServerDelayMessage;

/**
 * Interactive test for the <code>Container</code> component.
 */
public class ContainerTest extends SplitPane {
    
    public ContainerTest() {
        super(SplitPane.ORIENTATION_HORIZONTAL, new Extent(250, Extent.PX));
        setStyleName("DefaultResizable");
        
        ButtonColumn controlsColumn = new ButtonColumn();
        controlsColumn.setStyleName("TestControlsColumn");
        add(controlsColumn);
        
        final Container container = new Container();
        add(container);

        controlsColumn.addButton("Reset", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.setBackground(null);
                container.setForeground(null);
                container.setFont(null);
            }
        });
        controlsColumn.addButton("Change Background", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.setBackground(StyleUtil.randomColor());
            }
        });
        controlsColumn.addButton("Change Foreground", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.setForeground(StyleUtil.randomColor());
            }
        });
        controlsColumn.addButton("Change Font", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.setFont(StyleUtil.randomFont());
            }
        });
        controlsColumn.addButton("Set Content (Label)", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (container.getComponentCount() > 0) {
                    container.removeAll();
                }
                container.add(new Label("Hello, world!"));
            }
        });
        controlsColumn.addButton("Set Content (Long Label)", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (container.getComponentCount() > 0) {
                    container.removeAll();
                }
                container.add(new Label(StyleUtil.QUASI_LATIN_TEXT_1));
            }
        });
        controlsColumn.addButton("Set Content (Grid)", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (container.getComponentCount() > 0) {
                    container.removeAll();
                }
                Grid grid = new Grid();
                grid.setBorder(StyleUtil.randomBorder());
                grid.setInsets(new Insets(StyleUtil.randomExtent(8)));
                grid.add(new Label("A label"));
                grid.add(new Label("A label"));
                grid.add(new Label("A label"));
                grid.add(new Label("A label"));
                grid.add(new Label("A label"));
                container.add(grid);
            }
        });
        controlsColumn.addButton("Clear Content", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
            }
        });
        controlsColumn.addButton("Add Component", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (container.getParent() == null) {
                    ContainerTest.this.add(container);
                }
            }
        });
        controlsColumn.addButton("Remove Component", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (container.getParent() != null) {
                    ContainerTest.this.remove(container);
                }
            }
        });
    }
}
