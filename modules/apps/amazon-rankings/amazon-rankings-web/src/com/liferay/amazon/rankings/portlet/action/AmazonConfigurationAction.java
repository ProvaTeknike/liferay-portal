/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.amazon.rankings.portlet.action;

import com.liferay.amazon.rankings.model.AmazonRankings;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_amazon_rankings_portlet_AmazonRankingsPortlet"
	},
	service = ConfigurationAction.class
)
public class AmazonConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String[] isbns = StringUtil.split(
			StringUtil.toUpperCase(getParameter(actionRequest, "isbns")),
			CharPool.SPACE);

		String amazonAccessKeyId = getParameter(
			actionRequest, "amazon.access.key.id");
		String amazonAssociateTag = getParameter(
			actionRequest, "amazon.associate.tag");
		String amazonSecretAccessKey = getParameter(
			actionRequest, "amazon.secret.access.key");

		Arrays.sort(isbns);

		setPreference(actionRequest, "isbns", isbns);
		setPreference(
			actionRequest, "amazon.access.key.id", amazonAccessKeyId);
		setPreference(
			actionRequest, "amazon.associate.tag", amazonAssociateTag);
		setPreference(
			actionRequest, "amazon.secret.access.key",
			amazonSecretAccessKey);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

}