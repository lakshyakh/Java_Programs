import java.awt.*;
import java.awt.event.*;
class NokiaSnake2 implements KeyListener,ActionListener 
{
	private Frame f;
	private Button bplay;
	private Graphics g;
	public static int count=0;
	NokiaSnake2()
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
		Circle c=new Circle();
		count=1;
		while(count==1)
		{
			Line l=new Line();
			l.start();
		}
	}
	public void keyPressed(KeyEvent e)
	{
		Object o=e.getSource();
		if(o==f)
		{
			int x=e.getKeyCode();
			if(x==KeyEvent.VK_UP)
			{
				count=2;
				while(count==2)
				{
					Up u=new Up();
					u.start();
				}
			}
			else if(x==KeyEvent.VK_DOWN)
			{
				count=3;
				Down d=new Down();
				d.start();
			}
			else if(x==KeyEvent.VK_LEFT)
			{
				count=4;
				Left l=new Left();
				l.start();
			}
			else if(x==KeyEvent.VK_RIGHT)
			{
				count=5;
				Right r=new Right();
				r.start();
			}
			else
			{
				e.consume();
			}
		}
	
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public static void main(String args[])
	{
		NokiaSnake2 ns2=new NokiaSnake2();
	}
}
class Line   extends Thread 
{
	public void run()
	{
		System.out.println("line moving");
	}
}
class Circle   extends Thread 
{
	public void run()
	{
		System.out.println("Circle drawn");
	}
}
class Up   extends Thread 
{
	public void run()
	{
		System.out.println("line moving up");
	}
}
class Down   extends Thread 
{
	public void run()
	{
		while(NokiaSnake2.count==3)
		{	
			System.out.println("line moving down");
		}
	}
}
class Left   extends Thread 
{
	public void run()
	{
		while(NokiaSnake2.count==4)
		{
		System.out.println("line moving left");
		}
	}
}
class Right   extends Thread 
{
	public void run()
	{
		while(NokiaSnake2.count==5)
		{
		System.out.println("line moving right");
		}
	}
}