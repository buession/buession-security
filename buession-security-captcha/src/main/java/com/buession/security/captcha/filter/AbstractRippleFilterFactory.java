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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.filter;

import com.buession.security.captcha.filter.operation.RippleImageOp;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ripple 过滤器工厂抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public abstract class AbstractRippleFilterFactory extends AbstractFilterFactory implements RippleFilterFactory {

	protected List<BufferedImageOp> filters;

	protected RippleImageOp ripple = new RippleImageOp();

	public AbstractRippleFilterFactory(){
		super();
	}

	@Override
	public List<BufferedImageOp> getFilters(){
		if(filters == null){
			filters = new ArrayList<>();

			filters.addAll(getPreRippleFilters());
			filters.add(ripple);
			filters.addAll(getPostRippleFilters());
		}

		return filters;
	}

	protected List<BufferedImageOp> getPreRippleFilters(){
		return Collections.emptyList();
	}

	protected List<BufferedImageOp> getPostRippleFilters(){
		return Collections.emptyList();
	}

	protected static List<BufferedImageOp> createRippleFilters(BufferedImageOp curves){
		List<BufferedImageOp> list = new ArrayList<>(1);
		list.add(curves);
		return list;
	}

}
