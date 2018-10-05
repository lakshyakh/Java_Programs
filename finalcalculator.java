import java.awt.*;
import java.awt.event.*;
class Calci2 extends WindowCloser implements ActionListener
{		private Frame f;
		private TextField t;
		private Button b[],bu;
		private Panel p1,p2,p3,p4;
		private String s,s1,c;
		private float i,j,z;
		public int count,count1;
	Calci2()
	{
		
		t=new TextField("0");
		t.selectAll();
		t.setEditable(false); // so that we cant type anything on it
			
		b=new Button[16];
		
		for(int i=0;i<10;i++)
		{
			b[i]=new Button(String.valueOf(i));
		}
		b[10]=new Button("c");
		b[11]=new Button("+");
		b[12]=new Button("-");
		b[13]=new Button("*");
		b[14]=new Button("/");
		b[15]=new Button("=");
		
		for(int i=0;i<10;i++)
		{
			b[i].setForeground(Color.BLUE);	
		}
		for(int i=10;i<16;i++)
		{
			b[i].setForeground(Color.RED);	
		}
		for(int i=0;i<16;i++)
		{
			b[i].addActionListener(this);	
		}
		p1=new Panel();
		p1.setLayout(new GridLayout(4,4,10,20));
		
		p1.add(b[1]);
		p1.add(b[2]);
		p1.add(b[3]);
		p1.add(b[11]);
		p1.add(b[4]);
		p1.add(b[5]);
		p1.add(b[6]);
		p1.add(b[12]);
		p1.add(b[7]);
		p1.add(b[8]);
		p1.add(b[9]);		
		p1.add(b[13]);
		p1.add(b[0]);
		p1.add(b[10]);
		p1.add(b[15]);
		p1.add(b[14]);

		p2=new Panel();
		p3=new Panel();
		p4=new Panel();

		f=new Frame();
		f.setBackground(Color.GRAY);
		f.setSize(250,300);
		f.setLocation(300,300);
		f.setLayout(new BorderLayout(10,10));
		f.add(t,BorderLayout.NORTH);
		f.add(p1,BorderLayout.CENTER);
		f.add(p2,BorderLayout.WEST);  //for blank space
		f.add(p3,BorderLayout.EAST);  //for blank space
		f.add(p4,BorderLayout.SOUTH);  //for blank space
		WindowCloser wc=new WindowCloser();
		f.addWindowListener(wc);

		f.setResizable(false);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{	
		//s=t.getText();   if place it here then on double pressing the = button some further operations take place and also it is not needed too
		Button b1=(Button)e.getSource();
		if(b1==b[0] || b1==b[1] || b1==b[2] || b1==b[3] || b1==b[4] || b1==b[5] || b1==b[6] || b1==b[7] || b1==b[8] || b1==b[9])
		{	
			if(bu==b[11] ||bu==b[12]||bu==b[13]||bu==b[14]) // as number entered must not append with the operator instead operator must be removed and then num must be shown 
			{	
					c=bu.getLabel();  //if the code marked as label A is used then suppose after + a person press = instead of giving any no...then there was some addition done there(of the single no entered)....but when we intialize c here it is confirmed that after + some no is entered and so label B would be executed correctly  .... also see label c
					s=b1.getLabel();
					t.setText(s);
					bu=null;   //E//suppose an operation was performed so bu has some value now..so now when no would be entered every tym this if part will execute thats why bu is changed i.e now when no would be entered for the first tym after an operation ,bu is changed and so on further entering of any no other else if part will execute and not this until bu contains some value again
			 }
			else   //multidigit no must append one after other
			{
				if(count!=0)
				{
			 		s+=b1.getLabel();
					 t.setText(s);
				}	
				else		//when use calci first tym then 0 will be removed
				{
					s=b1.getLabel();
					t.setText(s);
				}
			}
			count++;  
		}
		else if(b1==b[11] ||b1==b[12]||b1==b[13]||b1==b[14])   //addition,sub,mul,div
		{
			if(count1==0)
			{
				s1=t.getText();
				count1++;
			}	
			else if(count1!=0 && bu==null)	//count1 used so that 5*3+8 such multioperations are also possible as the result is stored in s1(shown in comment S) and not the t.setText when sec operator is used . now after = is pressed count1 again becomes 0 and function goes on.......bu==null is used so that suppose anyone press 12+2++ then on the third + no operation must occur (if not put bu==null ) (bu==null actually means here that after + any no must be entered then only operation will occur (see label E))
			{
					operation();
			}
			bu=b1;
			//c= '+'; //label : A
		}
		else if(b1==b[15])   //equalsto
		{	
			operation();
			z=0;
		//	c="";  //label c.... so that the operation do not occur like => 12+2=14-=16 .... instead it would now be like => 12+2=14-=0  (this was happening bcoz after first = also c was + so on pressing = next tym this c=+ was executing )
					//remove this c="" if we want that on clicking = again n again operation would take place accordingly 
			//bu=b[15];
			count1=0;
			count=0;  //so that when after one operation new no is entered for new operation it will remove the previous result and start afresh	
		}
		else if(b1==b[10]) //clear
		{	
			t.setText("0");
			bu=null;
			count=0;
			count1=0;
		} 
	}
	private void operation()
	{
		i=Float.parseFloat(s1);
		j=Float.parseFloat(s);
		if(c=="+")  //label :B
		{
			z=i+j;
		}
		else if(c=="-")
		{
			z=i-j;
		}
		else if(c=="*")
		{
			z=i*j;
		}
		else if(c=="/")
		{	if(i%j==0)
			{
				z=i/j;
			}
			else
			{
				z=(float)i/j;
			}		
		}
		s1=z+""; //S
		if((s1.indexOf(".")==(s1.length()-2)) && (s1.charAt(s1.length()-1)== '0'))  //so that after int no .0 doesnt come but float are shown as float only
		{	
			String s2;
			s2=s1.substring(0,s1.length()-1);
			t.setText(s2);
		}
		else
		{
			t.setText(String.valueOf(z));
		}
	}
	public static void main(String[] args)
	{
		Calci2 ca=new Calci2();
	}
}