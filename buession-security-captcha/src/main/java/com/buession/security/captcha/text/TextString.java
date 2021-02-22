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
package com.buession.security.captcha.text;

import com.buession.security.captcha.core.TextCharacter;

import java.util.ArrayList;

class TextString {

	private ArrayList<TextCharacter> characters = new ArrayList<>();

	public void addCharacter(TextCharacter tc){
		characters.add(tc);
	}

	public ArrayList<TextCharacter> getCharacters(){
		return characters;
	}

	public double getWidth(){
		double min = 0;
		double max = 0;
		boolean first = true;

		for(TextCharacter tc : characters){
			if(first){
				min = tc.getX();
				max = tc.getX() + tc.getWidth();
				first = false;
			}else{
				if(min > tc.getX()){
					min = tc.getX();
				}

				if(max < tc.getX() + tc.getWidth()){
					max = tc.getX() + tc.getWidth();
				}
			}
		}

		return max - min;
	}

	public double getHeight(){
		double min = 0;
		double max = 0;
		boolean first = true;

		for(TextCharacter tc : characters){
			if(first){
				min = tc.getY();
				max = tc.getY() + tc.getHeight();
				first = false;
			}else{
				if(min > tc.getY()){
					min = tc.getY();
				}

				if(max < tc.getY() + tc.getHeight()){
					max = tc.getY() + tc.getHeight();
				}
			}
		}

		return max - min;
	}

	public void clear(){
		characters.clear();
	}

}
