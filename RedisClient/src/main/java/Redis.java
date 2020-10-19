import java.net.Socket;
import java.util.HashSet;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisException;

public class Redis {

        private	static	final	String	MASTER_NAME	= "mymaster";
        public	static	final	String	PASSWORD	= "mypass";
        public	static	final	int		DATABASE	= 0;

        public static void main( String[] args )
        {
            HashSet <String>			oHashSetSetSentinel			= null;
            GenericObjectPoolConfig	    oGenericObjectPoolConfig	= null;
            JedisSentinelPool		    oJedisSentinelPool			= null;
            Jedis					    oJedis						= null;
            Socket					    oSocket						= null;
            String					    strValue					= "";

            // Sentinel의 ip와 port setting
            oHashSetSetSentinel = new HashSet <String>();
            oHashSetSetSentinel.add("192.168.100.135:26379");
            oHashSetSetSentinel.add("192.168.100.245:26379");
            oHashSetSetSentinel.add("192.168.100.143:26379");

            // Sentinel Pool에 연결한다.
            oGenericObjectPoolConfig = new GenericObjectPoolConfig();
            oJedisSentinelPool = new JedisSentinelPool(MASTER_NAME, oHashSetSetSentinel, oGenericObjectPoolConfig, 1000);
            //oJedisSentinelPool = new JedisSentinelPool(MASTER_NAME, oHashSetSetSentinel, oGenericObjectPoolConfig, 1000,"password1234");

            // set
            for ( int i = 0; i < 100; i++ )
            {
                try
                {
                    // pool에서 jedis master를 가져온다.
                    oJedis = oJedisSentinelPool.getResource();

                    // master가 어떤 server인지 확인하기 위한 code
                    oSocket = oJedis.getClient().getSocket();
                    System.out.println("Connected to " + oSocket.getRemoteSocketAddress());

                    // set
                    oJedis.set("foo_" + i, "bar_" + i);
                    System.out.println("set foo_" + i);
                }
                catch ( JedisException ex)
                {
                    ex.printStackTrace();
                    break;
                }
                finally
                {
                    // jedis 객체 반환
                    oJedis.close();
                }

            }

            // get
            for ( int i = 0; i < 100; i++ )
            {
                try
                {
                    // pool에서 jedis master를 가져온다.
                    oJedis = oJedisSentinelPool.getResource();

                    // master가 어떤 server인지 확인하기 위한 code
                    oSocket = oJedis.getClient().getSocket();
                    System.out.println("Connected to " + oSocket.getRemoteSocketAddress());

                    // get
                    strValue = oJedis.get("foo_" + i);
                    System.out.println("get foo_" + i + ": [" + strValue + "]");
                }
                catch ( JedisException ex)
                {
                    ex.printStackTrace();
                    break;
                }
                finally
                {
                    // jedis 객체 반환
                    oJedis.close();
                }

            }
            // Sentinel Pool 반환
            oJedisSentinelPool.destroy();
        }

}
