package BusRes;

public class Bus {
	private int busno;
	private boolean ac;
	private int capacity;
	
	Bus(int busno,boolean ac,int cap){
		this.busno=busno;
		this.ac=ac;
		this.capacity=cap;
	}
	public int getBusNo()
	{
		return busno;
	}
	public boolean isAc() {
		return ac;
	}
	public int getCapacity()
	{
		return capacity;
	}
	public void setAC(boolean val)
	{
		ac=val;
	}
	public void setCapacity(int cap)
	{
		capacity=cap;
	}
	
}
