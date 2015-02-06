/**
 * Copyright (C) 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dashbuilder.client.metrics;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.dashbuilder.client.metrics.widgets.vertical.VerticalServerMetrics;

public class MetricsDashboard extends Composite {

    interface MetricsDashboardBinder extends UiBinder<Widget, MetricsDashboard>{}
    private static final MetricsDashboardBinder uiBinder = GWT.create(MetricsDashboardBinder.class);

    // The client bundle for this widget.
    @UiField
    MetricsDashboardClientBundle resources;

    @UiField()
    HTML title;

    @UiField()
    HorizontalPanel summaryArea;

    @UiField()
    HorizontalPanel verticalSummaryArea;

    @UiField()
    HorizontalPanel serverDetailsArea;

    public String getTitle() {
        return "System Metrics Dashboard Widget";
    }

    public MetricsDashboard() {

        // Init the dashboard from the UI Binder template
        initWidget(uiBinder.createAndBindUi(this));

        // Set the initial dashboard title.
        title.setText("System metrics summary");
        
        // Initially show vertical servers summary.
        showVerticalServersSummary();
    }
    
    private void showVerticalServersSummary() {
        String[] servers = getAvailableServers();
        if (servers != null && servers.length > 0) {
            for (int x = 0; x < servers.length; x++) {
                String server = servers[x];
                VerticalServerMetrics verticalServerMetrics = null;
                if (x == 0) verticalServerMetrics = new VerticalServerMetrics(server, true);
                else verticalServerMetrics = new VerticalServerMetrics(server);
                verticalSummaryArea.add(verticalServerMetrics);
            }
        }
        verticalSummaryArea.setVisible(true);
    }
    
    private String[] getAvailableServers() {
        // TODO: Perfom a real dataset lookup.
        return new String[] {"server1","server2","server3","server4","server5"};
    }

}