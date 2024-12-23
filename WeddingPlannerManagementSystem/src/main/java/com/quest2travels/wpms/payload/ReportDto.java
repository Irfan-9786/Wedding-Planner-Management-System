package com.quest2travels.wpms.payload;

import java.util.List;

import com.quest2travels.wpms.entities.Vendor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReportDto {
	  private long totalClientsRegistered;
	    private long totalUpcomingEvents;
	    private long totalCompletedEvents;
	    private List<Vendor> topVendors;
	    private double totalRevenue;
		public ReportDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ReportDto(long totalClientsRegistered, long totalUpcomingEvents, long totalCompletedEvents,
				List<Vendor> topVendors, double totalRevenue) {
			super();
			this.totalClientsRegistered = totalClientsRegistered;
			this.totalUpcomingEvents = totalUpcomingEvents;
			this.totalCompletedEvents = totalCompletedEvents;
			this.topVendors = topVendors;
			this.totalRevenue = totalRevenue;
		}
		public long getTotalClientsRegistered() {
			return totalClientsRegistered;
		}
		public void setTotalClientsRegistered(long totalClientsRegistered) {
			this.totalClientsRegistered = totalClientsRegistered;
		}
		public long getTotalUpcomingEvents() {
			return totalUpcomingEvents;
		}
		public void setTotalUpcomingEvents(long totalUpcomingEvents) {
			this.totalUpcomingEvents = totalUpcomingEvents;
		}
		public long getTotalCompletedEvents() {
			return totalCompletedEvents;
		}
		public void setTotalCompletedEvents(long totalCompletedEvents) {
			this.totalCompletedEvents = totalCompletedEvents;
		}
		public List<Vendor> getTopVendors() {
			return topVendors;
		}
		public void setTopVendors(List<Vendor> topVendors) {
			this.topVendors = topVendors;
		}
		public double getTotalRevenue() {
			return totalRevenue;
		}
		public void setTotalRevenue(double totalRevenue) {
			this.totalRevenue = totalRevenue;
		}
		@Override
		public String toString() {
			return "ReportDto [totalClientsRegistered=" + totalClientsRegistered + ", totalUpcomingEvents="
					+ totalUpcomingEvents + ", totalCompletedEvents=" + totalCompletedEvents + ", topVendors="
					+ topVendors + ", totalRevenue=" + totalRevenue + "]";
		}
	    
}
