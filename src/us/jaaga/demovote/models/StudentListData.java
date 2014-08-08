package us.jaaga.demovote.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentListData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String name;
	String pictureUrl;
	
	ArrayList<ProjectListData> delivList = new ArrayList<ProjectListData>();
	
	public ArrayList<ProjectListData> getDelivList() {
		
		return delivList;
	}
	
	public void setDelivList(ArrayList<ProjectListData> delivList) {
		
		this.delivList = delivList;
	}
	
	
	//Student List
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/*@Override
	public int describeContents() {
		return 0;
	}*/

	/*@Override
	public void writeToParcel(Parcel paramParcel, int paramInt) {
		
		paramParcel.writeString(id);
		paramParcel.writeString(name);
		paramParcel.writeString(pictureUrl);
		paramParcel.writeList(delivList);
		
	}
	
	public StudentListData(Parcel in){
		
		this.id = in.readString();
		this.name = in.readString();
		this.pictureUrl = in.readString();
		in.readList(delivList, List.class.getClassLoader());
	}*/
		
	
	
}
