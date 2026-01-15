package studentmanagement.model;

import java.sql.Time;

//This class contains getters and setters of all private variables of Batch

public class Batch {
	
	private int batchId;
	private String day;
	private Time startTime;
	private Time endTime;
	private String homework;
	private int level;
	private String lessonPlan;
	private boolean isDeleted;
	
	
	
	public Batch(int batchId, String day, Time startTime, Time endTime, String homework, int level, String lessonPlan,
			boolean isDeleted) {
		super();
		this.batchId = batchId;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.homework = homework;
		this.level = level;
		this.lessonPlan = lessonPlan;
		this.isDeleted = isDeleted;
	}
	
	public Batch(String day, Time startTime, Time endTime, String homework, int level, String lessonPlan,
			boolean isDeleted) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.homework = homework;
		this.level = level;
		this.lessonPlan = lessonPlan;
		this.isDeleted = isDeleted;
	}
	
	
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getHomework() {
		return homework;
	}
	public void setHomework(String homework) {
		this.homework = homework;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLessonPlan() {
		return lessonPlan;
	}
	public void setLessonPlan(String lessonPlan) {
		this.lessonPlan = lessonPlan;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
