package us.jaaga.demovote.models;

import java.io.Serializable;

public class ProjectListData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String deliverableId;
	String deliverableName;
	Boolean deliverableStatus;
	String deliverablesDescription;
	boolean votingStatus;
	
	public String getDeliverablesDescription() {
		return deliverablesDescription;
	}
	public void setDeliverablesDescription(String deliverablesDescription) {
		this.deliverablesDescription = deliverablesDescription;
	}
	public boolean isVotingStatus() {
		return votingStatus;
	}
	public void setVotingStatus(boolean votingStatus) {
		this.votingStatus = votingStatus;
	}
	public int getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}
	int totalVotes;
	
	String noDeliverableMessage;
	
	public String getNoDeliverableMessage() {
		return noDeliverableMessage;
	}
	public void setNoDeliverableMessage(String noDeliverableMessage) {
		this.noDeliverableMessage = noDeliverableMessage;
	}
	
	public String getDeliverableId() {
		return deliverableId;
	}
	public void setDeliverableId(String deliverableId) {
		this.deliverableId = deliverableId;
	}
	public String getDeliverableName() {
		return deliverableName;
	}
	public void setDeliverableName(String deliverableName) {
		this.deliverableName = deliverableName;
	}
	public Boolean getDeliverableStatus() {
		return deliverableStatus;
	}
	public void setDeliverableStatus(Boolean deliverableStatus) {
		this.deliverableStatus = deliverableStatus;
	}
	
	/*@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel paramParcel, int paramInt) {
		
		paramParcel.writeString(deliverableId);
		paramParcel.writeString(deliverableName);
		paramParcel.writeByte((byte) (deliverableStatus ? 1 : 0)); //if myBoolean == true, byte == 1
		
		//boolean deliverableStatus = (Boolean) source.readValue();
	}
	
	public ProjectListData(Parcel source){
		
		this.deliverableId = source.readString();
		this.deliverableName = source.readString();
		this.deliverableStatus = source.readByte() !=0; //myBoolean == true if byte != 0
	}
	
	public static final Parcelable.Creator<ProjectListData> CREATOR = new Parcelable.Creator<ProjectListData>() {

		@Override
		public ProjectListData createFromParcel(Parcel paramParcel) {
			
			return new ProjectListData(paramParcel);
		}

		@Override
		public ProjectListData[] newArray(int paramInt) {
			
			return new ProjectListData[paramInt];
		}
	};
	
	public ProjectListData(){
		
	}*/
	
		
	
}
