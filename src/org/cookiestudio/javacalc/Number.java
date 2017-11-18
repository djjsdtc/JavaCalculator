package org.cookiestudio.javacalc;

import java.text.DecimalFormat;
//Number类：实现数字的显示

public class Number {
	private double numValue=0;   //真实值
	private String dispNum="0";   //显示值
	private boolean ZeroValue=true;     //是否为0值
	private boolean HasDot=false;     //是否已经有小数点
	private boolean LowerThan0=false;    //是否为负数
	private int length=0;
	
	//UpdateData：更新数据
	private String UpdateData(){
		numValue=Double.parseDouble(dispNum);
		length=dispNum.length();
		return dispNum;
	}
	
	//push_back：加数字
	public String push_back(int digit){
		if(length==32) return UpdateData();
		if(ZeroValue && digit==0);
		else{
			if(ZeroValue) dispNum=Integer.toString(digit);
			else dispNum+=Integer.toString(digit);
			ZeroValue=false;
		}
		return UpdateData();
	}
	
	//push_dot：加小数点
	public String push_dot(){
		if(length==32) return UpdateData();
		if(!HasDot){
			dispNum+=".";HasDot=true;ZeroValue=false;
		}
		return UpdateData();
	}
	
	//pop_back：删除数字或小数点
	public String pop_back(){
		if(Math.abs(numValue)<10 && !HasDot) return clear();
		if(dispNum.charAt(dispNum.length()-1)=='.') HasDot=false;
		
		int length=dispNum.length()-1;
		char[] tempCh=new char[length];
		for(int i=0;i<length;i++){
			tempCh[i]=dispNum.charAt(i);
		}
		dispNum=String.copyValueOf(tempCh);
		return UpdateData();
	}
	
	//clear：清除数据
	public String clear(){
		dispNum="0";numValue=0;ZeroValue=true;HasDot=false;length=0;
		return dispNum;
	}
	
	//getNumber：取得数值，setNumber：设置数值,getDisp：获取字符串型数值
	public double getNumber(){return numValue;}
	public String setNumber(double num){
		numValue=num;
		//dispNum=Double.toString(numValue);
		//if(numValue==(int)numValue){
			/*
			int length=dispNum.length();
			char[] tempStr=new char[length-1];
			for(int i=0;i<length-2;i++){
				tempStr[i]=dispNum.charAt(i);
			}
			tempStr[length-2]='\0';
			dispNum=String.copyValueOf(tempStr);
			*/
			DecimalFormat df=new DecimalFormat("#.##############################");
			dispNum=df.format(numValue);
		//}
		length=dispNum.length();
		return dispNum;
	}
	
	//symbolChange：切换正负号
	public String symbolChange(){
		if(ZeroValue);
		else{
			LowerThan0=!LowerThan0;
			if(LowerThan0) dispNum="-"+dispNum;
			else{
				int length=dispNum.length();
				char[] tempStr=new char[length];
				for(int i=1;i<length;i++){
					tempStr[i-1]=dispNum.charAt(i);
				}
				dispNum=String.copyValueOf(tempStr);
			}
		}
		return UpdateData();
	}
}
