/*
 * [The "BSD licence"]
 * Copyright (c) 2013 Dandelion
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors 
 * may be used to endorse or promote products derived from this software 
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.dandelion.datatables.core.extension.feature;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.configuration.Configuration;
import com.github.dandelion.datatables.core.extension.AbstractExtension;
import com.github.dandelion.datatables.core.html.HtmlTable;
import com.github.dandelion.datatables.core.processor.feature.FeatureAppearProcessor;

/**
 * <p>
 * Extension used to add an effect when the table has finished being drawed.
 * 
 * @see FeatureAppearProcessor
 * @see Configuration#FEATURE_APPEAR
 * @author Thibault Duchateau
 * @since 0.10.0
 */
public class AppearFeature extends AbstractExtension {

	@Override
	public String getName() {
		return "appear";
	}

	@Override
	public void setup(HtmlTable table) {

		if("fadein".equals(table.getTableConfiguration().getFeatureAppear())){
			table.addCssStyle("display:none;");
		}
		
		if("block".equals(table.getTableConfiguration().getFeatureAppear())){
			appendToBeforeEndDocumentReady("$('#" + table.getId() + "').show();");			
		}
		else{
			if(StringUtils.isNotBlank(table.getTableConfiguration().getFeatureAppearDuration())){
				appendToBeforeEndDocumentReady("$('#" + table.getId() + "').fadeIn(" + table.getTableConfiguration().getFeatureAppearDuration() + ");");
			}
			else{
				appendToBeforeEndDocumentReady("$('#" + table.getId() + "').fadeIn();");
			}
		}		
	}
}