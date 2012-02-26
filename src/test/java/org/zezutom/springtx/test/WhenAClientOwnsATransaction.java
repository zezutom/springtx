package org.zezutom.springtx.test;

import javax.annotation.Resource;
import org.zezutom.springtx.client.BookingClient;

/**
 *
 * @author tomasz
 */
public class WhenAClientOwnsATransaction extends BookingTestCase {

    @Resource(name="bookingDAOClient")
    private BookingClient client;

    @Override
    protected BookingClient getClient() {
        return client;
    }
        
}
