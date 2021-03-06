package com.github.dandelion.datatables.testing.basics;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.dandelion.datatables.testing.BaseIT;

public class FilteringBaseIT extends BaseIT {

	@Test
	public void should_filter_data_when_using_an_input_field() throws Exception {
		goToPage("basics/filtering_with_input");
		
		// Now we test the input that filters data
		fill(getTable().find("tfoot").find("input", 0)).with("vanna");
		
		assertThat(getTable().find("tbody").find("tr", 0).find("td", 0).getText()).isEqualTo("2");
		assertThat(getTable().find("tbody").find("tr", 0).find("td", 1).getText()).isEqualTo("Vanna");
		assertThat(getTable().find("tbody").find("tr", 0).find("td", 2).getText()).isEqualTo("Salas");
		assertThat(getTable().find("tbody").find("tr", 0).find("td", 4).getText()).isEqualTo("bibendum.fermentum.metus@ante.ca");
	}
	
	@Test
	public void should_generate_select_in_the_footer() throws Exception {
		goToPage("basics/filtering_with_select");
		
		// A tfoot tag must be generated
		assertThat(getTable().find("tfoot")).hasSize(1);
				
		// Now we test that drop down lists exist in the footer
		assertThat(getTable().find("tfoot").find("span")).hasSize(2);
		assertThat(getTable().find("tfoot").find("select")).hasSize(2);
		
		// All drop down list must contain as many options than the data source, minus duplicates and null values
		assertThat(getTable().find("tfoot").find("select", 0).find("option")).hasSize(688);
		assertThat(getTable().find("tfoot").find("select", 1).find("option")).hasSize(649);
		
		// All other footer cells must have the same content as the header ones
		assertThat(getTable().find("thead").find("th", 0).getValue()).isEqualTo(getTable().find("tfoot").find("th", 0).getValue());
		assertThat(getTable().find("thead").find("th", 3).getValue()).isEqualTo(getTable().find("tfoot").find("th", 3).getValue());
		assertThat(getTable().find("thead").find("th", 4).getValue()).isEqualTo(getTable().find("tfoot").find("th", 4).getValue());
	}
	
	@Test
	public void should_filter_data_when_using_an_extra_form() throws Exception {
		goToPage("basics/filtering_with_extra_form");
		
		assertThat(find("#firstNameFilter").find("span.filter_column")).hasSize(1);
		assertThat(find("#firstNameFilter").find("span.filter_column").find("select.dandelion_select_filter")).hasSize(1);
		assertThat(find("#lastNameFilter").find("span.filter_column")).hasSize(1);
		assertThat(find("#lastNameFilter").find("span.filter_column").find("select.dandelion_select_filter")).hasSize(1);
		assertThat(find("#cityFilter").find("span.filter_column")).hasSize(1);
		assertThat(find("#cityFilter").find("span.filter_column").find("select.dandelion_select_filter")).hasSize(1);
	}
	
	@Test
	public void should_generate_a_filtering_listbox_with_predefined_values(){
		goToPage("basics/filtering_with_select_with_predefined_values");
		
		assertThat(getTable().find("tfoot").find("tr", 0).find("th", 2).find("select")).hasSize(1);
		assertThat(getTable().find("tfoot").find("tr", 0).find("th", 2).find("select").find("option")).hasSize(4);
		assertThat(getTable().find("tfoot").find("tr", 0).find("th", 2).find("select").find("option", 0).getText()).isEqualTo("LastName");
		assertThat(getTable().find("tfoot").find("tr", 0).find("th", 2).find("select").find("option", 1).getText()).isEqualTo("val1");
		assertThat(getTable().find("tfoot").find("tr", 0).find("th", 2).find("select").find("option", 2).getText()).isEqualTo("val2");
		assertThat(getTable().find("tfoot").find("tr", 0).find("th", 2).find("select").find("option", 3).getText()).isEqualTo("val3");
	}
}