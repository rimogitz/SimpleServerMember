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
public class ServerMember_PartitionCheck {
    public static void main(String[] args) {
    	Config config;
		try {
			config = new FileSystemXmlConfig("src/main/resources/hazelcast.xml");
			HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Map<String,String> imap = hz.getMap("Map1");
			imap.put("ONE", "uno");
			PartitionService pt = hz.getPartitionService();
			Partition p1 =  pt.getPartition("ONE");
			int partitionID = p1.getPartitionId();
			System.out.println("Owning Member for Key ONE" +p1.getOwner()+" Partition ID :: "+partitionID);
			
			HazelcastInstanceProxy hcp = (HazelcastInstanceProxy)hz;
			HazelcastInstanceImpl hazII = hcp.getOriginal();
			
			InternalPartitionService i_ps = hazII.node.getPartitionService();
			InternalPartition i_p = i_ps.getPartition(partitionID);
			
			int pid_2 = i_ps.getPartitionId("ONE");
			System.out.println("PID ::"+pid_2);
			System.out.println("partitionID ::"+partitionID);
			
			InternalPartitionImpl internalPartitionImpl =(InternalPartitionImpl)i_ps.getPartition(pid_2);
			
			for (int i = 0 ; i < 7 ; i++) {
			Address address =
					i_p.getReplicaAddress(i);

			if (address!=null) {

					System.out.printf(String.format(i+" X Partition %d copy %d on %s",	pid_2, i, address));
					System.out.println("\n");

				}
			}
			/*System.out.println(partitionID+" :"+ internalPartitionImpl);
			System.out.printf("Partition {} master: {}", "ONE", internalPartitionImpl.getReplicaAddress(0));
			System.out.println("\n");
			System.out.printf("Partition {} backup: {}", "ONE", internalPartitionImpl.getReplicaAddress(1));*/
					
			
		    
			
					
					
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}


//vm options -Xms4G -Xmx4G -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseG1GC -Dhazelcast.partition.count=271