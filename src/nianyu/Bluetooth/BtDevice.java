package nianyu.Bluetooth;

public class BtDevice {
	public static final int BT_SPP = 0;
	public static final int BT_HID = 1;
	public static final int BT_MUSIC = 2;
	
	private int type;	//¿‡–Õ
	private String name;
	private String address;
	
	public BtDevice(String address,String name,int type){
		this.address = address;
		this.name = name;
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
	public String getAddress(){
		return address;
	}
	public String getName(){
		return name;
	}
	public void setType(int type){
		this.type = type;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setName(String name){
		this.name = name;
	}
	
	
	
	
}
