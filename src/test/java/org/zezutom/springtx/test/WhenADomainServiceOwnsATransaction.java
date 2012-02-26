package org.zezutom.springtx.test;

import javax.annotation.Resource;
import org.zezutom.springtx.client.BookingClient;

/**
 *
 * @author tomasz
 */
public class WhenADomainServiceOwnsATransaction extends BookingTestCase {

    @Resource(name="bookingServiceClient")
    private BookingClient client;
    
    @Override
    protected BookingClient getClient() {
        return client;
    }

    
}
