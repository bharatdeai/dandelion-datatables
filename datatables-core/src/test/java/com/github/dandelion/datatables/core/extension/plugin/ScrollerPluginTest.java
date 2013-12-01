package com.github.dandelion.datatables.core.extension.plugin;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import com.github.dandelion.core.asset.web.AssetsRequestContext;
import com.github.dandelion.datatables.core.constants.DTConstants;
import com.github.dandelion.datatables.core.extension.AbstractExtensionTest;
import com.github.dandelion.datatables.core.extension.Extension;

public class ScrollerPluginTest extends AbstractExtensionTest {

	@Test
	public void shoud_load_the_extension() {
		extensionProcessor.process(new HashSet<Extension>(Arrays.asList(new ScrollerPlugin())));

		assertThat(AssetsRequestContext.get(table.getTableConfiguration().getRequest()).getScopes(true)).hasSize(1);
		assertThat(mainConfig).hasSize(1);
		assertThat(mainConfig).includes(entry(DTConstants.DT_DOM, "frtiS"));
	}

	@Test
	public void shoud_load_the_extension_with_already_configured_dom() {
		table.getTableConfiguration().setFeatureDom("lfr");
		mainConfig.put(DTConstants.DT_DOM, "lfr");
		extensionProcessor.process(new HashSet<Extension>(Arrays.asList(new ScrollerPlugin())));

		assertThat(AssetsRequestContext.get(table.getTableConfiguration().getRequest()).getScopes(true)).hasSize(1);
		assertThat(mainConfig).hasSize(1);
		assertThat(mainConfig).includes(entry(DTConstants.DT_DOM, "lfrS"));
	}
	
	@Test
	public void shoud_load_the_extension_with_jqueryui_enabled() {
		table.getTableConfiguration().setFeatureJqueryUi(true);
		mainConfig.put(DTConstants.DT_DOM, "lfr");
		extensionProcessor.process(new HashSet<Extension>(Arrays.asList(new ScrollerPlugin())));

		assertThat(AssetsRequestContext.get(table.getTableConfiguration().getRequest()).getScopes(true)).hasSize(1);
		assertThat(mainConfig).hasSize(1);
		assertThat(mainConfig).includes(entry(DTConstants.DT_DOM, "<\"H\"lfr>t<\"F\"ip>S"));
	}
}