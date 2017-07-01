import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LineDraw extends JFrame implements ActionListener{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//�ŧiLineDraw���O�A�ñN���~��JFrame����C��@ActionListener(�i��","�j�}���P��@)�A�����ާ@JFrame���ƥ�C
	 
	int gwidth=450,gheigh=450,gx,gy; //�ŧi�������ܼơCgwidth���e�����e�Fgheigh���e�������Fgx���e����x��m�Fgy���e����y��m�C
	Dimension ScreenSize,FrameSize; //�ŧi�������O�ܼơCScreenSize���ù����j�p�FFrameSize����椧�j�p�C
	JLabel jl1,jl2; //�ŧi����JLabel���Ҫ���Cjl1�����ܨϥΪ̿�J�F�誺���ҡFjl2�����ܨϥΪ̿�J�ƭȩή榡���~�����ҡC
	JTextField jtf; //�ŧi����JTextField��r�������Cjtf���ѨϥΪ̿�J��r������ϡC
	JButton jb1,jb2,jb3; //�ŧi����JButton���s����Cjb1���T�{jtf��J�����s�A�ö}�lø�ϡFjb2�����ϥΪ̲M��JFrame�����W�i�H�������F��A�Y�^���}�l���e���Fjb3���ѨϥΪ������{�����\��C
	JPanel jp1,jp2; //�ŧi����JPanel�e������Cjp1���JFrame���_�A���t����jl1�Bjtf�Bjb1�Bjb2�Bjb3�Fjp2���JFrame���n�A���t����jl2�C
	Graphics g; //�ŧi����Graphics�e����k�Cg��ø�s�h��Ϊ��e���C
 
	public LineDraw(){ //����JFrame���غc�l
		jl1 = new JLabel("�п�J�h��Ϊ���ơG"); //�إ�jl1��JLabel���Ҫ���A�ó]�w��r���u�п�J�h��Ϊ���ơG�v�C
		jl2 = new JLabel(" "); //�إ�jl2��JLabel���Ҫ���A�ó]�w��r���šC
		jl2.setForeground(Color.red); //�]�wjl2���Ҫ��󤧤�r�C�⬰����C
		jtf = new JTextField(10); //�إ�jtf��JTextField��r�������A�ó]�w���׬�10�C
		jb1 = new JButton("�}�l�e�I"); //�إ�jb1��JButton���s����A�ó]�w��r���u�}�l�e�I�v�C
		jb2 = new JButton("�M��"); //�إ�jb2��JButton���s����A�ó]�w��r���u�M���v�C
		jb3 = new JButton("����"); //�إ�jb3��JButton���s����A�ó]�w��r���u�����v�C
		jp1 = new JPanel(); //�إ�jp1��JPanel�e������C
		jp2 = new JPanel(); //�إ�jp2��JPanel�e������C
		jp1.add(jl1); //�Njl1���ҥ[�Jjp1�e���C
		jp1.add(jtf); //�Njtf��r����[�Jjp1�e���C
		jp1.add(jb1); //�Njb1���s�[�Jjp1�e���C
		jp1.add(jb2); //�Njb2���s�[�Jjp1�e���C
		jp1.add(jb3); //�Njb3���s�[�Jjp1�e���C
		add(jp1,BorderLayout.NORTH); //�Njp1�[�JJFrame�A�ó]�w��BorderLayout�ƪ���NORTH(�_��)��m�C
		jp2.add(jl2); //�Njl2���ҥ[�Jjp2�e���C
		add(jp2,BorderLayout.SOUTH); //�Njp2�[�JJFrame�A�ó]�w��BorderLayout�ƪ���SOUTH(�n��)��m�C
		jb1.addActionListener(this); //�إ�jb1�bJFrame(LineDraw���O)��ActionListener��@�����C
		jb2.addActionListener(this); //�إ�jb2�bJFrame(LineDraw���O)��ActionListener��@�����C
		jb3.addActionListener(this); //�إ�jb3�bJFrame(LineDraw���O)��ActionListener��@�����C
 
		//---jtf��KeyListener��@����---
			jtf.addKeyListener( //�إ�jtf��KeyListener��@�����C
				new KeyListener(){ //�إ�KeyListener
					public void keyPressed(KeyEvent e) { //����L���U�Y����ɨϥΦ���k�C
						if(e.getKeyCode()==10) //�p�G���UEnter�C
						{
							JButton2_Action(); //����JButton2_Action��k�C
						}
					}
 
 
					public void keyTyped(KeyEvent e) {} //����L��J�Y����ɨϥΦ���k�A�o�̨S���Ψ�C
 
					public void keyReleased(KeyEvent e) {} //����L��}�Y����ɨϥΦ���k�A�o�̨S���Ψ�C
 
				}
			);
		//-------------END-------------
 
		setSize(600,600); //�]�wJFrame�������j�p�C
		setResizable(false); //����JFrame����ܵ����j�p���\��C
		ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); //���oJFrame�������ù�����m�I�C
		FrameSize = getSize(); //���oJFrame�����j�p�C
		setLocation((ScreenSize.width-FrameSize.width)/2,(ScreenSize.height-FrameSize.height)/2); //�NJFrame�����w�]��m�\�b�ù������C
		setTitle("JAVA�h���ø�s�{��"); //�]�wJFrame�������D�C
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�]�wJFrame���U�e�e�����s�i�H�����{���C
		setVisible(true); //���JFrame�C
 
		//-----------�إߵe��-----------
			gx=(FrameSize.width-gwidth)/2; //�]�w�e��x�_�l�I���JFrame���e�״�e���e�׫�A���G����m�C
			gy=(FrameSize.height-gheigh)/2; //�]�w�e��y�_�l�I���JFrame��氪�״�e�����׫�A���G����m�C
			g = getGraphics(); //�إ�g�e����k�C
			g.setClip(gx,gy, gwidth, gheigh); //�]�wg�e������m����檺�������C
			g.setColor(Color.BLACK); //�]�mg�e����ø���C�⬰�¦�C
		//-------------END-------------
	}
 
	public static void main(String[] args) { //�D�{��
		new LineDraw(); //�إ�LineDraw����C
	}
 
	public void actionPerformed(ActionEvent e) { //ActionListener��actionPerformed��k�C
		if(e.getSource()==jb1) //�p�G���Ujb1�C(�}�l�e)
		{
			JButton2_Action(); //����JButton2_Action��k�C
		}
		else if(e.getSource()==jb2) //�p�G���Ujb2�C(�M��)
		{
			update(g); //����g�e�����e�C
			jl2.setText(" "); //�M��jl2�C
			jtf.setText(""); //�M��jtf�C
		}
		else if(e.getSource()==jb3) //�p�G���Ujb3�C(����)
		{
			System.exit(0); //�������{���C
		}
	}
 
	public void JButton2_Action(){ //���Ujb2��άO�bjtf�W��Enter�ᤧ�h���ø�Ϥ�k�C
		update(g); ////����g�e�����e�C
		jl2.setText("��J���~�I"); //�]�wjl2���Ҥ�r���u��J���~�I�v�C
 
		int n=Integer.parseInt(jtf.getText()); //�ŧi���n����jtf��J�i�Ӫ��ƭȡC
		final int sizemax=80; //�ŧisizemax�`�ơA��jtf����J�̤j�ȡC
		if(n>=2&&n<=sizemax) //�Y�ƭȤj�󵥩�2�A�B�p�󵥩�sizemax�A�~�}�l�e�h��ΡC�]���u�ܤ֭n���I�~��s�A�ӷU�h�I�U�����ΡA�q���L�k�ǽT�B����ܥX�ӡC
		{
			jl2.setText(" "); //�M��jl2�C
 
			int[] X=new int[sizemax],Y=new int[sizemax]; //�ŧiX,Y��ư}�C�A�s���I����m�C
			int s=0,i,k; //�ŧi����ܼơCs��for�j�餧�p���ܼơFi��for�j�餧�p���ܼơFk��i��for�j��̪�for�j�餧�p���ܼơC
			double a; //�ŧia������ǯB�I���ܼơA�s��h��Τ������ܼƭȡC
			final double PIDouble=Math.PI*2; //�ŧiPIDouble������ǯB�I�Ʊ`�ơA�Ȭ��⭿����P�v�C
 
			//------�h��γ��I�����B��------
				for(a=0;a<PIDouble;a+=PIDouble/n)
				{
		            X[s] = (int) (Math.round((gwidth/2-1) * Math.cos(a)*Math.pow(10,0))/Math.pow(10,0)+(gwidth/2)); //�s�JX�I�C
		            Y[s] = (int) (Math.round((gwidth/2-1) * Math.sin(a)*Math.pow(10,0))/Math.pow(10,0)+(gheigh/2)); //�s�JY�I�C
		            s++; //�p��s=s+1�C
				}
			//-------------END-------------
 
			//--�h��γ��I�s�uø�ϰj��{��--
				for(i=0;i<n-1;i++) //�q�Ĥ@�I��˼ƲĤG�I
				{
					for(k=i+1;k<n;k++) //�C�@�I���|�@���P�ۤv���U�@�I�άO�U�U�I�A�@����̫�@�I���s�����u�C
					{
						g.drawLine(X[i]+gx, Y[i]+gy, X[k]+gx, Y[k]+gy); //�bg�e���W�e���u�C
						//System.out.println("(X[" + i + "]="+X[i]+","+"Y[" + i + "]="+Y[i]+") - " + "(X[" + k + "]="+X[k]+","+"Y[" + k + "]="+Y[k]+")"); //�N�e�����Ѯ����A�i�H�d���I�s�u���ԲӰʧ@�Ҧ��C
					}
				}
			//-------------END------------
		}
	}
}
