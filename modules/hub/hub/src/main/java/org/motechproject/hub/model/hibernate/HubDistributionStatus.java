package org.motechproject.hub.model.hibernate;

// Generated Apr 21, 2014 1:51:45 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * HubDistributionStatus generated by hbm2java
 */
@Entity
@Table(name = "hub_distribution_status", schema = "hub")
public class HubDistributionStatus implements java.io.Serializable {

	private static final long serialVersionUID = -6224225769462872750L;
	
	private long distributionStatusId;
	private String distributionStatusCode;
	private Set<HubSubscriberTransaction> hubSubscriberTransactions = new HashSet<HubSubscriberTransaction>(
			0);

	public HubDistributionStatus() {
	}

	public HubDistributionStatus(long distributionStatusId,
			String distributionStatusCode) {
		this.distributionStatusId = distributionStatusId;
		this.distributionStatusCode = distributionStatusCode;
	}

	public HubDistributionStatus(long distributionStatusId,
			String distributionStatusCode,
			Set<HubSubscriberTransaction> hubSubscriberTransactions) {
		this.distributionStatusId = distributionStatusId;
		this.distributionStatusCode = distributionStatusCode;
		this.hubSubscriberTransactions = hubSubscriberTransactions;
	}

	@Id
	@Column(name = "distribution_status_id", unique = true, nullable = false)
	public long getDistributionStatusId() {
		return this.distributionStatusId;
	}

	public void setDistributionStatusId(long distributionStatusId) {
		this.distributionStatusId = distributionStatusId;
	}

	@Column(name = "distribution_status_code", nullable = false, length = 15)
	public String getDistributionStatusCode() {
		return this.distributionStatusCode;
	}

	public void setDistributionStatusCode(String distributionStatusCode) {
		this.distributionStatusCode = distributionStatusCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hubDistributionStatus")
	public Set<HubSubscriberTransaction> getHubSubscriberTransactions() {
		return this.hubSubscriberTransactions;
	}

	public void setHubSubscriberTransactions(
			Set<HubSubscriberTransaction> hubSubscriberTransactions) {
		this.hubSubscriberTransactions = hubSubscriberTransactions;
	}

}