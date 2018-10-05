import java.awt.*;
import java.awt.event.*;
class NokiaSnake1 extends Thread implements KeyListener,ActionListener 
{
	private Frame f;
	private Button bplay;
	private Graphics g;
	private int x=0,y=100,width=100,height=10,tx,twidth,ty,theight,ux,uy,uwidth,uheight,t;
	private int cx,cy,a=0,b=0;
	private boolean flag=true,flag1=true;
	private static boolean flag2; 
	private Color c;
	
	NokiaSnake1()
	{
		
		f=new Frame("Snake");
		f.setSize(450,450);
		f.setLocation(250,250);
		f.setLayout(new GridBagLayout());
		WindowCloser wc=new WindowCloser();
		f.addWindowListener(wc);
		f.setResizable(false);
		f.addKeyListener(this);
		
		bplay=new Button("Play");
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		f.add(bplay,gbc);
		bplay.addActionListener(this);

		
		
		f.setVisible(true);
				
		g=f.getGraphics();
		
	}
	public void actionPerformed(ActionEvent e)
	{
		bplay.setVisible(false);

		this.start();
	}
 public void keyPressed(KeyEvent e)
	{
		Object o=e.getSource();
		if(o==f)
		{
			int x=e.getKeyCode();
			if(x==KeyEvent.VK_UP)
			{
				
				
			}
			else if(x==KeyEvent.VK_DOWN)
			{
				
				flag1=false;
			}
			else if(x==KeyEvent.VK_LEFT)
			{
					
				flag1=false;
			}
			else if(x==KeyEvent.VK_RIGHT)
			{
				
				flag1=false;
			}
			else
			{
				e.consume();
			}
			flag=false;
		}
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
 public void run()
	{
	cx=(int)(Math.random()*1000);
		cy=(int)(Math.random()*1000);
		while(a==0)
		{
			if(cx<0 || cx>440)
			{
				cx=(int)(Math.random()*10);
			}
			else
			{
				a=1;
			}
		}
		while(b==0)
		{
			if(cy<0 || cy>440)
			{
				cy=(int)(Math.random()*10);
			}
			else
			{
				b=1;
			}
		}
				g.fillOval(cx,cy,10,10);
		while(flag==true)
		{
			flag2=false;
			c=Color.white;  //so that previous rect are removed otherwise a complete line equal to frame width is drawn
			g.setColor(c);
			g.fillRect(x-width,y,width,height);
			x++;
			c=Color.black;  //so that new rect drawn are visible 
			g.setColor(c);
			g.fillRect(x,y,width,height);
			if(x==450)
			{
				x=tx;
				tx=0;
				twidth=0;
			}
			else if(x>=350 && x<450) //so that as it enters the right border of frame ,it must start appearing from left
			{
				c=Color.white;
				g.setColor(c);
				g.fillRect(tx-width,y,twidth,height);
				c=Color.black;
				g.setColor(c);
				g.fillRect(tx,y,twidth,height);
				tx++;
				twidth++;
			}
			try
			{
				sleep(10);  //otherwise it occurs very fast
			}
			catch(InterruptedException e)
			{
			}
		}
		notifyAll();
	}
	public static void main(String args[])
	{
		NokiaSnake1 ns=new NokiaSnake1();
	}
}
