import java.io.*;
class Merger
{
	public static void main(String args[])throws IOException
	{
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.println("enter the path where the directory containing files to be merged are placed");
		String s1,s2,s3;
		s1=sc.next();
		System.out.println("enter the name of the file to be merged");
		s2=sc.next();
		s3=s1+"_Splitted_"+s2.substring(0,s2.indexOf("."));
		System.out.println(s3);
		FileOutputStream fos=new FileOutputStream(s1+s2);
		File f[],f1;
		f1=new File(s3);
		f=f1.listFiles();
		int i=1;
		for(int j=0;j<f.length;j++)
		{	
			FileInputStream fis;
			fis=new FileInputStream(s3+"/tmp_"+i+".txt");
			byte b[]=new byte[fis.available()];
			fis.read(b);	
			fos.write(b);
			i++;
		}	
	}
}