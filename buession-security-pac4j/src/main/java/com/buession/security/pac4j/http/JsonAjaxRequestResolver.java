/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.http;

import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.http.HttpAction;
import org.pac4j.core.exception.http.RedirectionAction;
import org.pac4j.core.exception.http.RedirectionActionHelper;
import org.pac4j.core.exception.http.UnauthorizedAction;
import org.pac4j.core.exception.http.WithLocationAction;
import org.pac4j.core.http.ajax.DefaultAjaxRequestResolver;
import org.pac4j.core.redirect.RedirectionActionBuilder;
import org.pac4j.core.util.CommonHelper;

/**
 * JSON way to compute if a HTTP request is an AJAX one.
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JsonAjaxRequestResolver extends DefaultAjaxRequestResolver {

	@Override
	public HttpAction buildAjaxResponse(final WebContext context,
										final RedirectionActionBuilder redirectionActionBuilder) {
		String url = null;

		if(isAddRedirectionUrlAsHeader()){
			final RedirectionAction action = redirectionActionBuilder.getRedirectionAction(context).orElse(null);
			if(action instanceof WithLocationAction){
				url = ((WithLocationAction) action).getLocation();
			}
		}

		if(context.getRequestParameter(FACES_PARTIAL_AJAX_PARAMETER).isPresent() == false){
			if(CommonHelper.isNotBlank(url)){
				context.setResponseHeader(HttpConstants.LOCATION_HEADER, url);
			}
			throw UnauthorizedAction.INSTANCE;
		}

		final StringBuilder buffer = new StringBuilder("{\"redirect\":{");

		if(CommonHelper.isNotBlank(url)){
			buffer.append("\"url\":\"").append(url).append("\"");
		}
		buffer.append("}}");

		return RedirectionActionHelper.buildFormPostContentAction(context, buffer.toString());
	}

}
