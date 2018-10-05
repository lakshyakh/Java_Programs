import java.io.*;
class Splitter
{
	public static void main(String args[])throws IOException
	{
		FileInputStream fis;
		String s1,s2,s3;
		
		System.out.println("enter the name and path of the file to be splitted :");
		java.util.Scanner sc=new java.util.Scanner(System.in);
		s1=sc.nextLine();
		fis=new FileInputStream(s1);
		
		System.out.println("enter the no of parts in which file is to be divided :");
		int x;
		x=sc.nextInt();
		
		File f1=new File(s1);
		s2=f1.getParent();
		int idir=1;
		s3=s2+"_Splitted_"+f1.getName();
		s3=s3.substring(0,s3.indexOf("."));
		while(true)
		{
			File f2=new File(s3);
			boolean bo=f2.mkdir();
			if(bo==true)
			{
				break;
			}
			else
			{
				s3=s2+"_Splitted_"+f1.getName();
				s3=s3.substring(0,s3.indexOf("."));
				s3+=idir;
				idir++;
			}
			System.out.println("directory made?"+bo);
		}
	
		int i=1,count;
		byte b[]=new byte[fis.available()/x];
		while((count=fis.read(b))!=-1)
		{
			FileOutputStream fos;
			fos=new FileOutputStream(s3+"/tmp_"+i+".txt");
			System.out.println(s3+"/tmp_"+i+".txt" + " having " + count + " no. of bytes");
			fos.write(b,0,count);
			fos.close();
			i++;
		}
		fis.close();	
	}
}