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
package ca.travelagency.salesreports;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ca.travelagency.BaseWicketTester;
import ca.travelagency.invoice.InvoiceHelper;
import ca.travelagency.persistence.DaoEntity;
import ca.travelagency.salesstats.PaymentDistribution;

import com.google.common.collect.Lists;


public class PaymentModelTest extends BaseWicketTester {

	@Test
	public void testLoad() throws Exception {
		// setup
		List<DaoEntity> invoicePayments = Lists.newArrayList((DaoEntity) InvoiceHelper.makePayment());
		SalesSearch salesSearch = SalesSearch.make(null);
		PaymentModel fixture = PaymentModel.make(salesSearch);
		// expected
		Mockito.when(daoSupport.find(salesSearch.getInvoicePaymentCriteria())).thenReturn(invoicePayments);
		// execute
		PaymentDistribution actual = fixture.load();
		// validate
		Assert.assertEquals(invoicePayments.size(), actual.getInvoicePayments().size());
		Assert.assertEquals(invoicePayments.get(0), actual.getInvoicePayments().iterator().next());

		Mockito.verify(daoSupport).find(salesSearch.getInvoicePaymentCriteria());
	}
}
