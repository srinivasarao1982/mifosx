/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.group.data;

import java.util.Collection;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.organisation.office.data.OfficeData;
import org.mifosplatform.organisation.staff.data.StaffData;
import org.mifosplatform.portfolio.calendar.data.CalendarData;

/**
 * Immutable data object representing groups.
 */
public class CenterData {

    private final Long id;
    private final String name;
    private final String externalId;
    private final Long officeId;
    private final String officeName;
    private final Long staffId;
    private final String staffName;
    private final String hierarchy;
    private final String isnewCenter;
    private final String iscbCheckRequired;
    private final String iscbchecked;
    private final String isgrtCompleted;

    private final EnumOptionData status;
    @SuppressWarnings("unused")
    private final boolean active;
    private final LocalDate activationDate;

    private final GroupTimelineData timeline;
    // associations
    private final Collection<GroupGeneralData> groupMembers;

    // template
    private final Collection<GroupGeneralData> groupMembersOptions;
    private final CalendarData collectionMeetingCalendar;
    private final Collection<CodeValueData> closureReasons;
    private final Collection<OfficeData> officeOptions;
    private final Collection<StaffData> staffOptions;
    private final Collection<CodeValueData>time;

    public static CenterData template(final Long officeId, final LocalDate activationDate, final Collection<OfficeData> officeOptions,
            final Collection<StaffData> staffOptions, final Collection<GroupGeneralData> groupMembersOptions,final Collection<CodeValueData>time) {
        final CalendarData collectionMeetingCalendar = null;
        final Collection<CodeValueData> closureReasons = null;
        final GroupTimelineData timeline = null;
        final String isnewCenter=null;
        final String iscbCheckRequired=null;
        final String iscbchecked=null;
        final String isgrtCompleted=null;

        return new CenterData(null, null, null, null, activationDate, officeId, null, null, null, null, null, officeOptions, staffOptions,
                groupMembersOptions, collectionMeetingCalendar, closureReasons, timeline,isnewCenter,iscbCheckRequired,time,iscbchecked,isgrtCompleted);
    }

    public static CenterData withTemplate(final CenterData templateCenter, final CenterData center) {
        return new CenterData(center.id, center.name, center.externalId, center.status, center.activationDate, center.officeId,
                center.officeName, center.staffId, center.staffName, center.hierarchy, center.groupMembers, templateCenter.officeOptions,
                templateCenter.staffOptions, templateCenter.groupMembersOptions, templateCenter.collectionMeetingCalendar,
                templateCenter.closureReasons, center.timeline,center.isnewCenter,center.iscbCheckRequired,templateCenter.time,center.iscbchecked,center.isgrtCompleted);
    }

    public static CenterData instance(final Long id, final String name, final String externalId, final EnumOptionData status,
            final LocalDate activationDate, final Long officeId, final String officeName, final Long staffId, final String staffName,
            final String hierarchy, final GroupTimelineData timeline, final CalendarData collectionMeetingCalendar,final String isnewCenter,final String isCbcheckRequired,final String iscbchecked,
            final String isgrtCompleted) {

        final Collection<GroupGeneralData> groupMembers = null;
        final Collection<OfficeData> officeOptions = null;
        final Collection<StaffData> staffOptions = null;
        final Collection<GroupGeneralData> groupMembersOptions = null;
        final Collection<CodeValueData> closureReasons = null;
        final Collection<CodeValueData>time =null;
        return new CenterData(id, name, externalId, status, activationDate, officeId, officeName, staffId, staffName, hierarchy,
                groupMembers, officeOptions, staffOptions, groupMembersOptions, collectionMeetingCalendar, closureReasons, timeline,isnewCenter,isCbcheckRequired,time,iscbchecked,isgrtCompleted);
    }

