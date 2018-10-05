import java.io.*;
import java.awt.*;
import java.awt.event.*;
class MyNotepad extends DialogCloser implements ActionListener,ItemListener,TextListener,WindowListener
{
	private Frame f;
	private TextArea ta;
	private MenuBar mb;
	private Menu file,format,edit,font;
	private MenuItem neu,open,save,saveas,exit,cut,copy,paste,find,replace,selectall;
	private CheckboxMenuItem bold,italic;
	private Dialog dclose,dfind,df,dreplace;
	private Label lclose1,lclose2,lblfind,lbldir,l,lblreplace,lblfind2,lbldir2;
	private Panel p,p1,p2,p3,pr,pr1;
	private Button byes,bno,bcancel,ffindnext,fcancel,bok,breplace,brcancel,ffindnext2;
	private FileDialog fd;
	private int count,i,a,i2,a2;
	private Boolean count1=false,bo=true,count2=false,bo2=true,count3=false;
	private char c[],ca[],c2[],ca2[];
	private String d,sc,sfind,sf,st,sr,sfind2,st2,sv;
	private TextField tfind,treplace,tfind2;
	private Checkbox cmatch,cup,cdown,cmatch2,cup2,cdown2;
	
	MyNotepad()
	{
		f=new Frame("New-MyNotepad");
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		f.addWindowListener(this);
	
		neu=new MenuItem("New");
		open=new MenuItem("Open");
		save=new MenuItem("Save");
		saveas=new MenuItem("Save As");
		exit=new MenuItem("Exit");
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		paste=new MenuItem("Paste");
		find=new MenuItem("Find");
		replace=new MenuItem("Replace");
		selectall=new MenuItem("Select All");
		
		find.setEnabled(false);
		
		MenuShortcut ms=new MenuShortcut(KeyEvent.VK_N);
		neu.setShortcut(ms);
		open.setShortcut(new MenuShortcut(KeyEvent.VK_O));
		save.setShortcut(new MenuShortcut(KeyEvent.VK_S));
		//saveas.setShortcut(new MenuShortcut(KeyEvent.VK_ALT | KeyEvent.VK_S));
		exit.setShortcut(new MenuShortcut(KeyEvent.VK_F4));
		cut.setShortcut(new MenuShortcut(KeyEvent.VK_X));
		copy.setShortcut(new MenuShortcut(KeyEvent.VK_C));
		paste.setShortcut(new MenuShortcut(KeyEvent.VK_V));
		selectall.setShortcut(new MenuShortcut(KeyEvent.VK_A));
		find.setShortcut(new MenuShortcut(KeyEvent.VK_F));
		replace.setShortcut(new MenuShortcut(KeyEvent.VK_H));
		
		
		neu.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		find.addActionListener(this);
		replace.addActionListener(this);
		selectall.addActionListener(this);
		
		save.setEnabled(false);
		paste.setEnabled(false);
		
		bold=new CheckboxMenuItem("Bold");
		italic=new CheckboxMenuItem("Italic");
	
		bold.addItemListener(this);
		italic.addItemListener(this);
		
		file=new Menu("File");
		format=new Menu("Format");
		edit=new Menu("Edit");
		font=new Menu("Font");
		
		mb=new MenuBar();
		
		file.add(neu);
		file.add(open);
		file.add(new MenuItem("-"));
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(exit);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.addSeparator();
		edit.add(find);
		edit.add(replace);
		edit.addSeparator();
		edit.add(selectall);
		
		font.add(bold);
		font.add(italic);
		
		format.add(font);
		
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		
		ta=new TextArea();
		ta.addTextListener(this);
		
		lclose1=new Label("The text in the file has changed.",Label.CENTER);
		lclose2=new Label("Do you want to save the changes?",Label.CENTER);
		
		byes=new Button("Yes");
		bno=new Button("No");
		bcancel=new Button("Cancel");
		
		byes.addActionListener(this);
		bno.addActionListener(this);
		bcancel.addActionListener(this);
		
		p=new Panel();
		p.add(byes);
		p.add(bno);
		p.add(bcancel);
		
		dclose=new Dialog(f,"MyNotepad",true);
		dclose.setSize(300,125);
		dclose.setLocationRelativeTo(f);
		dclose.setLayout(new GridLayout(3,1));
		dclose.setResizable(false);
		dclose.add(lclose1);
		dclose.add(lclose2);
		dclose.add(p);
		DialogCloser dc=new DialogCloser();
		dclose.addWindowListener(dc);
		
		p1=new Panel();
		p3=new Panel();
		pr=new Panel();
		pr1=new Panel();
		
		ffindnext=new Button("Find Next");
		ffindnext.setEnabled(false);
		fcancel=new Button("Cancel");
		ffindnext.addActionListener(this);
		fcancel.addActionListener(this);
		ffindnext2=new Button("Find Next");
		ffindnext2.setEnabled(false);
		ffindnext2.addActionListener(this);
		breplace=new Button("Replace");
		breplace.setEnabled(false);
		brcancel=new Button("Cancel");
		breplace.addActionListener(this);
		brcancel.addActionListener(this);
		
		lblfind=new Label("Find What :");
		lblfind2=new Label("Find What :");
		lblreplace=new Label("Replace With :");
		tfind=new TextField(20);
		tfind.addTextListener(this);
		tfind2=new TextField(20);
		tfind2.addTextListener(this);
		treplace=new TextField(20);
		treplace.addTextListener(this);
		lbldir=new Label("Direction");
		lbldir.setForeground(Color.BLUE);
		lbldir2=new Label("Direction");
		lbldir2.setForeground(Color.BLUE);
		cmatch=new Checkbox("Match Case",true);
		cmatch.addItemListener(this);
		CheckboxGroup cbg=new CheckboxGroup();
		cup=new Checkbox("Up",false,cbg);
		cdown=new Checkbox("Down",true,cbg);
		sf="down";
		cup.addItemListener(this);
		cdown.addItemListener(this);
		cmatch2=new Checkbox("Match Case",true);
		cmatch2.addItemListener(this);
		CheckboxGroup cbg2=new CheckboxGroup();
		cup2=new Checkbox("Up",false,cbg2);
		cdown2=new Checkbox("Down",true,cbg2);
		cup2.addItemListener(this);
		cdown2.addItemListener(this);
		sr="down";
		p1.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		Insets in=new Insets(0,10,5,0);
		gbc.insets=in;
		gbc.gridx=0;
		gbc.gridy=0;
		p1.add(lblfind,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.gridwidth=2;
		p1.add(tfind,gbc);
		
		gbc.gridx=3;
		gbc.gridy=0;
		gbc.gridwidth=1;
		p1.add(ffindnext,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		p1.add(lbldir,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		p1.add(cmatch,gbc);
		
		p3.add(cup);
		p3.add(cdown);
		
		gbc.gridx=2;
		gbc.gridy=2;
		p1.add(p3,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		p1.add(fcancel,gbc);
		
		pr.setLayout(new GridBagLayout());
		GridBagConstraints gbc1=new GridBagConstraints();
		Insets ins=new Insets(0,10,5,0);
		gbc1.insets=ins;
		gbc1.gridx=0;
		gbc1.gridy=0;
		pr.add(lblfind2,gbc1);
		gbc1.gridx=1;
		gbc1.gridy=0;
		gbc1.gridwidth=2;
		pr.add(tfind2,gbc1);
		gbc1.gridx=3;
		gbc1.gridy=0;
		pr.add(ffindnext2,gbc1);
		gbc1.gridx=0;
		gbc1.gridy=1;
		pr.add(lblreplace,gbc1);
		gbc1.gridx=1;
		gbc1.gridy=1;
		gbc1.gridwidth=2;
		pr.add(treplace,gbc1);
		gbc1.gridx=3;
		gbc1.gridy=1;
		pr.add(breplace,gbc1);
		gbc1.gridx=2;
		gbc1.gridy=2;
		pr.add(lbldir2,gbc1);
		gbc1.gridx=0;
		gbc1.gridy=3;
		pr.add(cmatch2,gbc1);
		pr1.add(cup2);
		pr1.add(cdown2);
		gbc1.gridx=2;
		gbc1.gridy=3;
		pr.add(pr1,gbc1);
		gbc1.gridx=3;
		gbc1.gridy=2;
		gbc1.fill=GridBagConstraints.HORIZONTAL;
		pr.add(brcancel,gbc1);
		
		
		dfind=new Dialog(f,"Find",false);
		dfind.setSize(360,150);
		dfind.setLocationRelativeTo(f);
		dfind.setResizable(false);
		dfind.addWindowListener(dc);
		dfind.setLayout(new GridLayout(1,1));
		dfind.add(p1);
		
		dreplace=new Dialog(f,"Replace",false);
		dreplace.setSize(400,200);
		dreplace.setLocationRelativeTo(f);
		dreplace.setResizable(false);
		dreplace.addWindowListener(dc);
		dreplace.setLayout(new GridLayout(1,1));
		dreplace.add(pr);
		
		p2=new Panel();
		bok=new Button("OK");
		p2.add(bok);
		bok.addActionListener(this);
		df=new Dialog(dfind,"MyNotepad",true);
		df.setSize(100,100);
		df.setLocationRelativeTo(dfind);
		df.setResizable(false);
		df.addWindowListener(dc);
		df.setLayout(new GridLayout(2,1));
		l=new Label("Cannot find !",Label.CENTER);
		df.add(l);
		df.add(p2);
				
		f.setMenuBar(mb);
		f.add(ta);
		st=" ";
		st2=" ";

		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		try
		{
			if(o==neu)
			{	
				String s=f.getTitle();
				if(s.charAt(0)=='*')
				{
					d="neu";
					dclose.setVisible(true);
				}
				else
				{
					ta.setText(" ");
					count=1;
					f.setTitle("New-MyNotepad");
				}
			}
			else if(o==open)
			{
				String sg=f.getTitle();
				d="open";
				if(sg.charAt(0)=='*')
				{	
					dclose.setVisible(true);
				}
				else
				{
					open();
				}
			}
			else if(o==save || o==saveas || o==byes)
			{
				String s=f.getTitle();
				if(s.equals("*New-MyNotepad") || o==saveas || o==byes)
				{
					fd=new FileDialog(f,"Save",FileDialog.SAVE);
					fd.setVisible(true);
					String s1,s2;
					if(fd.getDirectory()==null || fd.getDirectory().length()==0)
					{
						return;
					}
					s2=fd.getDirectory()+fd.getFile();
					FileWriter fw;
					if(fd.getFile().indexOf(".")==-1)
					{
						s2=s2+".txt";
						fw=new FileWriter(s2);
					}
					else
					{
						fw=new FileWriter(s2);
					}
					s1=ta.getText();
					fw.write(s1);
					fw.close(); 
					if(o==byes)
					{	
						close1(); //to remove dialog box dclose
						
					}
					else
					{
						f.setTitle(s2+"-MyNotepad");
					}
				}
				else
				{
					String s1,s2;
					s2=s.substring(1,s.indexOf("-"));
					FileWriter fw=new FileWriter(s2);
					s1=ta.getText();
					fw.write(s1);
					fw.close(); 
					f.setTitle(s2+"-MyNotepad");
				}
				save.setEnabled(false);
			}
			else if(o==bno)
			{
				close1();
			}
			else if(o==bcancel)
			{
				dclose.setVisible(false);
				dclose.dispose();
			}
			else if(o==exit)
			{
				close();
			}
			else if(o==cut)
			{
				paste.setEnabled(true);
				StringBuilder sb=new StringBuilder(ta.getText());
				sc=ta.getSelectedText();
				int a=ta.getSelectionStart();
				int b=ta.getSelectionEnd();
				sb.replace(a,b,"");
				ta.setText(sb.toString());
			}
			else if(o==copy)
			{
				paste.setEnabled(true);
				sc=ta.getSelectedText();
			}
			else if(o==paste)
			{
				StringBuilder sb=new StringBuilder(ta.getText());
				int a=ta.getSelectionStart();
				sb.insert(a,sc);
				ta.setText(sb.toString());
			}
			else if(o==selectall)
			{
				ta.selectAll();
			}
			else if(o==find)
			{
				dfind.setVisible(true);
				count1=false;
			}
			else if(o==ffindnext)
			{
				sfind=tfind.getText();
				st=ta.getText();
				c=st.toCharArray();
				int t=0;
				for(char z:c)
				{
					if(z=='\n')
					{
						t++;
					}
				}
 				ca=new char[st.length()-t];
				int p=0,j=0;
				while(p<st.length())
				{
					if(c[p]=='\n' )
					{
						p++;
					}
					else
					{
						ca[j]=c[p];
						j++;
						p++;
					}
				}
				st=String.copyValueOf(ca,0,ca.length);
				if(i!=-1 || count1==false)
				{
					if(bo==false)
					{
						String g,l;
						g=sfind.toUpperCase();
						l=st.toUpperCase();
						sfind=g;
						st=l;
					}
					if(sf.equals("down"))
					{
						if(count1==false)
						{
							i=st.indexOf(sfind,i);
							count1=true;
						}
						else
						{
							i=st.indexOf(sfind,i+1);
						}
					}
					else if(sf.equals("up"))
					{
						if(count1==false)
						{
							i=st.lastIndexOf(sfind,i);
							count1=true;
						}
						else
						{
							i=st.lastIndexOf(sfind,i-1);
						}
					}
					ta.requestFocus();
					a=sfind.length();
					ta.select(i,i+a);
					if(i==-1)   //if put this in else then the if part execute one extra tym
					{
						df.setVisible(true);
					}
				}
				else
				{
					df.setVisible(true);
				}
			}
			else if(o==fcancel)
			{
				dfind.setVisible(false);
				dfind.dispose();
			}
			else if(o==bok)
			{
				df.setVisible(false);
				df.dispose();
			}
			else if(o==replace)
			{
				dreplace.setVisible(true);
			}
			else if(o==ffindnext2)
			{
					select();
			}
			else if(o==breplace )
			{
				if(i2!=-1)
				{
					if(count3==false)
					{
						count3=true;
						select();
					}
					else
					{
						String sh=treplace.getText();
						ta.replaceRange(sh,i2,i2+a2);
						select();
					}
				}
				else
				{
					df.setVisible(true);
				}
			}
			else if(o==brcancel)
			{
				dreplace.setVisible(false);
				dreplace.dispose();
			}
		} 
		catch(IOException e1)
		{
		}
	}
	public void itemStateChanged(ItemEvent e)
	{
		Object o=e.getSource();
		if(o==bold || o==italic)
		{
			Boolean b1,b2;
			b1=bold.getState();
			b2=italic.getState();
			Font fnt;
			if(b1==true && b2==true)
			{
				fnt=new Font("Times New Roman",Font.BOLD | Font.ITALIC,15);
			}
			else if(b1==true && b2==false)
			{
				fnt=new Font("Times New Roman",Font.BOLD,15);
			}
			else if(b1==false && b2==true)
			{
				fnt=new Font("Times New Roman",Font.ITALIC,15);
			}
			else 
			{
				fnt=new Font("Times New Roman",Font.PLAIN,15);
			}
			ta.setFont(fnt);
		}
		else if(o==cmatch)
		{
			bo=cmatch.getState();
			count1=false;
		}
		else if(o==cup)
		{
			sf="up";
			i=ta.getText().length();
			count1=false;
		}
		else if(o==cdown)
		{
			sf="down";
			count1=false;
		}
		else if(o==cmatch2)
		{
			bo2=cmatch2.getState();
			count2=false;
			count3=false;
		}
		else if(o==cup2)
		{
			sr="up";
			i2=ta.getText().length();
			count2=false;
			count3=false;
		}
		else if(o==cdown2)
		{
			sr="down";
			count2=false;
			count3=false;
		}
	}
	public void textValueChanged(TextEvent e)
	{
		Object o=e.getSource();
		if(o==ta)
		{
			find.setEnabled(true);
			String s=f.getTitle();
			if(s.charAt(0)!='*' && count==0)
			{
				f.setTitle("*"+s);
				save.setEnabled(true);
			}
			count=0;
		}
		else if(o==tfind)
		{
			String hy;
			hy=tfind.getText();
			if(hy.equals(" "))
			{
				ffindnext.setEnabled(false);
			}
			else
			{
				ffindnext.setEnabled(true);
			}
		}
		else if(o==tfind2)
		{
			String hy;
			hy=tfind2.getText();
			if(hy.equals(" "))
			{
				ffindnext2.setEnabled(false);
				breplace.setEnabled(false);
			}
			else
			{
				ffindnext2.setEnabled(true);
				breplace.setEnabled(true);
			}
		}
	}
	public void close()
	{
		String s=f.getTitle();
		if(s.charAt(0)=='*')
		{	d="close";
			dclose.setVisible(true);
		}
		else
		{
			f.setVisible(false);
			f.dispose();
			System.exit(1);
		}
	}
	public void close1()
	{
		dclose.setVisible(false);
		dclose.dispose();
		if(d.equals("neu"))
		{
			ta.setText(" ");
			count=1;
			f.setTitle("New-MyNotepad");
		}
		else if(d.equals("close"))
		{
			f.setVisible(false);
			f.dispose();
			System.exit(1);
		}
		else if(d.equals("open"))
		{
			open();
		}
	}
	public void open()
	{
		try
		{
		fd=new FileDialog(f,"Open",FileDialog.LOAD);
		fd.setVisible(true);
		String s,s1;
		s1=fd.getDirectory()+fd.getFile();
		FileInputStream fi=new FileInputStream(s1);
		int c;
		s="";
		while((c=fi.read())!=-1)
		{
			s=s+String.valueOf((char)c);
		}
		ta.setText(s);
		f.setTitle(s1+"-MyNotepad");
		count=1; //if do not use this then on just opening the file it was showing * in front
		save.setEnabled(false);
		}
		catch(IOException e)
		{
		}
	}
	public void select()
	{
		sfind2=tfind2.getText();
		st2=ta.getText();
		c2=st2.toCharArray();
		int t=0;
		for(char z:c2)
		{
			if(z=='\n')
			{
				t++;
			}
		}
 		ca2=new char[st2.length()-t];
		int p=0,j=0;
		while(p<st2.length())
		{
			if(c2[p]=='\n' )
			{
				p++;
			}
			else
			{
				ca2[j]=c2[p];
				j++;
				p++;
			}
		}
		st2=String.copyValueOf(ca2,0,ca2.length);
		if(i2!=-1 || count2==false)
		{
			if(bo2==false)
			{
				String g,l;
				g=sfind2.toUpperCase();
				l=st2.toUpperCase();
				sfind2=g;
				st2=l;
			}
			if(sr.equals("down"))
			{
				if(count2==false)
				{
					i2=st2.indexOf(sfind2,i2);
					count2=true;
				}
				else
				{
					i2=st2.indexOf(sfind2,i2+1);
				}
			}
			else if(sr.equals("up"))
			{
				if(count2==false)
				{
					i2=st2.lastIndexOf(sfind2,i2);
					count2=true;
				}
				else
				{
					i2=st2.lastIndexOf(sfind2,i2-1);
				}
			}
			ta.requestFocus();
			a2=sfind2.length();
			ta.select(i2,i2+a2);
			if(i2==-1)   //if put this in else then the if part execute one extra tym
			{
				df.setVisible(true);
			}
		}
		else
		{
			df.setVisible(true);
		}
	}
	public void windowOpened(WindowEvent e)
	{
	}
	public void windowClosed(WindowEvent e)
	{
	}
	public void windowActivated(WindowEvent e)
	{
	}
	public void windowDeactivated(WindowEvent e)
	{
	}
	public void windowIconified(WindowEvent e)
	{
	}
	public void windowDeiconified(WindowEvent e)
	{
	}
	public void windowClosing(WindowEvent e)
	{
		close();
	}
	public static void main(String args[])
	{
		MyNotepad mn=new MyNotepad();
	}
}