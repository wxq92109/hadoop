package cn.edu.gzu.hadoop.hdfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HDFSDemo {
	FileSystem fs = null;
	@Before
	public void init() throws IOException, URISyntaxException, InterruptedException
	{
		fs = FileSystem.get(new URI("hdfs://192.168.101.26:9000"),new Configuration(),"root");
	}
	@Test
	public void testUpload() throws IllegalArgumentException, IOException{
		//��ȡ�����ļ�ϵͳ���ļ�
		InputStream in = new FileInputStream("D:\\greenplum-db-4.2.8.5-build-1-RHEL5-x86_64.zip");
		//��HDFS�ϴ���һ���ļ�������������
		OutputStream out = fs.create(new Path("greenplum.jar"));
		//���� --> ���
		IOUtils.copyBytes(in, out, 4096,true);
		
	}
	@Test
	public void testDownload2() throws IllegalArgumentException, IOException{
		fs.copyFromLocalFile(new Path("/user/root/greenplum.jar"), new Path("D:\\hadooptest"));
	}
	
	@Test
	public void testDownload() throws IllegalArgumentException, IOException
	{
		InputStream in = fs.open(new Path("/user/root/greenplum.jar"));
		OutputStream out = new FileOutputStream("D://hadoopfiletest");
		IOUtils.copyBytes(in, out, 4096, true);
	}
	@Test
	public void testDelete() throws IllegalArgumentException, IOException{
		fs.delete(new Path("/user/root/greenplum.jar"),false);//false true ����ȷ���ǲ��ǵݹ�ɾ��
	}
	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testMkdir() throws IllegalArgumentException, IOException
	{
		boolean mkdir = fs.mkdirs(new Path("/testMkdir"));
		System.out.println("�����ļ�"+mkdir);
	}

}
