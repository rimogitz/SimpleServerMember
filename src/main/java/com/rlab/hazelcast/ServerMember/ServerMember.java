package com.rlab.hazelcast.ServerMember;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.Client;
import com.hazelcast.core.ClientListener;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Partition;
import com.hazelcast.core.PartitionService;
import com.hazelcast.instance.HazelcastInstanceImpl;
import com.hazelcast.instance.HazelcastInstanceProxy;
import com.hazelcast.internal.partition.InternalPartition;
import com.hazelcast.internal.partition.InternalPartitionService;
import com.hazelcast.internal.partition.impl.InternalPartitionImpl;
import com.hazelcast.nio.Address;
public class ServerMember {
    public static void main(String[] args) {
    	Config config;
		try {
			config = new FileSystemXmlConfig("src/main/resources/hazelcast.xml");
			HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}


//vm options -Xms4G -Xmx4G -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseG1GC -Dhazelcast.partition.count=271