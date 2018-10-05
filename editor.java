import java.awt.*;
import java.awt.event.*;
import java.io.*;
class Editor implements ActionListener
{
	
		private Frame f;
		private TextArea t;
		private Button bopen,bsave,bcut,bcopy,bpaste;
		private String s;
		private Label l1,l2;
	
	Editor() 
	{
		f=new Frame("Editor");
		f.setSize(600,300);
		f.setLocation(300,300);
		
		Font fnt=new Font("Arial",Font.PLAIN,21);
		t=new TextArea("Hello");
		t.setFont(fnt);

		bopen=new Button("Open");
		bsave=new Button("Save");
		bcut=new Button("Cut");
		bcopy=new Button("Copy");
		bpaste=new Button("Paste");
		bpaste.setEnabled(false);
		bopen. addActionListener(this);
		bsave. addActionListener(this);	
		bcut. addActionListener(this);
		bcopy. addActionListener(this);
		bpaste. addActionListener(this);	
		
		Watch w=new Watch();
		l1=w.getDateLabel();
		l2=w.getTimeLabel();
		

		Panel p=new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		p.add(bcut);
		p.add(bcopy);
		p.add(bpaste);
		p.add(bopen);
		p.add(bsave);
		p.add(l1);
		p.add(l2);
		
		//f.setLayout(new BorderLayout(20,120));
		f.add(t);
		f.add(p,BorderLayout.SOUTH);
		f.setVisible(true);
		w.interrupt();//otherwise for the first time when the window is visible tym would be visible breaking,,,,and when we min or max the window then it would be correctly visible...this was happening bcoz watch was in sleeping mode when the frame is just visible.....to resolve this interrupt it as soon as visible
		
	}
	public void actionPerformed(ActionEvent e) 
	{
		Object o;
		o=e.getSource();
		try
		{
			if(o==bopen)
			{
				bpaste.setEnabled(false);
				FileInputStream f=new FileInputStream("d:/java/editor.java");
				int c;
				String s="";
				while((c=f.read())!=-1)
				{
					s=s+String.valueOf((char)c);
				}
				t.setText(s);
			}
			else if(o==bsave)
			{	//all lines would be read but space would be shown as special characters and also all lines will appear in single line
				/*FileOutputStream fos=new FileOutputStream("d:/demo1.txt");
				String s=t.getText();
				DataOutputStream dos=new DataOutputStream(fos);
				dos.writeChars(s);*/
				//textarea is copied as it is
				FileWriter fw=new FileWriter("d:/demo.txt");
				String s=t.getText();
				fw.write(s);
				fw.close();  //very important othervise 0kb file is created
			}
			else if(o==bcut)
			{	
				bpaste.setEnabled(true);
				StringBuilder sb=new StringBuilder(t.getText());
				s=t.getSelectedText();
				int a=t.getSelectionStart();
				int b=t.getSelectionEnd();
				sb.replace(a,b," ");
				t.setText(sb.toString());
			}
			else if(o==bcopy)
			{
				bpaste.setEnabled(true);
				s=t.getSelectedText();
			}
			else if(o==bpaste)
			{
				StringBuilder sb=new StringBuilder(t.getText());
				int a=t.getSelectionStart();
				sb.insert(a,s);
				t.setText(sb.toString());
			}
		}
		catch(IOException ex)
		{
		}
	}
	public static void main(String args[]) 
		{
			Editor e=new Editor();
		}	
}