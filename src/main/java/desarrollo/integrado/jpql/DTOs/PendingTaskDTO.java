package desarrollo.integrado.jpql.DTOs;

public class PendingTaskDTO {
    private Long taskId;
    private String taskTitle;
    private String status;
    private String employeeName;

    public PendingTaskDTO(Long taskId, String taskTitle, String status, String employeeName) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.status = status;
        this.employeeName = employeeName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getStatus() {
        return status;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
