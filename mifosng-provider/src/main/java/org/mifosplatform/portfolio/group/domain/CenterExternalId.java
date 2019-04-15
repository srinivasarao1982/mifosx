package org.mifosplatform.portfolio.group.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_centertexternalId")
public class CenterExternalId extends AbstractPersistable<Long> {
  @ManyToOne
  @JoinColumn(name = "center_id", nullable = true)
  private Group group;

  public CenterExternalId(Group group) {
    super();
    this.group = group;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

}
