package org.mifosplatform.portfolio.cgtgrt.data;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;

public class ClientAttendenceData {
	private final Long taskId;
	private final Long clientId;
	private final String clientName;
	private final EnumOptionData clientAttendence;

	public static ClientAttendenceData getclientAttendence(final Long taskId, final Long clientId,
			final String clientName, final EnumOptionData clientAttendence) {

		return new ClientAttendenceData(taskId, clientId, clientName, clientAttendence);
	}

	public ClientAttendenceData(Long taskId, Long clientId, String clientName, EnumOptionData clientAttendence) {
		super();
		this.taskId = taskId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientAttendence = clientAttendence;
	}

}
