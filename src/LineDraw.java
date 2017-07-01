import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LineDraw extends JFrame implements ActionListener{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//宣告LineDraw類別，並將其繼承JFrame物件。實作ActionListener(可用","隔開不同實作)，接收操作JFrame的事件。
	 
	int gwidth=450,gheigh=450,gx,gy; //宣告全域整數變數。gwidth為畫布之寬；gheigh為畫布之高；gx為畫布之x位置；gy為畫布之y位置。
	Dimension ScreenSize,FrameSize; //宣告全域類別變數。ScreenSize為螢幕之大小；FrameSize為表單之大小。
	JLabel jl1,jl2; //宣告全域JLabel標籤物件。jl1為提示使用者輸入東西的標籤；jl2為提示使用者輸入數值或格式有誤的標籤。
	JTextField jtf; //宣告全域JTextField文字方塊物件。jtf提供使用者輸入文字的方塊區。
	JButton jb1,jb2,jb3; //宣告全域JButton按鈕物件。jb1為確認jtf輸入之按鈕，並開始繪圖；jb2能讓使用者清除JFrame視窗上可以不有的東西，即回到剛開始的畫面；jb3提供使用者關閉程式的功能。
	JPanel jp1,jp2; //宣告全域JPanel容器物件。jp1位於JFrame之北，內含物件jl1、jtf、jb1、jb2、jb3；jp2位於JFrame之南，內含物件jl2。
	Graphics g; //宣告全域Graphics畫布方法。g為繪製多邊形的畫布。
 
	public LineDraw(){ //物件JFrame之建構子
		jl1 = new JLabel("請輸入多邊形的邊數："); //建立jl1為JLabel標籤物件，並設定文字為「請輸入多邊形的邊數：」。
		jl2 = new JLabel(" "); //建立jl2為JLabel標籤物件，並設定文字為空。
		jl2.setForeground(Color.red); //設定jl2標籤物件之文字顏色為紅色。
		jtf = new JTextField(10); //建立jtf為JTextField文字方塊物件，並設定長度為10。
		jb1 = new JButton("開始畫！"); //建立jb1為JButton按鈕物件，並設定文字為「開始畫！」。
		jb2 = new JButton("清除"); //建立jb2為JButton按鈕物件，並設定文字為「清除」。
		jb3 = new JButton("關閉"); //建立jb3為JButton按鈕物件，並設定文字為「關閉」。
		jp1 = new JPanel(); //建立jp1為JPanel容器物件。
		jp2 = new JPanel(); //建立jp2為JPanel容器物件。
		jp1.add(jl1); //將jl1標籤加入jp1容器。
		jp1.add(jtf); //將jtf文字方塊加入jp1容器。
		jp1.add(jb1); //將jb1按鈕加入jp1容器。
		jp1.add(jb2); //將jb2按鈕加入jp1容器。
		jp1.add(jb3); //將jb3按鈕加入jp1容器。
		add(jp1,BorderLayout.NORTH); //將jp1加入JFrame，並設定為BorderLayout排版之NORTH(北方)位置。
		jp2.add(jl2); //將jl2標籤加入jp2容器。
		add(jp2,BorderLayout.SOUTH); //將jp2加入JFrame，並設定為BorderLayout排版之SOUTH(南方)位置。
		jb1.addActionListener(this); //建立jb1在JFrame(LineDraw類別)的ActionListener實作介面。
		jb2.addActionListener(this); //建立jb2在JFrame(LineDraw類別)的ActionListener實作介面。
		jb3.addActionListener(this); //建立jb3在JFrame(LineDraw類別)的ActionListener實作介面。
 
		//---jtf的KeyListener實作介面---
			jtf.addKeyListener( //建立jtf的KeyListener實作介面。
				new KeyListener(){ //建立KeyListener
					public void keyPressed(KeyEvent e) { //當鍵盤按下某個鍵時使用此方法。
						if(e.getKeyCode()==10) //如果按下Enter。
						{
							JButton2_Action(); //執行JButton2_Action方法。
						}
					}
 
 
					public void keyTyped(KeyEvent e) {} //當鍵盤輸入某個鍵時使用此方法，這裡沒有用到。
 
					public void keyReleased(KeyEvent e) {} //當鍵盤放開某個鍵時使用此方法，這裡沒有用到。
 
				}
			);
		//-------------END-------------
 
		setSize(600,600); //設定JFrame的視窗大小。
		setResizable(false); //關閉JFrame能更變視窗大小的功能。
		ScreenSize = Toolkit.getDefaultToolkit().getScreenSize(); //取得JFrame視窗位於螢幕的位置點。
		FrameSize = getSize(); //取得JFrame視窗大小。
		setLocation((ScreenSize.width-FrameSize.width)/2,(ScreenSize.height-FrameSize.height)/2); //將JFrame視窗預設位置擺在螢幕中央。
		setTitle("JAVA多邊形繪製程式"); //設定JFrame視窗標題。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //設定JFrame按下叉叉關閉鈕可以關閉程式。
		setVisible(true); //顯示JFrame。
 
		//-----------建立畫布-----------
			gx=(FrameSize.width-gwidth)/2; //設定畫布x起始點位於JFrame表單寬度減掉畫布寬度後再除二的位置。
			gy=(FrameSize.height-gheigh)/2; //設定畫布y起始點位於JFrame表單高度減掉畫布高度後再除二的位置。
			g = getGraphics(); //建立g畫布方法。
			g.setClip(gx,gy, gwidth, gheigh); //設定g畫布的位置為表單的正中間。
			g.setColor(Color.BLACK); //設置g畫布的繪圖顏色為黑色。
		//-------------END-------------
	}
 
	public static void main(String[] args) { //主程式
		new LineDraw(); //建立LineDraw物件。
	}
 
	public void actionPerformed(ActionEvent e) { //ActionListener的actionPerformed方法。
		if(e.getSource()==jb1) //如果按下jb1。(開始畫)
		{
			JButton2_Action(); //執行JButton2_Action方法。
		}
		else if(e.getSource()==jb2) //如果按下jb2。(清除)
		{
			update(g); //消除g畫布內容。
			jl2.setText(" "); //清空jl2。
			jtf.setText(""); //清空jtf。
		}
		else if(e.getSource()==jb3) //如果按下jb3。(關閉)
		{
			System.exit(0); //關閉本程式。
		}
	}
 
	public void JButton2_Action(){ //按下jb2後或是在jtf上按Enter後之多邊形繪圖方法。
		update(g); ////消除g畫布內容。
		jl2.setText("輸入錯誤！"); //設定jl2標籤文字為「輸入錯誤！」。
 
		int n=Integer.parseInt(jtf.getText()); //宣告整數n等於jtf輸入進來的數值。
		final int sizemax=80; //宣告sizemax常數，為jtf的輸入最大值。
		if(n>=2&&n<=sizemax) //若數值大於等於2，且小於等於sizemax，才開始畫多邊形。因為線至少要兩點才能連，而愈多點愈接近圓形，電腦無法準確運算顯示出來。
		{
			jl2.setText(" "); //清空jl2。
 
			int[] X=new int[sizemax],Y=new int[sizemax]; //宣告X,Y整數陣列，存放點的位置。
			int s=0,i,k; //宣告整數變數。s為for迴圈之計次變數；i為for迴圈之計次變數；k為i的for迴圈裡的for迴圈之計次變數。
			double a; //宣告a為倍精準浮點數變數，存放多邊形之公式變數值。
			final double PIDouble=Math.PI*2; //宣告PIDouble為倍精準浮點數常數，值為兩倍的圓周率。
 
			//------多邊形頂點公式運算------
				for(a=0;a<PIDouble;a+=PIDouble/n)
				{
		            X[s] = (int) (Math.round((gwidth/2-1) * Math.cos(a)*Math.pow(10,0))/Math.pow(10,0)+(gwidth/2)); //存入X點。
		            Y[s] = (int) (Math.round((gwidth/2-1) * Math.sin(a)*Math.pow(10,0))/Math.pow(10,0)+(gheigh/2)); //存入Y點。
		            s++; //計次s=s+1。
				}
			//-------------END-------------
 
			//--多邊形頂點連線繪圖迴圈程式--
				for(i=0;i<n-1;i++) //從第一點到倒數第二點
				{
					for(k=i+1;k<n;k++) //每一點都會一直與自己的下一點或是下下點，一直到最後一點都連成直線。
					{
						g.drawLine(X[i]+gx, Y[i]+gy, X[k]+gx, Y[k]+gy); //在g畫布上畫直線。
						//System.out.println("(X[" + i + "]="+X[i]+","+"Y[" + i + "]="+Y[i]+") - " + "(X[" + k + "]="+X[k]+","+"Y[" + k + "]="+Y[k]+")"); //將前面註解拿掉，可以查看點連線的詳細動作模式。
					}
				}
			//-------------END------------
		}
	}
}
