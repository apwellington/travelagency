/**
 *    Copyright (C) 2010 - 2014 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ca.travelagency.invoice;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ca.travelagency.components.formheader.DaoEntityModelFactory;
import ca.travelagency.customer.Customer;
import ca.travelagency.customer.CustomerPage;

public class InvoiceCustomerPanel extends Panel {
	private static final long serialVersionUID = 1L;

	static final String LINK = "link";

	public InvoiceCustomerPanel(String id, Customer customer) {
		this(id, DaoEntityModelFactory.make(customer));
	}

	private InvoiceCustomerPanel(String id, final IModel<Customer> model) {
		super(id, model);

		Link<Invoice> link = new Link<Invoice>(LINK) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(new CustomerPage(model.getObject()));
			}
		};
		link.add(new Label(Customer.PROPERTY_NAME, Model.of(model.getObject().getName())));
		add(link);
		add(new Label(Customer.Properties.companyName.name()));
		add(new Label(Customer.Properties.primaryPhone.name()));
	}

}
