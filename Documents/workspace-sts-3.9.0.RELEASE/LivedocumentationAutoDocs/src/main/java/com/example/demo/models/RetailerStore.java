package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RetailerStore {
	/**
	 * @param id ID of the RetailerStore.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * @param ticker Ticker of the RetailerStore.
	 */
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Ticker ticker;

	/**
	 * @param state State of the RetailerStore.
	 */
	@OneToOne(cascade = CascadeType.DETACH)
	private State state;

	/**
	 * @param city City of the RetailerStore.
	 */
	@OneToOne(cascade = CascadeType.DETACH)
	private City city;

	/**
	 * @param storeId StoreId of the RetailerStore.
	 */
	@Column(name = "store_id")
	private Integer storeId;

	/**
	 * @param address Address of the RetailerStore.
	 */
	private String address;
	
	/**
	 * @param zip Zip of the RetailerStore.
	 */
	private Long zip;

	/**
	 * @param comment Comment of the RetailerStore.
	 */
	@Enumerated(EnumType.ORDINAL)
	private Comment comment;

	
	/**
	 * @param mallType MallType of the RetailerStore.
	 */
	@Enumerated(EnumType.ORDINAL)
	private MallType mallType;

	/**
	 * @param storeNumber StoreNumber of the RetailerStore.
	 */
	private Integer storeNumber;
	
	/**
	 * @param latitude Latitude of the RetailerStore.
	 */
	private Double latitude;
	
	/**
	 * @param longitude Longitude of the RetailerStore.
	 */
	private Double longitude;
	
	/**
	 * @param spaces Spaces of the RetailerStore.
	 */
	private Integer spaces;
	
	/**
	 * @param minCars MinCars of the RetailerStore.
	 */
	private Integer minCars;
	
	/**
	 * @param maxCars MaxCars of the RetailerStore.
	 */
	private Integer maxCars;

	/**
	 * @param mallName MallName of the RetailerStore.
	 */
	private String mallName;
	
	/**
	 * @param mallAddress MallAddress of the RetailerStore.
	 */
	private String mallAddress;
	
	/**
	 * @param mallRemarks MallRemarks of the RetailerStore.
	 */
	private String mallRemarks;
	
	/**
	 * @param mallOnlyType MallOnlyType of the RetailerStore.
	 */
	@Enumerated(EnumType.ORDINAL)
	private MallOnlyType mallOnlyType;
	
	/**
	 * @param grade Grade of the RetailerStore.
	 */
	@Enumerated(EnumType.STRING)
	private Grade grade;
	
	/**
	 * @param reit Reit of the RetailerStore.
	 */
	@OneToOne(cascade = CascadeType.DETACH)
	private Reit reit;
	
	/**
	 * @param cmbx Cmbx of the RetailerStore.
	 */
	private Integer cmbx;
	
	
	/**
	 * @param activated Activated of the RetailerStore.
	 */
	private Boolean activated;

}
