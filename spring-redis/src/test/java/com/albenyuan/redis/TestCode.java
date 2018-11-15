package com.albenyuan.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @Author Alben Yuan
 * @Date 2018-11-09 11:50
 */

public class TestCode {

    private static final Logger logger = LoggerFactory.getLogger(TestCode.class);


    @Test
    public void property() throws UnknownHostException {
        Properties properties = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();


        for (String key : properties.stringPropertyNames()) {
            logger.info("{}: {}", key, properties.get(key));
        }

        for (String key : map.keySet()) {
            logger.info("{}: {}", key, map.get(key));
        }
        logger.info("本地ip地址:" + ip);
    }

    @Test
    public void memory() {
        Runtime runtime = Runtime.getRuntime();
        logger.info("total: {}MB", runtime.totalMemory() / 1000 / 1000);
        logger.info("max: {}MB", runtime.maxMemory() / 1000 / 1000);
        logger.info("free: {}MB", runtime.freeMemory() / 1024 / 1024);
        logger.info("available Processors: {}", runtime.availableProcessors());
    }

    @Test
    public void network() throws SocketException {
        Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
        while (networks.hasMoreElements()) {

            NetworkInterface network = networks.nextElement();

            logger.info("Name: {}", network.getName());
            logger.info("network: {}", network.getName());
            logger.info("MTU : {}", network.getMTU());
            logger.info("Display Name: {}", network.getDisplayName());
            logger.info("Hardware Address: {},{}", network.getHardwareAddress());

            Enumeration<InetAddress> addresses = network.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
//                if (!address.isLoopbackAddress() && !address.isLinkLocalAddress() && address.isSiteLocalAddress()) {
                logger.info("Address : {}", address.getHostAddress());
                logger.info("Host Name : {}", address.getHostName());
//                }
            }
            logger.info("\n");

        }
    }

    @Test
    public void localIP() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        logger.info("Name: {}", address.getHostName());
        logger.info("Host Address: {}", address.getHostAddress());
        logger.info("Address: {} ", address.getAddress());
    }


    private static void cpu() {


    }

    private static void printCpuPerc() {
    }

    private static void os() {
    }

    private static void who() {
    }

    private static void file() {
    }

    private static void net() {
    }

    private static void ethernet() {

    }
}
