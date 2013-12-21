package seneca.slee201.ttcbusschedule.task;

import seneca.slee201.ttcbusschedule.task.TaskBase;

public interface OnTaskCompleteListener {
    // Notifies about task completeness
    void onTaskComplete(TaskBase<?,?> task);
}