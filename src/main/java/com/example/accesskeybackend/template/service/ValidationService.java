package com.example.accesskeybackend.template.service;

import com.example.accesskeybackend.exception.IllegalArgumentException;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;

@Service
@Log4j2
public class ValidationService {

    public boolean checkIpv6(String url) {
        log.info("Checking url: {}", url);
        InetAddress[] address = getAddress(url);
        return Arrays.stream(address).anyMatch(inetAddress -> ipV6AddressChecker(inetAddress.getHostAddress()));
    }

    private InetAddress[] getAddress(String url) {
        try {
            return InetAddress.getAllByName(new URL(url).getHost());
        } catch (UnknownHostException | MalformedURLException e) {
            log.warn(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private boolean ipV6AddressChecker(String address) {
        log.info("Checking address: {}", address);
        IPAddress ipAddress = new IPAddressString(address).getAddress();
        return ipAddress.getIPVersion().isIPv6();
    }
}
