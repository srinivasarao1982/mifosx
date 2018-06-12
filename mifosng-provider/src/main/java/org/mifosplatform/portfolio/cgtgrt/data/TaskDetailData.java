package org.mifosplatform.portfolio.cgtgrt.data;


public class TaskDetailData {
	private final Long taskId;
	private final String  detailsDescription;
	private final String descriptionValue;
	private final Long id;
	
	
	public static  TaskDetailData createtaskdetails( final Long taskId,final String  detailsDescription,
			final String descriptionValue,final Long id){
		return new TaskDetailData(taskId,detailsDescription,descriptionValue,id);
	}
	public TaskDetailData(Long taskId, final String  detailsDescription,final String descriptionValue,Long id) {
		super();
		this.taskId = taskId;
		this.detailsDescription = detailsDescription;
		this.descriptionValue=descriptionValue;
		this.id=id;
	}
	
	
	
	

}
