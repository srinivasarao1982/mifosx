package org.mifosplatform.portfolio.client.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_clientexternalId")
public class ClientExternalId extends AbstractPersistable<Long> {
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = true)
  private Client client;

  public ClientExternalId(Client client) {
    super();
    this.client = client;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
