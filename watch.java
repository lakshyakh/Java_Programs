import java.util.*;
import java.awt.*;
class Watch extends Thread 
{
	private Label lbldate , lbltime;
	Watch()
	{
		lbldate=new Label();
		lbltime=new Label();
		start();
	}
	public void run()
	{
		int dt,mm,yy,hh,mn,ss,am_pm;
		Calendar c;
		String s,time,date;
		while(true)
		{
			c=Calendar.getInstance();
			dt=c.get(Calendar.DATE);
			mm=c.get(Calendar.MONTH)+1;
			yy=c.get(Calendar.YEAR);
			hh=c.get(Calendar.HOUR);
			mn=c.get(Calendar.MINUTE);
			ss=c.get(Calendar.SECOND);
			am_pm=c.get(Calendar.AM_PM);
			if(am_pm==Calendar.AM)
			{
				s="AM";
			}
			else
			{
				s="PM";
			}
			time=" "+hh+":"+mn+":"+ss+" "+s;
			date=" "+dt+"/"+mm+"/"+yy;
			lbldate.setText(date);
			lbltime.setText(time);
			try
			{
				sleep(1000);
			}
			catch(InterruptedException e)
			{
			}
		}
	}
		public Label getDateLabel()
		{
			return lbldate;
		}
		public Label getTimeLabel()
		{
			return lbltime;
		}
}