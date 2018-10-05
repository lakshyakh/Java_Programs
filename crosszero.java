import java.awt.*;
import java.awt.event.*;
class CrossZero extends WindowCloser implements ActionListener,MouseListener,WindowListener
{
	private Frame f;
	private Panel p[],p1,p2,p3,p4,po2;
	private Graphics g[];
	private Button bplay,bnewgame,bexit;
	private Label pl1,pl2,lp,lp1,lp2;
	private int i,j[],k,a[],b[],r,s,v[][],fc,l,po,pos,fcg;
	private boolean count,t;
	private Dialog wins;
	CrossZero()
	{
		f=new Frame("Cross-Zero");
		f.setSize(450,450);
		f.setLocation(250,250);
		f.setLayout(new GridLayout(4,3));
		
		bplay=new Button("NewGame");
		bplay.setSize(50,50);
		bplay.addActionListener(this);
		
		pl1=new Label("Player 1  :  O");
		pl2=new Label("Player 2  :  X");
		
		p=new Panel[9];
		for(i=0;i<9;i++)
		{
			p[i]=new Panel();
			p[i].addMouseListener(this);
		}
		i=0;
		while(i!=9)
		{
			if(i%2==0)
			{
					p[i].setBackground(Color.BLACK);
			}
			i++;
		}

		p1=new Panel();
		p2=new Panel();
		p3=new Panel();
		p4=new Panel();
		p1.setLayout(new GridLayout(0,3));
		p2.setLayout(new GridLayout(0,3));
		p3.setLayout(new GridLayout(0,3));
		p4.setLayout(new GridBagLayout());
		
		p1.add(p[0]);
		p1.add(p[1]);
		p1.add(p[2]);
		p2.add(p[3]);
		p2.add(p[4]);
		p2.add(p[5]);
		p3.add(p[6]);
		p3.add(p[7]);
		p3.add(p[8]);
		GridBagConstraints gbc;
		gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		p4.add(bplay,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		p4.add(pl1,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		p4.add(pl2,gbc);
		
		bnewgame=new Button("New Game");
		bnewgame.addActionListener(this);
		bexit=new Button("Exit");
		bexit.addActionListener(this);

		po2=new Panel();
		lp=new Label();
		lp1=new Label();
		lp2=new Label("Nobody Wins !!");

		po2.add(bnewgame);
		po2.add(bexit);
		wins =new Dialog(f,"Cross-Zero",true);
		wins.setLocationRelativeTo(f);
		wins.setSize(300,150);
		wins.setLayout(new GridLayout(3,1));
		wins.setResizable(false);
		
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.add(p4);
		f.addWindowListener(new WindowCloser());
		
		g=new Graphics[9];
		
		j=new int[20];
		a=new int[6];
		b=new int[5];
		v=new int[8][];
		v[0]=new int[]{0,1,2};
		v[1]=new int[]{3,4,5};
		v[2]=new int[]{6,7,8};
		v[3]=new int[]{0,3,6};
		v[4]=new int[]{1,4,7};
		v[5]=new int[]{2,5,8};
		v[6]=new int[]{0,4,8};
		v[7]=new int[]{2,4,6};
		
		f.setResizable(false);
		
		f.setVisible(true);
		
		for(i=0;i<9;i++)
		{
			g[i]=p[i].getGraphics();
		}
		i=0;
		while(i!=9)
		{
			if(i%2==0)
			{
					g[i].setColor(Color.WHITE);
			}
			i++;
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		if(o==bplay || o==bnewgame)
		{
			if(t==false)
			{
				f.setSize(451,451);
				newgame();
				if(o==bnewgame)
				{
					wins.setVisible(false);
					wins.dispose();
				}
				t=true;
			}
			else
			{
				f.setSize(450,450);
				newgame();
				if(o==bnewgame)
				{
					wins.setVisible(false);
					wins.dispose();
				}
				t=false;
			}
		}
		else if(o==bexit)
		{
			f.setVisible(false);
			f.dispose();
			System.exit(1);
		}
	}
	void newgame()
	{
		for(i=0;i<20;i++)
		{
			j[i]=-1;
		}
		for(i=0;i<6;i++)
		{
			a[i]=-1;
		}
		for(i=0;i<5;i++)
		{
			b[i]=-1;
		}
		k=0;
		r=0;
		s=0;
		fc=0;
		fcg=0;
		count=false;
	}
	public void mouseEntered(MouseEvent e)
	{}
	public void mouseExited(MouseEvent e)
	{}
	public void mousePressed(MouseEvent e)
	{}
	public void mouseReleased(MouseEvent e)
	{}
	public void mouseClicked(MouseEvent e)
	{
		Object o=e.getSource();
		outer:for(i=0;i<9;i++)
		{
			if(o==p[i])
			{
				inner:for(int u=0;u<k;u++)
				{
					if(i==j[u])  //so that no drawing on another
					{	
						continue outer;
					}
				}
				if(count==false)  //count is used so that once oval drawn and another cross
				{
					a[r]=i;
					r++;
					g[i].drawOval(30,25,60,60);
					count=true;
					po=r;
					//from here to check who wins
					if(r>2)
					{
					label:for(int h=0;h<8;h++)
						{
							label2:for(int y=0;y<3;y++)
							{
								for(int w=0;w<r;w++) 
								{
									if(v[h][y]==a[w])
									{	
										l=w;
										a[r]=a[l];
										while(l!=r)
										{
											a[l]=a[l+1];
											l++;
										}
										fc++;
										break;
									}
									else
									{
										if(w==r-1)
										{
											break label2;
										}
										
									}
								}
								if(fc==3)
								{
									lp.setText("Palyer 1 wins!!");
									lp1.setText("Do you want to start a new game or exit the game ? ");
									wins.add(lp);
									wins.add(lp1);
									wins.add(po2);
									wins.setVisible(true);
									break label;
								}
							}
							fc=0;
							r=po;
						}
					}
				}
				else
				{
					b[s]=i;
					s++;
					g[i].drawLine(25,30,110,90);
					g[i].drawLine(110,30,25,90);
					count=false;
					pos=s;
					if(s>2)
					{
					label1:for(int h=0;h<8;h++)
						{
							label3:for(int y=0;y<3;y++)
							{
								for(int w=0;w<s;w++) 
								{
									if(v[h][y]==b[w])
									{	
										l=w;
										b[s]=b[l];
										while(l!=s)
										{
											b[l]=b[l+1];
											l++;
										}
										fcg++;
										break;
									}
									else
									{
										if(w==s-1)
										{
											break label3;
										}
										
									}
								}
								if(fcg==3)
								{
									lp.setText("Palyer 2 wins!!");
									lp1.setText("Do you want to start a new game or exit the game ?");
									wins.add(lp);
									wins.add(lp1);
									wins.add(po2);
									wins.setVisible(true);
									break label1;
								}
							}
							fcg=0;
							s=pos;
						}
					}
				}
				j[k]=i;
				k++;
				break;
			}
		}
	}
	public static void main(String args[])
	{
		CrossZero cz=new CrossZero();
	}
}