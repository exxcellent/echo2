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

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.Table;
import nextapp.echo2.app.layout.SplitPaneLayoutData;
import nextapp.echo2.app.table.DefaultTableModel;
import nextapp.echo2.app.table.TableCellRenderer;
import nextapp.echo2.webcontainer.ContainerContext;
import nextapp.echo2.webrender.ClientProperties;

/**
 * A test which displays the contents of the <code>ClientProperties</code> 
 * object.
 * <p>
 * Note that this object has a dependency on the Web Application Container 
 * and Web Renderer.
 */
public class ContainerContextTest extends Column {
    
    private class PropertyTableCellRenderer 
    implements TableCellRenderer {

        /**
         * @see nextapp.echo2.app.table.TableCellRenderer#getTableCellRendererComponent(nextapp.echo2.app.Table, 
         *      java.lang.Object, int, int)
         */
        public Component getTableCellRendererComponent(Table table, Object value, int column, int row) {
            String labelValue;
            if (value instanceof String[]) {
                String[] stringArray = (String[]) value;
                StringBuffer out = new StringBuffer();
                for (int i = 0; i < stringArray.length; ++i) {
                    out.append(stringArray[i]);
                    if (i < stringArray.length - 1) {
                        out.append(",");
                    }
                }
                labelValue = out.toString();
            } else {
                labelValue = value.toString();
            }
            
            Label label = new Label(labelValue);
            label.setStyleName(row % 2 == 0 ? "evenCellLabel" : "oddCellLabel");
            return label;
        }
    }

    public ContainerContextTest() {
        super();
        
        setCellSpacing(new Extent(10));
        
        SplitPaneLayoutData splitPaneLayoutData = new SplitPaneLayoutData();
        splitPaneLayoutData.setInsets(new Insets(10));
        setLayoutData(splitPaneLayoutData);
        
        ApplicationInstance app = ApplicationInstance.getActive();
        ContainerContext containerContext 
                = (ContainerContext) app.getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
        
        Column clientPropertiesColumn = new Column();
        add(clientPropertiesColumn);
        clientPropertiesColumn.add(new Label("Client Properties"));
        clientPropertiesColumn.add(createClientPropertiesTable(containerContext));
        
        Column initialParametersColumn = new Column();
        add(initialParametersColumn);
        initialParametersColumn.add(new Label("Initial Parameters"));
        initialParametersColumn.add(createInitialParametersTable(containerContext));
    }
    
    public Table createClientPropertiesTable(ContainerContext containerContext) {
        ClientProperties clientProperties = containerContext.getClientProperties();
        String[] propertyNames = clientProperties.getPropertyNames();
        Arrays.sort(propertyNames);
        
        Table table = new Table();
        table.setStyleName("default");
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(2);
        for (int i = 0; i < propertyNames.length; ++i) {
            model.insertRow(0, new Object[]{propertyNames[i], clientProperties.getString(propertyNames[i])});
        }
        
        table.setDefaultRenderer(Object.class, new PropertyTableCellRenderer());
        
        table.getColumnModel().getColumn(0).setHeaderValue("Property");
        table.getColumnModel().getColumn(1).setHeaderValue("Value");
        
        return table;
    }
    
    public Table createInitialParametersTable(ContainerContext containerContext) {
        Map initialParameterMap = containerContext.getInitialParameterMap();
        
        Table table = new Table();
        table.setStyleName("default");
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnCount(2);
        Iterator it = initialParameterMap.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            model.insertRow(0, new Object[]{key, initialParameterMap.get(key)});
        }
        
        table.setDefaultRenderer(Object.class, new PropertyTableCellRenderer());
        
        table.getColumnModel().getColumn(0).setHeaderValue("Property");
        table.getColumnModel().getColumn(1).setHeaderValue("Value");
        
        return table;
    }
}