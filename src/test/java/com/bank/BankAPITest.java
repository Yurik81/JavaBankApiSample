package com.bank;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.easymock.EasyMock.*;

/**
 * Created by eivanchenko on 7/11/14.
 * Modified by ykuzub on 8/15/14.
 */
public class BankAPITest {

    private ProcessingCenter mock;
    private BankAPI bankApi;

    @Before
    public void setUp() throws Exception {
        mock = createMock(ProcessingCenter.class);
        bankApi = new BankAPI();
        bankApi.setProcessingCenter(mock);
    }

    @After
    public void tearDown() throws Exception {
        verify(mock);
    }

    @Test(expected = RuntimeException.class)
    public void testGetBalanceWhenAccountNotExist() throws Exception {
        expect(mock.getBalance("NonExistingAccount")).andThrow(new RuntimeException());
        replay(mock);
        double result = bankApi.getBalance("NonExistingAccount");
    }

    @Test
    public void testGetBalance() throws Exception {
        expect(mock.getBalance("Customer1")).andReturn(100.00);
        replay(mock);
        double result = bankApi.getBalance("Customer1");
        Assert.assertTrue(result==100.00);
    }

    @Test
    public void testSetBalance() throws Exception {
        mock.setBalance("Customer2", 100.00);
        replay(mock);
        bankApi.setBalance("Customer2", 100.00);
    }

    @Test
    public void testDecrease() throws Exception {
        expect(mock.getBalance("Customer3")).andReturn(3500.00);
        mock.decreaseAccount("Customer3", 500.00);
        replay(mock);
        bankApi.decrease("Customer3", 500.00);
    }

    @Test
    public void testIncrease() throws Exception {
        expect(mock.getBalance("Customer3")).andReturn(3000.00);
        mock.increaseAccount("Customer3", 300.00);
        replay(mock);
        bankApi.increase("Customer3", 300.00);
    }

    @Test
    public void testTransfer() throws Exception {
        expect(mock.getBalance("Customer4")).andReturn(8000.00);
        expect(mock.getBalance("Customer5")).andReturn(5000.00);
        mock.transfer("Customer4", "Customer5", 1000.00);
        replay(mock);
        bankApi.transfer("Customer4", "Customer5", 1000.00);
    }

}