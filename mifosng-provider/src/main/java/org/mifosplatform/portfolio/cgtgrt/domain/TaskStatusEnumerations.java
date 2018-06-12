package org.mifosplatform.portfolio.cgtgrt.domain;

import java.util.ArrayList;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;

public class TaskStatusEnumerations {
	
	public static EnumOptionData taskStatus(final int taskStatus) {
        return TaskStatus((TaskStatus.fromInt(taskStatus)));
    }

    public static EnumOptionData TaskStatus(final TaskStatus taskStatus) {

        EnumOptionData optionData = new EnumOptionData(TaskStatus.INVALID.getValue().longValue(), TaskStatus.INVALID.getCode(),
                "Invalid");

        switch (taskStatus) { 
        case INVALID:
            optionData = new EnumOptionData(TaskStatus.INVALID.getValue().longValue(), TaskStatus.INVALID.getCode(), "Invalid");
            break;
            case ACTIVE:
                optionData = new EnumOptionData(TaskStatus.ACTIVE.getValue().longValue(), TaskStatus.ACTIVE.getCode(), "Active");
            break;
            case PASS:
                optionData = new EnumOptionData(TaskStatus.PASS.getValue().longValue(), TaskStatus.PASS.getCode(), "Pass");
            break;
            case FAILED:
                optionData = new EnumOptionData(TaskStatus.FAILED.getValue().longValue(), TaskStatus.FAILED.getCode(),
                        "Failed");
            break;
            case COMPLETED:
                optionData = new EnumOptionData(TaskStatus.COMPLETED.getValue().longValue(), TaskStatus.COMPLETED.getCode(), "Completed");
            break;
            
        }
        return optionData;
    }

    public static List<EnumOptionData> taskStatusType(final TaskStatus[] TaskStatuss) {
        final List<EnumOptionData> optionDatas = new ArrayList<>();
        for (final TaskStatus TaskStatus : TaskStatuss) {
            if (TaskStatus.getValue().equals(TaskStatus.INVALID.getValue())) {
                continue;
            }
            optionDatas.add(TaskStatus(TaskStatus));
        }
        return optionDatas;
    }


}
