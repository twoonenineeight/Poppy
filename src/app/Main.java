package app;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import worker.ImageDownloader;
import worker.InfoCollector;
import worker.PDFBuilder;

public class Main extends JFrame {
	
	public Main() {
		init();
	}
	
	private void init() {
		setTitle("Project Poppy [TMH 2019]");
		setSize(500, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel lbDocURL = new JLabel("URL tài liệu");
		JLabel lbPlaceToSave = new JLabel("Lưu tại");
		JLabel lbStatus = new JLabel("Tải tài liệu thành công!");
		JTextField textDocURL = new JTextField(30);
		JTextField textPlaceToSave = new JTextField(30);
		JButton btnPlaceChooser = new JButton("Chọn");
		JProgressBar progressBar = new JProgressBar(0, 100);
		
		this.setLayout(new FlowLayout());
		this.add(lbDocURL);
		this.add(textDocURL);
		this.add(lbPlaceToSave);
		this.add(textPlaceToSave);
		this.add(btnPlaceChooser);
		this.add(progressBar);
		this.add(lbStatus);
		
		
	}

	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Main main = new Main();
				main.setVisible(true);
				
			}
		});

//		String docURL = "http://repository.vnu.edu.vn/flowpaper/simple_document.php?subfolder=14/23/60/&doc=142360455579609585742048625500263847997&bitsid=c2795b93-0d0c-42ab-8845-49ebcdd6083e&uid=";
//		String folderPath = "C:/Users/TMH/Desktop/Delphinium_Test/";
//		int pageNum = 20;
//		
//		InfoCollector.collectInfoFrom(docURL);
//		ImageDownloader.download(folderPath, InfoCollector.getValueFromKey("doc"), "png", InfoCollector.getValueFromKey("subfolder"), pageNum);
//		PDFBuilder.build(folderPath + "img/", InfoCollector.getValueFromKey("doc"), folderPath, InfoCollector.getValueFromKey("doc"), pageNum);
		
	}

}
