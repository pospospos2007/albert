package com.zdcf.test;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

import org.junit.Test;
public class FaceRecognition {
	private int picSize = 19 * 19;// 图像大小
	private int hiddenSize = 12;// 隐藏层节点个数
	private byte[] imageinfor = new byte[374];// 存放图像信息
	private double[] input = new double[picSize + 1];// 归一化后的图像信息
	private double[][] inputWeight = new double[hiddenSize][picSize + 1];// 输入层参数
	private double[] alpha1 = new double[hiddenSize];// 隐藏层调整的梯度
	private double[] hiddenWeight = new double[hiddenSize + 1];// 隐藏层参数
	private double[] hiddenOutput = new double[hiddenSize + 1];// 隐藏层输出
	private double alpha2;// 输出层调整的梯度
	private double output;// 输出层
	private double ci = 0.3;// 学习率
	private double opt;// 期望输出
	Random random = new Random();
	private double [] pro;

	public FaceRecognition() {
	}

	// 初始化
	public void init() {
	    for (int i = 0; i < hiddenSize; i++) {
	        for (int j = 0; j < picSize + 1; j++)
	            inputWeight[i][j] = random.nextDouble() * 2 - 1;
	        // inputWeight[i][j] =0;
	    }
	    for (int i = 0; i < hiddenSize + 1; i++) {
	        hiddenWeight[i] = random.nextDouble() * 2 - 1;
	        // hiddenWeight[i]=0;
	    }
	}

	// sigmoid
	private double Sigmoid(double x) {
	    return 1.0d / (1.0d + Math.exp(-x));
	}

	// 图像文件读入
	public void PGMReader(String filename) {
	    File file = new File(filename);
	    try {
	        RandomAccessFile in = new RandomAccessFile(file, "r");
	        in.read(imageinfor);
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    for (int i = 0; i < picSize; i++) {
	        int temp = (int) imageinfor[i + 13];
	        input[i] = (double) (temp + 128) / 255;
	    }
	    input[picSize] = 1.0;
	}

	public void PGMReader(File file) {
	    try {
	        RandomAccessFile in = new RandomAccessFile(file, "r");
	        in.read(imageinfor);
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    for (int i = 0; i < picSize; i++) {
	        int temp = (int) imageinfor[i + 13];
	        input[i] = (double) (temp + 128) / 255;
	    }
	    input[picSize] = 1.0;
	}

	public void setOpt(double opt) {
	    this.opt = opt;
	}

	private void forward() {
	    for (int i = 0; i < hiddenSize; i++) {
	        double temp = 0;
	        for (int j = 0; j < picSize + 1; j++) {
	            temp += input[j] * inputWeight[i][j];
	        }
	        hiddenOutput[i] = Sigmoid(temp);
	    }
	    hiddenOutput[hiddenSize] = 1.0;

	    double temp = 0;
	    for (int i = 0; i < hiddenSize + 1; i++) {
	        temp += hiddenOutput[i] * hiddenWeight[i];
	    }
	    output = Sigmoid(temp);
	}

	public void BP() {
	    // 计算各层的梯度
	    alpha2 = (opt - output) * output * (1 - output);

	    for (int i = 0; i < hiddenSize; i++) {
	        alpha1[i] = hiddenOutput[i] * (1 - hiddenOutput[i]) * alpha2 * hiddenWeight[i];
	    }

	    // 反向传播
	    for (int i = 0; i < hiddenSize; i++) {    
	        hiddenWeight[i] += ((hiddenOutput[i] * alpha2 * ci) );
	        for (int j = 0; j < picSize + 1; j++) {
	            inputWeight[i][j] +=((input[j] * alpha1[i] * ci));
	        }
	    }
	    hiddenWeight[hiddenSize]+=(hiddenOutput[hiddenSize] * alpha2 * ci);
	}

	@Test
	public void train() {
	    String non_facePath = "C://non-face";
	    File non_facFile = new File(non_facePath);
	    File[] non_faceList = non_facFile.listFiles();
	    String facePath = "C://face";
	    File faceFile = new File(facePath);
	    File[] faceList = faceFile.listFiles();
	    init();
	    pro =new double [151];
	    
	    
	    for(int i =0;i<151;i++){
	        int right = 0;
	        int facenumber =0;
	        int nonfacenumber =0;
	        
	        for (int j = 0; j < 16; j++) {
	            int temp = random.nextInt();
	            if(temp%2 ==0)
	            { // 正例训练
	                this.setOpt(1.0);
	                this.PGMReader(faceList[facenumber]);
	                this.forward();
	                this.BP();
	                facenumber++;
	            }
	            else{ // 反例训练

	                this.setOpt(0.0);
	                this.PGMReader(non_faceList[nonfacenumber]);
	                this.forward();
	                this.BP();
	                nonfacenumber++;
	            }
	        }

	        for (int j = 5; j <12; j++) {
	            { // 正例测试
	                this.PGMReader(faceList[j]);
	                this.forward();
	                if (output > 0.5)
	                    right++;
	            }
	            { // 反例测试
	                this.PGMReader(non_faceList[j]);
	                this.forward();
	                if (output < 0.5)
	                    right++;
	            }
	        }
	        pro[i] = (double) right / 800;
	        if(i%10==0)
	        {
	            System.out.println("第"+i+"次迭代估算正确率为：" + pro[i]);
	        }
	        
	        if(pro[i]>=0.95){
	            System.out.println("第"+i+"次迭代估算正确率为：" + pro[i]);
	            break;
	        }
	            
	    }
	}
}