    public static CenterData withAssociations(final CenterData centerData, final Collection<GroupGeneralData> groupMembers,
            final CalendarData collectionMeetingCalendar) {
        return new CenterData(centerData.id, centerData.name, centerData.externalId, centerData.status, centerData.activationDate,
                centerData.officeId, centerData.officeName, centerData.staffId, centerData.staffName, centerData.hierarchy, groupMembers,
                centerData.officeOptions, centerData.staffOptions, centerData.groupMembersOptions, collectionMeetingCalendar,
                centerData.closureReasons, centerData.timeline,centerData.isnewCenter,centerData.iscbCheckRequired,centerData.time,centerData.iscbchecked,centerData.isgrtCompleted);
    }

    public static CenterData withClosureReasons(final Collection<CodeValueData> closureReasons) {
        final Long id = null;
        final String name = null;
        final String externalId = null;
        final EnumOptionData status = null;
        final LocalDate activationDate = null;
        final Long officeId = null;
        final String officeName = null;
        final Long staffId = null;
        final String staffName = null;
        final String hierarchy = null;
        final Collection<GroupGeneralData> groupMembers = null;
        final Collection<OfficeData> officeOptions = null;
        final Collection<StaffData> staffOptions = null;
        final Collection<GroupGeneralData> groupMembersOptions = null;
        final CalendarData collectionMeetingCalendar = null;
        final Collection<CodeValueData>time =null;
        final GroupTimelineData timeline = null;
        final String isnewCenter=null;
        final String iscbCheckRequired=null;
        final String iscbchecked=null;
        final String isgrtCompleted=null;
        return new CenterData(id, name, externalId, status, activationDate, officeId, officeName, staffId, staffName, hierarchy,
                groupMembers, officeOptions, staffOptions, groupMembersOptions, collectionMeetingCalendar, closureReasons, timeline,isnewCenter,iscbCheckRequired,time,iscbchecked,isgrtCompleted);
    }
    
    private CenterData(final Long id, final String name, final String externalId, final EnumOptionData status,
            final LocalDate activationDate, final Long officeId, final String officeName, final Long staffId, final String staffName,
            final String hierarchy, final Collection<GroupGeneralData> groupMembers, final Collection<OfficeData> officeOptions,
            final Collection<StaffData> staffOptions, final Collection<GroupGeneralData> groupMembersOptions,
            final CalendarData collectionMeetingCalendar, final Collection<CodeValueData> closureReasons, final GroupTimelineData timeline,
            final String isnewCenter,final String iscbCheckRequired,final Collection<CodeValueData>time,final String iscbchecked,final String isgrtCompleted ) {
        this.id = id;
        this.name = name;
        this.externalId = externalId;
        this.status = status;
        if (status != null) {
            this.active = status.getId().equals(300L);
        } else {
            this.active = false;
        }
        this.activationDate = activationDate;
        this.officeId = officeId;
        this.officeName = officeName;
        this.staffId = staffId;
        this.staffName = staffName;
        this.hierarchy = hierarchy;
        this.iscbCheckRequired=iscbCheckRequired;
        this.isnewCenter=isnewCenter;

        this.groupMembers = groupMembers;

        //
        this.officeOptions = officeOptions;
        this.staffOptions = staffOptions;
        this.groupMembersOptions = groupMembersOptions;
        this.collectionMeetingCalendar = collectionMeetingCalendar;
        this.closureReasons = closureReasons;
        this.timeline = timeline;
        this.time=time;
        this.iscbchecked=iscbchecked;
        this.isgrtCompleted=isgrtCompleted;
    }

    public String getIscbchecked() {
		return iscbchecked;
	}

	public Long officeId() {
        return this.officeId;
    }

    public Long staffId() {
        return this.staffId;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getHierarchy() {
        return this.hierarchy;
    }

    public CalendarData getCollectionMeetingCalendar() {
        return collectionMeetingCalendar;
    }

    public String getStaffName() {
        return this.staffName;
    }
    public Collection<GroupGeneralData> getGroupMembers() {
        return groupMembers;
}
}