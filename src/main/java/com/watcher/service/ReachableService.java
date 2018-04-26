package com.watcher.service;



/**
 * Interface for checking ping or telnet reachable of URL.
 * 
 * @author Aendy
 *
 */
public interface ReachableService {

    /**
     * Method for checking ping connection of URL.
     * 
     * @param host to ping
     * 
     * @return result of ping
     */
    public String checkPing(String host);

    /**
     * Method for checking telnet connection of URL.
     * 
     * @param host host to check
     * @param port port to check
     * 
     * @return result of telnet
     */
    public String checkTelnet(String host, 
            Integer port);

}
