import java.awt.*;
import java.awt.event.*;
class ColorMixer extends WindowCloser implements AdjustmentListener,ActionListener,FocusListener,KeyListener
{
	private Frame f;
	private Label lred,lgreen,lblue;
	private Label lr,lg,lb,ll;
	private TextField tred,tgreen,tblue;
	private Scrollbar sred,sgreen,sblue;
	private Color cl;
	private	Panel p1,p2,p3,p4,p5,p6;
	private Button blike,b[];
	private int count;
	ColorMixer()
	{
			
		Font fnt=new Font("Arial",Font.BOLD,20);
		
		lred=new Label("Red",Label.RIGHT); //RIGHT ALIGNMENT GIVEN
		lgreen=new Label("Green",Label.RIGHT);
		lblue=new Label("Blue",Label.RIGHT);
		lred.setFont(fnt);
		lgreen.setFont(fnt);
		lblue.setFont(fnt);
		lred.setForeground(Color.RED);
		lgreen.setForeground(Color.GREEN);
		lblue.setForeground(Color.BLUE);
	
		lr=new Label("R",Label.CENTER);
		lg=new Label("G",Label.CENTER);
		lb=new Label("B",Label.CENTER);
		lr.setFont(fnt);
		lg.setFont(fnt);
		lb.setFont(fnt);
		lr.setForeground(Color.RED);
		lg.setForeground(Color.GREEN);
		lb.setForeground(Color.BLUE);

		sred=new Scrollbar(Scrollbar.VERTICAL,140,10,0,265); //range of colors is 255 but since slider width is 10 and scrollbar value is taken according to the top of slider that's why here we are using 265
		sgreen=new Scrollbar(Scrollbar.VERTICAL,45,10,0,265);
		sblue=new Scrollbar(Scrollbar.VERTICAL,90,10,0,265);
		sred.setBackground(Color.RED);
		sgreen.setBackground(Color.GREEN);
		sblue.setBackground(Color.BLUE);
		sred.addAdjustmentListener(this);
		sgreen.addAdjustmentListener(this);
		sblue.addAdjustmentListener(this);
		
		cl=new Color(sred.getValue(),sgreen.getValue(),sblue.getValue());
		
		tred=new TextField(Integer.toString(sred.getValue()),1);
		//tred=new TextField(sred.getValue()+"",1);  can do in this manner also
		tgreen=new TextField(Integer.toString(sgreen.getValue()),1);
		tblue=new TextField(Integer.toString( sblue.getValue()),1);
		
		tred.addFocusListener(this);
		tgreen.addFocusListener(this);
		tblue.addFocusListener(this);
		tred.addKeyListener(this);
		tgreen.addKeyListener(this);
		tblue.addKeyListener(this);
		
		blike=new Button("LIKE");
		blike.addActionListener(this);
		
		b=new Button[10];
		for(int i=0;i<10;i++)
		{
			b[i]=new Button();
			b[i].addActionListener(this);
		}

		p1=new Panel();
		p2=new Panel();
		p3=new Panel();
		p4=new Panel();
		p5=new Panel();
		p6=new Panel();
		
		p1.setBackground(cl);
	
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setBackground(Color.LIGHT_GRAY);
		p2.add(lred);
		p2.add(tred);
		p2.add(lgreen);
		p2.add(tgreen);
		p2.add(lblue);
		p2.add(tblue);
		p2.add(blike);

		p3.setLayout(new GridLayout(1,3));
		p3.add(sred);
		p3.add(sgreen);
		p3.add(sblue);

		p4.setLayout(new GridLayout(1,3));
		p4.setBackground(Color.LIGHT_GRAY);
		p4.add(lr);
		p4.add(lg);
		p4.add(lb);

		p5.setLayout(new BorderLayout());
		p5.add(p4,BorderLayout.NORTH);
		p5.add(p3);
		
		p6.setBackground(Color.LIGHT_GRAY);
		p6.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ll=new Label("Liked Colors:");
		p6.add(ll);
		ll.setFont(fnt);
		ll.setVisible(false);
					
		f=new Frame();
		f.setSize(400,400);
		f.setLocation(200,200);
		f.add(p1);
		f.add(p2,BorderLayout.SOUTH);
		f.add(p5,BorderLayout.EAST);
		f.add(p6,BorderLayout.NORTH);
		WindowCloser wc=new WindowCloser();
		f.addWindowListener(wc);
		f.setVisible(true);
	
	}
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		Object o=e.getSource();
		int x=e.getValue();
		Color c;
		if(o==sred)
		{
			tred.setText(Integer.toString(x));
			c=new Color(x,sgreen.getValue(),sblue.getValue());
		}
		else if(o==sgreen)
		{
			tgreen.setText(Integer.toString(x));
			c=new Color(sred.getValue(),x,sblue.getValue());
		}
		else 
		{
			tblue.setText(Integer.toString(x));
			c=new Color(sred.getValue(),sgreen.getValue(),x);
		}
		cl=c;
		p1.setBackground(cl);
	}
	public void actionPerformed(ActionEvent e)
	{
		Button b1=(Button)e.getSource();
		if(b1==blike)
		{	if(count<10)
			{
				ll.setVisible(true);
				b[count].setBackground(cl);
				p6.add(b[count]);
				count++;
				//ll.setForeground(cl);
				f.validate();
			}
			else
			{
				blike.setEnabled(false);
			}
		}
		else if(b1==b[0] || b1==b[1] || b1==b[2] || b1==b[3] || b1==b[4] || b1==b[5] || b1==b[6] || b1==b[7] || b1==b[8] || b1==b[9]  )
		{
			Color c;
			c=b1.getBackground();
			p1.setBackground(c);
			sred.setValue(c.getRed());
			sgreen.setValue(c.getGreen());
			sblue.setValue(c.getBlue());
			tred.setText(c.getRed()+"");
			tgreen.setText(c.getGreen()+"");
			tblue.setText(c.getBlue()+"");
		}
	}
	public void focusGained(FocusEvent e)
	{
	}
	public void focusLost(FocusEvent e)
	{	try
		{
			Object o=e.getSource();
			Integer in;
			int i;
			if(o==tred)
			{
				i=Integer.parseInt(tred.getText());
				if(i>255)
				{
					i=255;
					tred.setText(i+"");
				}
				in=new Integer(tred.getText());
				sred.setValue(in);
			}
			else if(o==tgreen)
			{
				i=Integer.parseInt(tgreen.getText());
				if(i>255) //so that if no>255 is entered then it would be set =255 and here there is no issue of negative nos as in keypressed minus is disabled
				{
					i=255;
					tgreen.setText(i+"");
				}
				in=new Integer(tgreen.getText());
				sgreen.setValue(in);
				
			}
			else if(o==tblue)
			{
				i=Integer.parseInt(tblue.getText());
				if(i>255)
				{
					i=255;
					tblue.setText(i+"");
				}
				in=new Integer(tblue.getText());
				sblue.setValue(in);
				tred.requestFocus(); //so that after tblue when press tab then focus comes directly on tred otherwise focus will first go on blike then scrollbars and then will come on tred
			}
			Color c=new Color(sred.getValue(),sgreen.getValue(),sblue.getValue());
			p1.setBackground(c);
		}
		catch(NumberFormatException ei)
		{
		}
	}
	public void keyPressed(KeyEvent e)
	{
		Object o=e.getSource();
		int x=e.getKeyCode();
		if(x>=KeyEvent.VK_0 && x<=KeyEvent.VK_9 )
		{
		}
		else if(x>=KeyEvent.VK_NUMPAD0 && x<=KeyEvent.VK_NUMPAD9)
		{
		}
		else if(x==KeyEvent.VK_DELETE || x==KeyEvent.VK_BACK_SPACE)
		{
		}
		else if(x==KeyEvent.VK_LEFT || x==KeyEvent.VK_RIGHT)
		{
		}
		else
		{
			e.consume();
		}
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public static void main(String args[])
	{
		ColorMixer cm=new ColorMixer();
	}
}